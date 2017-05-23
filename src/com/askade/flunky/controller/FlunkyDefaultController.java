package com.askade.flunky.controller;

import com.askade.flunky.operations.service.AppLoginService;
import com.askade.flunky.utils.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by AdrianIonita on 5/23/2017.
 */
@Controller
public class FlunkyDefaultController {
    @Autowired
    private AppLoginService appLoginService;

    public FlunkyDefaultController() {
    }

    /**
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new FlunkyRestValidator(appLoginService));
    }

    /**
     * @param objException
     * @return
     */
    @ExceptionHandler( MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(value = org.springframework.http.HttpStatus.BAD_REQUEST)
    protected JsonResponse handleException(MethodArgumentNotValidException objException)
    {
        String[] errCodes = objException.getBindingResult().getFieldError("authKey").getCodes();

        return JsonResponse.forError(errCodes[3]);
    }

    /**
     * @return
     */
    public AppLoginService getAppLoginService() {
        return appLoginService;
    }
}
