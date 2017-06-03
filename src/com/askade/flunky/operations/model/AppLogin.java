package com.askade.flunky.operations.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by AdrianIonita on 5/16/2017.
 */
@Entity
@Table(name = "xxflk_app_logins")
public class AppLogin {
    private Integer id;
    private String callerApp;
    private String authKey;
    private Integer clientId;
    private Date startDate;
    private Date endDate;
    private Date creationDate;
    private String createdBy;
    private Date lastUpdateDate;
    private String lastUpdatedBy;


    public AppLogin() {
    }

    public AppLogin(String callerApp) {
        this.callerApp = callerApp;
    }

    @Column(name = "id", updatable = false)
    @Id
    public Integer getId() {
        return id;
    }

    @Column (name = "caller_app", updatable = false)
    public String getCallerApp() {
        return callerApp;
    }

    @Column (name = "auth_key", updatable = false)
    public String getAuthKey() {
        return authKey;
    }

    @Column (name = "client_id", updatable = false)
    public Integer getClientId() {
        return clientId;
    }

    @Column (name = "start_date", updatable = false)
    public Date getStartDate() {
        return startDate;
    }

    @Column (name = "end_date")
    public Date getEndDate() {
        return endDate;
    }

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column (name = "creation_date", updatable = false)
    public Date getCreationDate() {
        return creationDate;
    }
    @Column (name = "created_by", updatable = false)
    public String getCreatedBy() {
        return createdBy;
    }
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Version
    @Column (name = "last_update_date")
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }
    @Column (name = "last_updated_by")
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCallerApp(String callerApp) {
        this.callerApp = callerApp;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
