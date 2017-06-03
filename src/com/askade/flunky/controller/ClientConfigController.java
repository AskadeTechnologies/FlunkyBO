package com.askade.flunky.controller;

import com.askade.flunky.appconfig.model.*;
import com.askade.flunky.appconfig.service.*;
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

import java.util.List;

/**
 * Created by AdrianIonita on 5/1/2017.
 */
@Controller
@RequestMapping("/config/client")
public class ClientConfigController {
    private static final Logger logger = Logger.getLogger(ClientConfigController.class);

    @Autowired
    private DocumentTypeService documentTypeService;

    @Autowired
    private ClientCategoryService<ClientCategory> clientCategoryService;

    @Autowired
    private ClientSubcategoryService<ClientSubcategory> clientSubcategoryService;

    @Autowired
    private ClientTypeService<ClientType> clientTypeService;

    @Autowired
    private AddressCategoryService<AddressCategory> addressCategoryService;

    @Autowired
    private AddressTypeService<AddressType> addressTypeService;

    @Autowired
    private PhoneTypeService<PhoneType> phoneTypeService;

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

    /**
     * @return
     */
    @RequestMapping(value = "/getClientCategories", method = RequestMethod.GET)
    public @ResponseBody JsonResponse getClientCategories(){
        return JsonResponse.forSuccess(clientCategoryService.getAllRows());
    }

    /**
     * @return
     */
    @RequestMapping(value = "/getClientCategoriesLov", method = RequestMethod.GET)
    public @ResponseBody JsonResponse getClientCategoriesLov(){
        List<ClientCategory> clientCategoryList = clientCategoryService.getAllRows();
        String response = "";
        if(clientCategoryList != null && clientCategoryList.size() > 0){
            for(ClientCategory clientCategory : clientCategoryList){
                if(response == null || response.isEmpty()){

                }else{
                    response = response+";";
                }
                response = response + clientCategory.getId().toString()+":"+clientCategory.getDescription();
            }
        }
        return JsonResponse.forSuccess(response);
    }

    /**
     * @param clientCategory
     * @return
     */
    @Transactional
    @RequestMapping(value = "/postClientCategory", method = RequestMethod.POST)
    public @ResponseBody JsonResponse postClientCategory(@RequestBody ClientCategory clientCategory){
        clientCategoryService.addRow(clientCategory);
        return JsonResponse.forSuccess();
    }

    /**
     * @return
     */
    @RequestMapping(value = "/getClientSubcategories", method = RequestMethod.GET)
    public @ResponseBody JsonResponse getClientSubcategories(){
        return JsonResponse.forSuccess(clientSubcategoryService.getAllRows());
    }

    /**
     * @param clientSubcategory
     * @return
     */
    @Transactional
    @RequestMapping(value = "/postClientSubcategory", method = RequestMethod.POST)
    public @ResponseBody JsonResponse postClientSubcategory(@RequestBody ClientSubcategory clientSubcategory){
        clientSubcategoryService.addRow(clientSubcategory);
        return JsonResponse.forSuccess();
    }

    /**
     * @param clientType
     * @return
     */
    @Transactional
    @RequestMapping(value = "/postNewClientType", method = RequestMethod.POST)
    public @ResponseBody JsonResponse postNewClientType(@RequestBody ClientType clientType){
        clientTypeService.addRow(clientType);
        return JsonResponse.forSuccess();
    }

    /**
     * @return
     */
    @RequestMapping(value = "/getClientTypes", method = RequestMethod.GET)
    public @ResponseBody JsonResponse getClientTypes(){
        return JsonResponse.forSuccess(clientTypeService.getAllRows());
    }

    /**
     * @return
     */
    @RequestMapping(value = "/getAddressCategories", method = RequestMethod.GET)
    public @ResponseBody JsonResponse getAddressCategories(){
        return JsonResponse.forSuccess(addressCategoryService.getAllRows());
    }

    /**
     * @return
     */
    @RequestMapping(value = "/getAddressCategoriesLov", method = RequestMethod.GET)
    public @ResponseBody JsonResponse getAddressCategoriesLov(){
        List<AddressCategory> addressCategoriesList = addressCategoryService.getAllRows();
        String response = "";
        if(addressCategoriesList != null && addressCategoriesList.size() > 0){
            for(AddressCategory addressCategory : addressCategoriesList){
                if(response == null || response.isEmpty()){

                }else{
                    response = response+";";
                }
                response = response + addressCategory.getId().toString()+":"+addressCategory.getDescription();
            }
        }
        return JsonResponse.forSuccess(response);
    }

    /**
     * @param addressCategory
     * @return
     */
    @Transactional
    @RequestMapping(value = "/postAddressCategory", method = RequestMethod.POST)
    public @ResponseBody JsonResponse postAddressCategory(@RequestBody AddressCategory addressCategory){
        addressCategoryService.addRow(addressCategory);
        return JsonResponse.forSuccess();
    }


    /**
     * @return
     */
    @RequestMapping(value = "/getAddressTypes", method = RequestMethod.GET)
    public @ResponseBody JsonResponse getAddressTypes(){
        return JsonResponse.forSuccess(addressTypeService.getAllRows());
    }

    /**
     * @param addressType
     * @return
     */
    @Transactional
    @RequestMapping(value = "/postAddressType", method = RequestMethod.POST)
    public @ResponseBody JsonResponse postAddressType(@RequestBody AddressType addressType){
        addressTypeService.addRow(addressType);
        return JsonResponse.forSuccess();
    }

    /**
     * @return
     */
    @RequestMapping(value = "/getPhoneTypes", method = RequestMethod.GET)
    public @ResponseBody JsonResponse getPhoneTypes(){
        return JsonResponse.forSuccess(phoneTypeService.getAllRows());
    }

    /**
     * @param phoneType
     * @return
     */
    @Transactional
    @RequestMapping(value = "/postPhoneType", method = RequestMethod.POST)
    public @ResponseBody JsonResponse postPhoneType(@RequestBody PhoneType phoneType){
        phoneTypeService.addRow(phoneType);
        return JsonResponse.forSuccess();
    }
}
