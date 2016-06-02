package br.com.haircutter.core.model;

import br.com.haircutter.core.enums.ScheduleStatusEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by hfaria on 5/11/16.
 */
@Entity
@Table(name = "schedule")
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Transient
    private String establishmentName;

    @Column(name = "establishment_professional_service_id")
    private Long professionalServiceId;

    @Transient
    private ProfessionalService professionalService;

    @Transient
    private String clientName;

    @Transient
    private String clientPhone;

    @Transient
    private String clientEmail;

    @Column(name = "username")
    private String username;

    @Column(name = "schedule_date")
    private Date scheduleDate;

    @Column(name = "schedule_in_minutes")
    private Integer scheduleInMinutes;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ScheduleStatusEnum status;

    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    @Column(name = "creation_time")
    private Date creationTime;

    public Schedule() {
    }

    public Schedule(String establishmentName, Long professionalServiceId, ProfessionalService professionalService, String clientName, String clientPhone, String clientEmail, String username, Date scheduleDate, Integer scheduleInMinutes, ScheduleStatusEnum status, Date lastModifiedDate, Date creationTime) {
        this.establishmentName = establishmentName;
        this.professionalServiceId = professionalServiceId;
        this.professionalService = professionalService;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.clientEmail = clientEmail;
        this.username = username;
        this.scheduleDate = scheduleDate;
        this.scheduleInMinutes = scheduleInMinutes;
        this.status = status;
        this.lastModifiedDate = lastModifiedDate;
        this.creationTime = creationTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstablishmentName() {
        return establishmentName;
    }

    public void setEstablishmentName(String establishmentName) {
        this.establishmentName = establishmentName;
    }

    public Long getProfessionalServiceId() {
        return professionalServiceId;
    }

    public void setProfessionalServiceId(Long professionalServiceId) {
        this.professionalServiceId = professionalServiceId;
    }

    public ProfessionalService getProfessionalService() {
        return professionalService;
    }

    public void setProfessionalService(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public Integer getScheduleInMinutes() {
        return scheduleInMinutes;
    }

    public void setScheduleInMinutes(Integer scheduleInMinutes) {
        this.scheduleInMinutes = scheduleInMinutes;
    }

    public ScheduleStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ScheduleStatusEnum status) {
        this.status = status;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}
