package com.askade.flunky.request;

import java.util.Date;

/**
 * Created by AdrianIonita on 6/3/2017.
 */
public class ClientEnrolmentRequest {

    private String authKey;
    private String username;
    private String password;
    private String socialMediaId;
    private String firstName;
    private String lastName;
    private Integer clientTypeId;
    private Integer clientCategoryId;
    private Integer clientSubcategoryId;
    private Integer phoneTypeId;
    private String clientPhone;
    private String nationalIdentifier;
    private String sex;
    private Date birthDate;
    private Integer documentTypeId;
    private String serialNo;
    private String docNumber;
    private Date issueDate;
    private Date expiryDate;

    @Override
    public String toString() {
        return "ClientEnrolmentRequest{" +
                "authKey='" + authKey + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", socialMediaId='" + socialMediaId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", clientTypeId=" + clientTypeId +
                ", clientCategoryId=" + clientCategoryId +
                ", clientSubcategoryId=" + clientSubcategoryId +
                ", phoneTypeId=" + phoneTypeId +
                ", clientPhone='" + clientPhone + '\'' +
                ", nationalIdentifier='" + nationalIdentifier + '\'' +
                ", sex='" + sex + '\'' +
                ", birthDate=" + birthDate +
                ", documentTypeId=" + documentTypeId +
                ", serialNo='" + serialNo + '\'' +
                ", docNumber='" + docNumber + '\'' +
                ", issueDate=" + issueDate +
                ", expiryDate=" + expiryDate +
                '}';
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSocialMediaId() {
        return socialMediaId;
    }

    public void setSocialMediaId(String socialMediaId) {
        this.socialMediaId = socialMediaId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getClientTypeId() {
        return clientTypeId;
    }

    public void setClientTypeId(Integer clientTypeId) {
        this.clientTypeId = clientTypeId;
    }

    public Integer getClientCategoryId() {
        return clientCategoryId;
    }

    public void setClientCategoryId(Integer clientCategoryId) {
        this.clientCategoryId = clientCategoryId;
    }

    public Integer getClientSubcategoryId() {
        return clientSubcategoryId;
    }

    public void setClientSubcategoryId(Integer clientSubcategoryId) {
        this.clientSubcategoryId = clientSubcategoryId;
    }

    public Integer getPhoneTypeId() {
        return phoneTypeId;
    }

    public void setPhoneTypeId(Integer phoneTypeId) {
        this.phoneTypeId = phoneTypeId;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getNationalIdentifier() {
        return nationalIdentifier;
    }

    public void setNationalIdentifier(String nationalIdentifier) {
        this.nationalIdentifier = nationalIdentifier;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(Integer documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

}
