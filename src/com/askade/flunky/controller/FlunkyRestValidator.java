package com.askade.flunky.controller;

import com.askade.flunky.exception.AuthKeyException;
import com.askade.flunky.operations.service.AppLoginService;
import org.springframework.validation.*;

import java.lang.reflect.Field;

/**
 * Created by AdrianIonita on 5/23/2017.
 */

public class FlunkyRestValidator implements Validator {


    private AppLoginService appLoginService;

    /**
     * @param appLoginService
     */
    public FlunkyRestValidator(AppLoginService appLoginService) {
        this.appLoginService = appLoginService;
    }

    /**
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * @param target
     * @param e
     */
    @Override
    public void validate(Object target, Errors e) {
        String authKey = null;
        ValidationUtils.rejectIfEmpty(e, "authKey", "authKey.empty");
        try {

            Field f = target.getClass().getDeclaredField("authKey"); //NoSuchFieldException
            f.setAccessible(true);
            authKey = (String)f.get(target);
            appLoginService.getAppLogin(authKey);
        } catch (NoSuchFieldException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (AuthKeyException e1) {
            e.rejectValue("authKey", e1.getMessage());
        }
    }
}
