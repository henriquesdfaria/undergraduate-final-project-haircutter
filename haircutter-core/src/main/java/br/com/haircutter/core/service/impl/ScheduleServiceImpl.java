package br.com.haircutter.core.service.impl;

import br.com.haircutter.core.enums.ScheduleStatusEnum;
import br.com.haircutter.core.model.EstablishmentEmployee;
import br.com.haircutter.core.model.ProfessionalService;
import br.com.haircutter.core.model.Schedule;
import br.com.haircutter.core.model.User;
import br.com.haircutter.core.model.repository.EstablishmentEmployeeRespository;
import br.com.haircutter.core.model.repository.ProfessionalServiceRepository;
import br.com.haircutter.core.model.repository.ScheduleRepository;
import br.com.haircutter.core.model.repository.UserRepository;
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
    EstablishmentAuditLogService establishmentAuditLogService;

    @Autowired
    ProfessionalServiceRepository professionalServiceRepository;

    @Autowired
    EstablishmentEmployeeRespository establishmentEmployeeRespository;

    @Autowired
    HaircutterMailSender haircutterMailSender;

    @Autowired
    UserRepository userRepository;

    @Override
    public Schedule create(Long professionalServiceId, String username, Date scheduleDate, Integer scheduleInMinutes,
                           String author) {

        Date now = new Date(ZonedDateTime.now().toInstant().toEpochMilli());

        Schedule schedule = new Schedule(professionalServiceId, username, scheduleDate, scheduleInMinutes,
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
            List<Schedule> s = scheduleRepository.findAllByProfessionalServiceId(professionalService.getId());
            schedules.addAll(s);
        });

        return new ArrayList<>(schedules);
    }

    @Override
    public List<Schedule> getAllByUsername(String username) {
        return scheduleRepository.findAllByUsername(username);
    }

    private void sendCreationEmail(String email, String action) {

        String subject = "Agendamento de Horário";

        String text = "Olá,\n\n" +
                "Você tem um horário agendado " + action +
                "\n\n Acesse nosso site para mais informações wwww.haircutter.com.br" +
                "\n\n\n\nEquipe Haircutter";

        haircutterMailSender.sendEmail(email, subject, text);
    }

    private void sendCanceledEmail(String email, String action) {

        String subject = "Agendamento cancelado";

        String text = "Olá,\n\n" +
                "Você tem cancelado um horário agendado " + action +
                "\n\n Acesse nosso site para mais informações wwww.haircutter.com.br" +
                "\n\n\n\nEquipe Haircutter";

        haircutterMailSender.sendEmail(email, subject, text);
    }
}
