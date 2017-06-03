package com.askade.flunky.response;

/**
 * Created by AdrianIonita on 5/22/2017.
 */
public class LoginResponse {
    private String authKey;
    private Integer clientId;

    public LoginResponse() {
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "authKey='" + authKey + '\'' +
                ", clientId=" + clientId +
                '}';
    }
}
