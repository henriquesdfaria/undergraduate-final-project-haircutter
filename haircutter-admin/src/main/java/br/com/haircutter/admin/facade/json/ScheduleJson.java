package br.com.haircutter.admin.facade.json;

import br.com.haircutter.admin.enums.ScheduleStatusEnum;

import java.util.Date;

/**
 * Created by hfaria on 5/11/16.
 */
public class ScheduleJson {

    private Long id;

    private Long professionalServiceId;

    private String username;

    private Date scheduleDate;

    private Integer scheduleInMinutes;

    private ScheduleStatusEnum status;

    private Date lastModifiedDate;

    private Date creationTime;

    public ScheduleJson() {

    }

    public ScheduleJson(Long professionalServiceId, String username, Date scheduleDate, Integer scheduleInMinutes, ScheduleStatusEnum status, Date lastModifiedDate, Date creationTime) {
        this.professionalServiceId = professionalServiceId;
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

    public Long getProfessionalServiceId() {
        return professionalServiceId;
    }

    public void setProfessionalServiceId(Long professionalServiceId) {
        this.professionalServiceId = professionalServiceId;
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
