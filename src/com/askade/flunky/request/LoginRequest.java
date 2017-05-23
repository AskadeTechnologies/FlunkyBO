package com.askade.flunky.request;

/**
 * Created by AdrianIonita on 5/22/2017.
 */
public class LoginRequest {

    private String callerApp;
    private String userName;
    private String password;
    private String socialMediaId;

    public LoginRequest() {
    }

    public String getCallerApp() {
        return callerApp;
    }

    public void setCallerApp(String callerApp) {
        this.callerApp = callerApp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    @Override
    public String toString() {
        return "LoginRequest{" +
                "callerApp='" + callerApp + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", socialMediaId='" + socialMediaId + '\'' +
                '}';
    }
}
