package br.com.haircutter.admin.facade.json;

import br.com.haircutter.admin.enums.WeekdayEnum;

public class ProfessionalCalendarJson {

	private Long id;

	private EstablishmentEmployeeJson establishmentEmployee;

	private Long establishmentEmployeeId;

    private WeekdayEnum weekday;

	private Integer scheduleInMinutes;

	public ProfessionalCalendarJson() {

	}

    public ProfessionalCalendarJson(EstablishmentEmployeeJson establishmentEmployee, Long id, Long establishmentEmployeeId, WeekdayEnum weekday, Integer scheduleInMinutes) {
        this.establishmentEmployee = establishmentEmployee;
        this.id = id;
        this.establishmentEmployeeId = establishmentEmployeeId;
        this.weekday = weekday;
        this.scheduleInMinutes = scheduleInMinutes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstablishmentEmployeeJson getEstablishmentEmployee() {
        return establishmentEmployee;
    }

    public void setEstablishmentEmployee(EstablishmentEmployeeJson establishmentEmployee) {
        this.establishmentEmployee = establishmentEmployee;
    }

    public Long getEstablishmentEmployeeId() {
        return establishmentEmployeeId;
    }

    public void setEstablishmentEmployeeId(Long establishmentEmployeeId) {
        this.establishmentEmployeeId = establishmentEmployeeId;
    }

    public WeekdayEnum getWeekday() {
        return weekday;
    }

    public void setWeekday(WeekdayEnum weekday) {
        this.weekday = weekday;
    }

    public Integer getScheduleInMinutes() {
        return scheduleInMinutes;
    }

    public void setScheduleInMinutes(Integer scheduleInMinutes) {
        this.scheduleInMinutes = scheduleInMinutes;
    }
}
