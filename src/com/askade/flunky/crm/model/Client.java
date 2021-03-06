package com.askade.flunky.crm.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by AdrianIonita on 5/22/2017.
 */
@Entity
@Table(name = "xxflk_clients")
public class Client {
    public static final String sequenceName = "xxflk_clients_s";

    private Integer clientId;
    private String clientName;
    private Integer clientCategory;
    private Integer clientSubcategory;
    private Integer clientTypeId;
    private Date creationDate;
    private String createdBy;
    private Date lastUpdateDate;
    private String lastUpdatedBy;

    public Client() {
    }

    @Id
    @Column(name = "client_id", updatable = false)
    public Integer getClientId() {
        return clientId;
    }

    @Column(name = "client_name", updatable = true)
    public String getClientName() {
        return clientName;
    }

    @Column(name = "client_category_id", updatable = true)
    public Integer getClientCategory() {
        return clientCategory;
    }

    @Column(name = "client_subcategory_id", updatable = true)
    public Integer getClientSubcategory() {
        return clientSubcategory;
    }

    @Column(name = "client_type_id", updatable = true)
    public Integer getClientTypeId() {
        return clientTypeId;
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

    public void setClientId(Integer clientId) {

        this.clientId = clientId;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setClientCategory(Integer clientCategory) {
        this.clientCategory = clientCategory;
    }

    public void setClientSubcategory(Integer clientSubcategory) {
        this.clientSubcategory = clientSubcategory;
    }

    public void setClientTypeId(Integer clientTypeId) {
        this.clientTypeId = clientTypeId;
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
        return "Client{" +
                "clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", clientCategory=" + clientCategory +
                ", clientSubcategory=" + clientSubcategory +
                ", clientTypeId=" + clientTypeId +
                ", creationDate=" + creationDate +
                ", createdBy='" + createdBy + '\'' +
                ", lastUpdateDate=" + lastUpdateDate +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                '}';
    }
}
