package com.askade.flunky.appconfig.model;

import com.askade.flunky.utils.FlunkyUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by AdrianIonita on 5/25/2017.
 */
@Entity
@Table(name = "xxflk_client_subcategories")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientSubcategory {

    private Integer id;
    private Integer categoryId;
    private String code;
    private String description;
    private Date dateIn;
    private Date dateOut;
    private Date creationDate;
    private String createdBy;
    private Date lastUpdateDate;
    private String lastUpdatedBy;

    public ClientSubcategory() {
    }

    @Column(name = "id")
    @Id
    public Integer getId() {
        return id;
    }

    @Column(name = "category_id")
    public Integer getCategoryId() {
        return categoryId;
    }

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @Column(name = "data_in")
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

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    public Date getCreationDate() {
        return creationDate;
    }

    @Column(name = "created_by")
    public String getCreatedBy() {
        return createdBy;
    }

    @Column(name = "last_update_date")
    @Temporal(TemporalType.TIMESTAMP)
    @Version
    @UpdateTimestamp
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    @Column(name = "last_updated_by")
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
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
