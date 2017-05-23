package com.askade.flunky.controller;

import com.askade.flunky.appconfig.model.DocumentType;
import com.askade.flunky.appconfig.service.DocumentTypeService;
import com.askade.flunky.crm.model.ClientUser;
import com.askade.flunky.crm.service.ClientUserService;
import com.askade.flunky.utils.JsonResponse;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by AdrianIonita on 5/1/2017.
 */
@Controller
@RequestMapping("/config/client")
public class ClientConfigController {
    private static final Logger logger = Logger.getLogger(ClientConfigController.class);

    @Autowired
    private DocumentTypeService documentTypeService;

    /**
     * @return
     */
    @RequestMapping(value = "/getDocumentTypes", method = RequestMethod.GET)
    public @ResponseBody JsonResponse getDocumentTypes(){
        return JsonResponse.forSuccess(documentTypeService.getAllDocumentTypes());
    }

    /**
     * @param documentType
     * @return
     */
    @Transactional
    @RequestMapping(value = "/postNewDocumentType", method = RequestMethod.POST)
    public @ResponseBody JsonResponse postNewDocumentType(@RequestBody DocumentType documentType){
        documentTypeService.addDocumentType(documentType);
        return JsonResponse.forSuccess();
    }

}
