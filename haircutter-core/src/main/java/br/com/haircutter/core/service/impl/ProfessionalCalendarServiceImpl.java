package br.com.haircutter.core.service.impl;

import br.com.haircutter.core.model.ProfessionalCalendar;
import br.com.haircutter.core.model.repository.ProfessionalCalendarRespository;
import br.com.haircutter.core.service.EstablishmentAuditLogService;
import br.com.haircutter.core.service.EstablishmentEmployeeService;
import br.com.haircutter.core.service.ProfessionalCalendarService;
import br.com.haircutter.core.validator.ProfessionalCalendarValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;


@Service
public class ProfessionalCalendarServiceImpl implements ProfessionalCalendarService {

    public static final int MILLISECOND_TO_MINUTE = 60000;

    @Autowired
    private ProfessionalCalendarRespository professionalCalendarRespository;

    @Autowired
    private EstablishmentEmployeeService establishmentEmployeeService;

    @Autowired
    private ProfessionalCalendarValidator validator;

    @Autowired
    private EstablishmentAuditLogService auditLogService;

    public ProfessionalCalendar create(ProfessionalCalendar professionalCalendar, String cnpj, String username) {

        validator.validate(professionalCalendar);

        ProfessionalCalendar createdProfessionalCalendar = professionalCalendarRespository.save(professionalCalendar);

        createdProfessionalCalendar.setEstablishmentEmployee(establishmentEmployeeService
                .get(createdProfessionalCalendar.getEstablishmentEmployeeId()));

        createdProfessionalCalendar.setWeekdayTitle(createdProfessionalCalendar.getWeekday().getBundle());

        auditLogService.registerLog(cnpj, username, "O profissional agora atende o horário "
                + professionalCalendar.getWeekday().getBundle() + " às "
                + new Time(professionalCalendar.getScheduleInMinutes() * MILLISECOND_TO_MINUTE).toString());

        return createdProfessionalCalendar;
    }

    public void delete(Long professionalCalendarId, String cnpj, String username) {

        ProfessionalCalendar professionalCalendar = professionalCalendarRespository.findOne(professionalCalendarId);

        professionalCalendarId = professionalCalendar.getId();

        professionalCalendarRespository.delete(professionalCalendarId);

        auditLogService.registerLog(cnpj, username, "O profissional não atende mais o horário "
                + professionalCalendar.getWeekday().getBundle() + " às "
                + new Time(professionalCalendar.getScheduleInMinutes() * MILLISECOND_TO_MINUTE).toString());
    }

    @Override
    public List<ProfessionalCalendar> getAllByEstablishmentEmployeeId(Long establishmentEmployeeId) {

        List<ProfessionalCalendar> professionalServices = professionalCalendarRespository.findAllByEstablishmentEmployeeId(establishmentEmployeeId);

        professionalServices.stream().forEach(ps -> {
            ps.setEstablishmentEmployee(establishmentEmployeeService.get(ps.getEstablishmentEmployeeId()));
            ps.setWeekdayTitle(ps.getWeekday().getBundle());
        });

        return professionalServices;
    }

}
