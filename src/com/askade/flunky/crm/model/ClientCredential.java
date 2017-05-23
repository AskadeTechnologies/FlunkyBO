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
@Table(name = "xxflk_client_credentials")
public class ClientCredential {

    private BigInteger id;
    private BigInteger clientId;
    private BigInteger userId;
    private Date dateIn;
    private Date dateOut;
    private Date creationDate;
    private String createdBy;
    private Date lastUpdateDate;
    private String lastUpdatedBy;

    public ClientCredential() {
    }

    @Id
    @Column(name = "id", updatable = false)
    public BigInteger getId() {
        return id;
    }
    @Column(name = "client_id", updatable = false)
    public BigInteger getClientId() {
        return clientId;
    }
    @Column(name = "user_id", updatable = false)
    public BigInteger getUserId() {
        return userId;
    }
    @Column(name = "date_id", updatable = false)
    public Date getDateIn() {
        return dateIn;
    }
    @Column(name = "date_out", updatable = true)
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

    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setClientId(BigInteger clientId) {
        this.clientId = clientId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
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
}
