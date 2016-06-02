package br.com.haircutter.core.service.impl;

import br.com.haircutter.core.enums.ScheduleStatusEnum;
import br.com.haircutter.core.model.*;
import br.com.haircutter.core.model.repository.*;
import br.com.haircutter.core.service.EstablishmentAuditLogService;
import br.com.haircutter.core.service.ScheduleService;
import br.com.haircutter.core.utils.HaircutterMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.ZonedDateTime;
import java.util.*;


@Service
public class ScheduleServiceImpl implements ScheduleService {

    public static final int MINUTES_TO_MILLISECONDS = 60000;
    @Autowired
    ScheduleRepository scheduleRepository;


    @Autowired
    EstablishmentRespository establishmentRespository;

    @Autowired
    EstablishmentAuditLogService establishmentAuditLogService;

    @Autowired
    ProfessionalServiceRepository professionalServiceRepository;

    @Autowired
    EstablishmentEmployeeRespository establishmentEmployeeRespository;

    @Autowired
    EstablishmentServiceRespository establishmentServiceRespository;

    @Autowired
    HaircutterMailSender haircutterMailSender;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Override
    public Schedule create(Long professionalServiceId, String username, Date scheduleDate, Integer scheduleInMinutes,
                           String author) {

        Date now = new Date(ZonedDateTime.now().toInstant().toEpochMilli());

        Schedule schedule = new Schedule(null, professionalServiceId, null, null, null, null, username, scheduleDate, scheduleInMinutes,
                ScheduleStatusEnum.ACCEPTED, now, now);


        Schedule createdSchedule = scheduleRepository.save(schedule);

        ProfessionalService professionalService = professionalServiceRepository
                .findOne(createdSchedule.getProfessionalServiceId());

        EstablishmentEmployee establishmentEmployee = establishmentEmployeeRespository
                .findOne(professionalService.getEstablishmentEmployeeId());

        String scheduleText = " no dia " +
                createdSchedule.getScheduleDate() + " às "
                + new Time(createdSchedule.getScheduleInMinutes() * MINUTES_TO_MILLISECONDS);
        String action = "Agendou horário para " + establishmentEmployee.getUser().getName() + scheduleText;

        establishmentAuditLogService.registerLog(establishmentEmployee.getEstablishmentCnpj(), author, action);

        if (createdSchedule.getUsername() != null) {
            User user = userRepository.findOneByUsername(createdSchedule.getUsername());
            sendCreationEmail(user.getEmail(), scheduleText);
        }

        sendCreationEmail(establishmentEmployee.getUser().getEmail(), scheduleText);

        return createdSchedule;
    }

    @Override
    public void cancel(Long scheduleId, String author) {
        Schedule schedule = scheduleRepository.findOneByIdAndUsername(scheduleId, author);

        schedule.setStatus(ScheduleStatusEnum.CANCELED);
        schedule.setLastModifiedDate(new Date(ZonedDateTime.now().toInstant().toEpochMilli()));

        Schedule canceledSchedule = scheduleRepository.save(schedule);

        String scheduleText = " no dia " +
                canceledSchedule.getScheduleDate() + " às "
                + new Time(canceledSchedule.getScheduleInMinutes() * MINUTES_TO_MILLISECONDS);

        ProfessionalService professionalService = professionalServiceRepository
                .findOne(canceledSchedule.getProfessionalServiceId());

        EstablishmentEmployee establishmentEmployee = establishmentEmployeeRespository
                .findOne(professionalService.getEstablishmentEmployeeId());

        String action = "Cancelou horário para " + establishmentEmployee.getUser().getName() + scheduleText;

        establishmentAuditLogService.registerLog(establishmentEmployee.getEstablishmentCnpj(), author, action);

        if (canceledSchedule.getUsername() != null) {
            User user = userRepository.findOneByUsername(canceledSchedule.getUsername());
            sendCanceledEmail(user.getEmail(), scheduleText);
        }

        sendCanceledEmail(establishmentEmployee.getUser().getEmail(), scheduleText);

    }

    @Override
    public List<Schedule> getAllByProfessional(Long establishmentEmployeeId) {

        List<ProfessionalService> professionalServices = professionalServiceRepository.findAllByEstablishmentEmployeeId(establishmentEmployeeId);

        Set<Schedule> schedules = new HashSet<>();

        professionalServices.stream().forEach(professionalService -> {
            List<Schedule> s = scheduleRepository.findAllByProfessionalServiceIdAndStatus(professionalService.getId(), ScheduleStatusEnum.ACCEPTED);
            schedules.addAll(s);
        });

        schedules.stream().forEach(schedule -> {
            schedule.setProfessionalService(professionalServiceRepository.findOne(schedule.getProfessionalServiceId()));
            schedule.getProfessionalService().setEstablishmentService(establishmentServiceRespository.findOne(schedule.getProfessionalService().getEstablishmentServiceId()));
            User user = userRepository.findOneByUsername(schedule.getUsername());
            user.setProfile(userProfileRepository.findOneByUsername(user.getUsername()));
            schedule.setClientName(user.getName());
            schedule.setClientEmail(user.getEmail());
            schedule.setClientPhone(user.getProfile().getPhone());
        });

        return new ArrayList<>(schedules);
    }

    @Override
    public List<Schedule> getAllByUsername(String username) {

        List<Schedule> schedules = scheduleRepository.findAllByUsernameAndStatus(username, ScheduleStatusEnum.ACCEPTED);

        schedules.stream().forEach(schedule -> {
            schedule.setProfessionalService(professionalServiceRepository.findOne(schedule.getProfessionalServiceId()));
            EstablishmentEmployee ee =establishmentEmployeeRespository.findOne(schedule.getProfessionalService().getEstablishmentEmployeeId());
            schedule.getProfessionalService().setEstablishmentEmployee(ee);
            schedule.getProfessionalService().setEstablishmentService(establishmentServiceRespository.findOne(schedule.getProfessionalService().getEstablishmentServiceId()));
            schedule.setEstablishmentName(establishmentRespository.findOneByCnpj(ee.getEstablishmentCnpj()).getName());
        });

        return schedules;
    }

    private void sendCreationEmail(String email, String action) {

        String subject = "Agendamento de Horário";

        String text = "Olá,\n\n" +
                "Você tem um horário agendado " + action +
                "\n\n Acesse nosso site para mais informações www.haircutter.com.br" +
                "\n\n\n\nEquipe Haircutter";

        haircutterMailSender.sendEmail(email, subject, text);
    }

    private void sendCanceledEmail(String email, String action) {

        String subject = "Agendamento cancelado";

        String text = "Olá,\n\n" +
                "Você tem cancelado um horário agendado " + action +
                "\n\n Acesse nosso site para mais informações www.haircutter.com.br" +
                "\n\n\n\nEquipe Haircutter";

        haircutterMailSender.sendEmail(email, subject, text);
    }
}
