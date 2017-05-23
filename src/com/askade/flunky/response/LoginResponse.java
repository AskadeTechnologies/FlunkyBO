package com.askade.flunky.response;

import java.math.BigInteger;

/**
 * Created by AdrianIonita on 5/22/2017.
 */
public class LoginResponse {
    private String authKey;
    private BigInteger clientId;

    public LoginResponse() {
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public BigInteger getClientId() {
        return clientId;
    }

    public void setClientId(BigInteger clientId) {
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
