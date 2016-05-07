package br.com.haircutter.admin.facade.json;

import br.com.haircutter.admin.enums.WeekdayEnum;

public class ProfessionalCalendarJson {

	private Long id;

	private EstablishmentEmployeeJson establishmentEmployee;

	private Long establishmentEmployeeId;

    private WeekdayEnum weekday;

	private Integer scheduleInMinutes;

    private Integer scheduleInMinutesFrom;

    private Integer scheduleInMinutesTo;

	public ProfessionalCalendarJson() {

	}

    public ProfessionalCalendarJson(Long id, EstablishmentEmployeeJson establishmentEmployee, Long establishmentEmployeeId, WeekdayEnum weekday, Integer scheduleInMinutes, Integer scheduleInMinutesFrom, Integer scheduleInMinutesTo) {
        this.id = id;
        this.establishmentEmployee = establishmentEmployee;
        this.establishmentEmployeeId = establishmentEmployeeId;
        this.weekday = weekday;
        this.scheduleInMinutes = scheduleInMinutes;
        this.scheduleInMinutesFrom = scheduleInMinutesFrom;
        this.scheduleInMinutesTo = scheduleInMinutesTo;
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

    public Integer getScheduleInMinutesFrom() {
        return scheduleInMinutesFrom;
    }

    public void setScheduleInMinutesFrom(Integer scheduleInMinutesFrom) {
        this.scheduleInMinutesFrom = scheduleInMinutesFrom;
    }

    public Integer getScheduleInMinutesTo() {
        return scheduleInMinutesTo;
    }

    public void setScheduleInMinutesTo(Integer scheduleInMinutesTo) {
        this.scheduleInMinutesTo = scheduleInMinutesTo;
    }
}
