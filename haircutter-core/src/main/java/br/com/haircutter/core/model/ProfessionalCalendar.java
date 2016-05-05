package br.com.haircutter.core.model;

import br.com.haircutter.core.enums.WeekdayEnum;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "establishment_professional_calendar")
public class ProfessionalCalendar implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Transient
	private EstablishmentEmployee establishmentEmployee;

    @Transient
    private String weekdayTitle;

	@Column(name = "establishment_employee_id")
	private Long establishmentEmployeeId;

    @Column(name = "weekday")
    @Enumerated(EnumType.STRING)
    private WeekdayEnum weekday;

	@Column(name = "schedule-in-minutes")
	private Integer scheduleInMinutes;

	public ProfessionalCalendar() {
	}

    public ProfessionalCalendar(EstablishmentEmployee establishmentEmployee, String weekdayTitle, Long establishmentEmployeeId, WeekdayEnum weekday, Integer scheduleInMinutes) {
        this.establishmentEmployee = establishmentEmployee;
        this.weekdayTitle = weekdayTitle;
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

    public EstablishmentEmployee getEstablishmentEmployee() {
        return establishmentEmployee;
    }

    public void setEstablishmentEmployee(EstablishmentEmployee establishmentEmployee) {
        this.establishmentEmployee = establishmentEmployee;
    }

    public String getWeekdayTitle() {
        return weekdayTitle;
    }

    public void setWeekdayTitle(String weekdayTitle) {
        this.weekdayTitle = weekdayTitle;
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
