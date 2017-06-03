package com.askade.flunky.controller;

import com.askade.flunky.appconfig.model.OperationType;
import com.askade.flunky.appconfig.service.OperationTypeService;
import com.askade.flunky.utils.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by AdrianIonita on 6/4/2017.
 */
@Controller
@RequestMapping("/config/operation")
public class OperationConfigController {

    @Autowired
    private OperationTypeService operationTypeService;

    /**
     * @return
     */
    @RequestMapping(value = "/getOperationTypes", method = RequestMethod.GET)
    public @ResponseBody JsonResponse getOperationTypes(){
        return JsonResponse.forSuccess(operationTypeService.getAllRows());
    }

    /**
     * @param operationType
     * @return
     */
    @RequestMapping(value = "/addOperationType", method = RequestMethod.POST)
    public @ResponseBody JsonResponse addOperationType(@RequestBody OperationType operationType){
        operationTypeService.addRow(operationType);
        return JsonResponse.forSuccess();
    }
}
