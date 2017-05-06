package com.askade.flunky.appconfig.dao;

import com.askade.flunky.appconfig.model.AppUser;

/**
 * Created by AdrianIonita on 5/1/2017.
 */
public interface AppUserDao {

    public AppUser findByUserName(String userName);
}
