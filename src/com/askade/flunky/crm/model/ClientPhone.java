package com.askade.flunky.crm.model;

import com.askade.flunky.utils.FlunkyUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by AdrianIonita on 6/3/2017.
 */
@Entity
@Table(name = "xxflk_client_phones")
public class ClientPhone {
    public final static String sequenceName = "xxflk_client_phones_s";
    private Integer id;
    private Integer clientId;
    private Integer phoneTypeId;
    private String phoneNo;
    private Date dateIn;
    private Date dateOut;
    private Date creationDate;
    private String createdBy;
    private Date lastUpdateDate;
    private String lastUpdatedBy;

    public ClientPhone() {
    }

    @Id
    @Column(name = "id", updatable = false)
    public Integer getId() {
        return id;
    }
    @Column(name = "client_id", updatable = false)
    public Integer getClientId() {
        return clientId;
    }
    @Column(name = "phone_type_id", updatable = false)
    public Integer getPhoneTypeId() {
        return phoneTypeId;
    }
    @Column(name = "phone", updatable = false)
    public String getPhoneNo() {
        return phoneNo;
    }
    @Column(name = "data_in", updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone= FlunkyUtils.BUCHAREST_TIME_ZONE)
    public Date getDateIn() {
        return dateIn;
    }
    @Column(name = "data_out")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone= FlunkyUtils.BUCHAREST_TIME_ZONE)
    public Date getDateOut() {
        return dateOut;
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

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public void setPhoneTypeId(Integer phoneTypeId) {
        this.phoneTypeId = phoneTypeId;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
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

    @Override
    public String toString() {
        return "ClientPhone{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", phoneTypeId=" + phoneTypeId +
                ", phoneNo='" + phoneNo + '\'' +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                ", creationDate=" + creationDate +
                ", createdBy='" + createdBy + '\'' +
                ", lastUpdateDate=" + lastUpdateDate +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                '}';
    }
}
