package com.askade.flunky.operations.model;

import com.askade.flunky.utils.FlunkyUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by AdrianIonita on 6/4/2017.
 */
@Entity
@Table(name = "xxflk_operations")
public class Operation {
    public final static String sequenceName = "xxflk_operations_s";
    private String voucherNr;
    private Integer operTypeId;
    private Integer operClientOrderer;
    private String status;
    private Date dateIn;
    private Date dateOut;
    private Date creationDate;
    private String createdBy;
    private Date lastUpdateDate;
    private String lastUpdatedBy;

    public Operation() {
    }

    @Id
    @Column(name = "voucher_nr", updatable = false, columnDefinition = "VARCHAR")
    public String getVoucherNr() {
        return voucherNr;
    }
    @Column(name = "oper_type_id", updatable = false)
    public Integer getOperTypeId() {
        return operTypeId;
    }
    @Column(name = "oper_client_orderer", updatable = false)
    public Integer getOperClientOrderer() {
        return operClientOrderer;
    }
    @Column(name = "status")
    public String getStatus() {
        return status;
    }
    @Column(name = "data_in", updatable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone= FlunkyUtils.BUCHAREST_TIME_ZONE)
    public Date getDateIn() {
        return dateIn;
    }
    @Column(name = "data_out", updatable = true)
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

    public void setVoucherNr(String voucherNr) {
        this.voucherNr = voucherNr;
    }

    public void setOperTypeId(Integer operTypeId) {
        this.operTypeId = operTypeId;
    }

    public void setOperClientOrderer(Integer operClientOrderer) {
        this.operClientOrderer = operClientOrderer;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "Operation{" +
                "voucherNr='" + voucherNr + '\'' +
                ", operTypeId=" + operTypeId +
                ", operClientOrderer=" + operClientOrderer +
                ", status='" + status + '\'' +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                ", creationDate=" + creationDate +
                ", createdBy='" + createdBy + '\'' +
                ", lastUpdateDate=" + lastUpdateDate +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                '}';
    }

    public static class OperationStatus{
        public final static String INIT = "INIT";
        public final static String DONE = "DONE";
        public final static String CANCEL = "CANCEL";
        public final static String ERROR = "ERROR";
    }
}
