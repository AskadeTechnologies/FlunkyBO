package com.askade.flunky.crm.model;

import com.askade.flunky.utils.FlunkyUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by AdrianIonita on 4/29/2017.
 */
@Entity
@Table(name = "xxflk_users")
public class ClientUser {

    private Integer userId;
    private String userName;
    private String userDescription;
    private String password;
    private String socialMediaId;
    private Date dateIn;
    private Date dateOut;
    private Date creationDate;
    private String createdBy;
    private Date lastUpdateDate;
    private String lastUpdatedBy;

    public ClientUser() {
    }

    public ClientUser(String userName, String password, String socialMediaId) {
        this.userName = userName;
        this.password = password;
        this.socialMediaId = socialMediaId;
    }

    @PrePersist
    public void prePersist() {
        if(dateIn == null) {
            dateIn = FlunkyUtils.getCurrentDate();
        }
        if(createdBy == null){
            createdBy = FlunkyUtils.ANONYMOUS;
        }
        if(lastUpdatedBy == null){
            lastUpdatedBy = FlunkyUtils.ANONYMOUS;
        }
    }

    @Column(name = "user_id", updatable = false)
    @Id
    public Integer getUserId() {
        return userId;
    }

    @Column (name = "user_name", updatable = false)
    public String getUserName() {
        return userName;
    }

    @Column (name = "user_description")
    public String getUserDescription() {
        return userDescription;
    }

    @Column (name = "password")
    public String getPassword() {
        return password;
    }

    @Column (name = "social_media_id")
    public String getSocialMediaId() {
        return socialMediaId;
    }

    @Column (name = "data_in", updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone= FlunkyUtils.BUCHAREST_TIME_ZONE)
    public Date getDateIn() {
        return dateIn;
    }

    @Column (name = "data_out")
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

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSocialMediaId(String socialMediaId) {
        this.socialMediaId = socialMediaId;
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


    /**
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientUser)) return false;

        ClientUser clientUser = (ClientUser) o;

        if (getUserName() != null ? !getUserName().equals(clientUser.getUserName()) : clientUser.getUserName() != null)
            return false;
        return getSocialMediaId() != null ? getSocialMediaId().equals(clientUser.getSocialMediaId()) : clientUser.getSocialMediaId() == null;
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        int result = getUserName() != null ? getUserName().hashCode() : 0;
        result = 31 * result + (getSocialMediaId() != null ? getSocialMediaId().hashCode() : 0);
        return result;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "ClientUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userDescription='" + userDescription + '\'' +
                ", password='" + password + '\'' +
                ", socialMediaId='" + socialMediaId + '\'' +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                ", creationDate=" + creationDate +
                ", createdBy='" + createdBy + '\'' +
                ", lastUpdateDate=" + lastUpdateDate +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                '}';
    }
}
