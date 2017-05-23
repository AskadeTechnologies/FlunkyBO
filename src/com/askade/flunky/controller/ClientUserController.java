package com.askade.flunky.controller;

import com.askade.flunky.crm.model.ClientUser;
import com.askade.flunky.crm.service.ClientUserService;
import com.askade.flunky.exception.LoginException;
import com.askade.flunky.operations.model.AppLogin;
import com.askade.flunky.request.LoginRequest;
import com.askade.flunky.request.TestRequest;
import com.askade.flunky.response.LoginResponse;
import com.askade.flunky.utils.JsonResponse;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by AdrianIonita on 4/29/2017.
 */
@Controller
public class ClientUserController extends FlunkyDefaultController {
    private static final Logger logger = Logger.getLogger(ClientUserController.class);

    public ClientUserController() {
    }

    @Autowired
    private ClientUserService clientUserService;

    /**
     * @param clientUser
     * @return
     */
    @RequestMapping(value = "/addNewClientUser", method = RequestMethod.POST)
    public @ResponseBody JsonResponse addNewClientUser(@RequestBody ClientUser clientUser){
        clientUserService.addClientUser(clientUser);
        return JsonResponse.forSuccess();
    }

    /**
     * @return
     */
    @RequestMapping(value = "/getClientUsers", method = RequestMethod.GET)
    public @ResponseBody JsonResponse getAppUsers(){
        return JsonResponse.forSuccess(clientUserService.getAllClientUsers());
    }

    /**
     * @param loginRequest
     * @return
     */
    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public @ResponseBody JsonResponse login(@RequestBody LoginRequest loginRequest){
        try {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setClientId(clientUserService.loginClientUser(new ClientUser(loginRequest.getUserName(), loginRequest.getPassword(), loginRequest.getSocialMediaId())));
            loginResponse.setAuthKey(getAppLoginService().addAppLogin(new AppLogin(loginRequest.getCallerApp())));
            return JsonResponse.forSuccess(loginResponse);
        } catch (LoginException e) {
            return JsonResponse.forError(e.getMessage());
        }
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public @ResponseBody JsonResponse test (@Valid @RequestBody TestRequest request){

        return JsonResponse.forSuccess();
    }


}
