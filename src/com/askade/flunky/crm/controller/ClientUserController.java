package com.askade.flunky.crm.controller;

import com.askade.flunky.crm.model.ClientUser;
import com.askade.flunky.crm.service.ClientUserService;
import com.askade.flunky.utils.JsonResponse;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by AdrianIonita on 4/29/2017.
 */
@Controller
public class ClientUserController {
    private static final Logger logger = Logger.getLogger(ClientUserController.class);

    public ClientUserController() {
    }

    @Autowired
    private ClientUserService clientUserService;

    @RequestMapping(value = "/addNewClientUser", method = RequestMethod.POST)
    public @ResponseBody JsonResponse addNewClientUser(@RequestBody ClientUser clientUser){
        clientUserService.addClientUser(clientUser);
        return JsonResponse.forSuccess();
    }

    @RequestMapping(value = "/getClientUsers", method = RequestMethod.GET)
    public @ResponseBody JsonResponse getAppUsers(){
        return JsonResponse.forSuccess(clientUserService.getAllClientUsers());
    }
}
