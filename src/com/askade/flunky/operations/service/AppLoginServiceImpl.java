package com.askade.flunky.operations.service;

import com.askade.flunky.exception.AuthKeyException;
import com.askade.flunky.operations.dao.AppLoginDao;
import com.askade.flunky.operations.model.AppLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by AdrianIonita on 5/22/2017.
 */
@Service
@Transactional
public class AppLoginServiceImpl implements AppLoginService {

    @Autowired
    AppLoginDao appLoginDao;

    @Override
    public String addAppLogin(AppLogin appLogin) {
        return appLoginDao.addAppLogin(appLogin);
    }

    @Override
    public AppLogin updateAppLogin(AppLogin appLogin) {
        return appLoginDao.updateAppLogin(appLogin);
    }

    @Override
    public AppLogin getAppLogin(String authKey) throws AuthKeyException {
        return appLoginDao.getAppLogin(authKey);
    }
}
