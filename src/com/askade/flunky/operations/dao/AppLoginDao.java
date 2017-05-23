package com.askade.flunky.operations.dao;

import com.askade.flunky.exception.AuthKeyException;
import com.askade.flunky.operations.model.AppLogin;

/**
 * Created by AdrianIonita on 5/16/2017.
 */
public interface AppLoginDao {

    /**
     * @param appLogin
     */
    public String addAppLogin(AppLogin appLogin);


    /**
     * @param appLogin
     * @return
     */
    public AppLogin updateAppLogin(AppLogin appLogin);

    /**
     * @return
     * @param authKey
     */
    public AppLogin getAppLogin(String authKey) throws AuthKeyException;

}
