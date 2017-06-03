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
@Table(name = "xxflk_client_addresses")
public class ClientAddress {
    public final static String sequenceName = "xxflk_client_addresses_s";
    private Integer id;
    private Integer clientId;
    private Integer addressCategoryId;
    private Integer addressTypeId;
    private String country;
    private String city;
    private String district;
    private String street;
    private String streetNo;
    private String buildingNo;
    private String floorNo;
    private String apartmentNo;
    private Date dateIn;
    private Date dateOut;
    private Date creationDate;
    private String createdBy;
    private Date lastUpdateDate;
    private String lastUpdatedBy;

    public ClientAddress() {
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
    @Column(name = "address_category_id")
    public Integer getAddressCategoryId() {
        return addressCategoryId;
    }
    @Column(name = "address_type_id")
    public Integer getAddressTypeId() {
        return addressTypeId;
    }
    @Column(name = "country")
    public String getCountry() {
        return country;
    }
    @Column(name = "city")
    public String getCity() {
        return city;
    }
    @Column(name = "district")
    public String getDistrict() {
        return district;
    }
    @Column(name = "street")
    public String getStreet() {
        return street;
    }
    @Column(name = "street_no")
    public String getStreetNo() {
        return streetNo;
    }
    @Column(name = "building_no")
    public String getBuildingNo() {
        return buildingNo;
    }
    @Column(name = "floor_no")
    public String getFloorNo() {
        return floorNo;
    }
    @Column(name = "apartment_no")
    public String getApartmentNo() {
        return apartmentNo;
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

    public void setAddressCategoryId(Integer addressCategoryId) {
        this.addressCategoryId = addressCategoryId;
    }

    public void setAddressTypeId(Integer addressTypeId) {
        this.addressTypeId = addressTypeId;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
    }

    public void setApartmentNo(String apartmentNo) {
        this.apartmentNo = apartmentNo;
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
        return "ClientAddress{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", addressCategoryId=" + addressCategoryId +
                ", addressTypeId=" + addressTypeId +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", streetNo='" + streetNo + '\'' +
                ", buildingNo='" + buildingNo + '\'' +
                ", floorNo='" + floorNo + '\'' +
                ", apartmentNo='" + apartmentNo + '\'' +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                ", creationDate=" + creationDate +
                ", createdBy='" + createdBy + '\'' +
                ", lastUpdateDate=" + lastUpdateDate +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                '}';
    }
}
