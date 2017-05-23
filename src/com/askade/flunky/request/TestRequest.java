package com.askade.flunky.request;

/**
 * Created by AdrianIonita on 5/23/2017.
 */
public class TestRequest {

    private String authKey;
    private String clientName;

    public TestRequest() {
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
