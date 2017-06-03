package com.askade.flunky.crm.serviceImpl;

import com.askade.flunky.crm.dao.*;
import com.askade.flunky.crm.model.*;
import com.askade.flunky.crm.service.ClientEnrolmentService;
import com.askade.flunky.request.ClientEnrolmentRequest;
import com.askade.flunky.utils.FlunkyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by AdrianIonita on 6/3/2017.
 */
@Service
@Transactional
public class ClientEnrolmentServiceImpl implements ClientEnrolmentService{

    @Autowired
    private ClientDao clientDao;
    @Autowired
    private IndividualClientDao individualClientDao;
    @Autowired
    private ClientDocumentDao clientDocumentDao;

    @Autowired
    private ClientCredentialDao clientCredentialDao;

    @Autowired
    private ClientUserDao clientUserDao;
    @Autowired
    private ClientPhoneDao clientPhoneDao;

    @Override
    public Client enrollIndividualClient(ClientEnrolmentRequest clientEnrolmentRequest) {
        Client client = new Client();
        client.setClientName(clientEnrolmentRequest.getLastName() + " " + clientEnrolmentRequest.getFirstName());
        client.setClientCategory(clientEnrolmentRequest.getClientCategoryId());
        client.setClientSubcategory(clientEnrolmentRequest.getClientSubcategoryId());
        client.setClientTypeId(clientEnrolmentRequest.getClientTypeId());
        client = clientDao.addNewClient(client);

        //add individual info
        IndividualClient individualClient = new IndividualClient();
        individualClient.setClientId(client.getClientId());
        individualClient.setBirthDate(clientEnrolmentRequest.getBirthDate());
        individualClient.setFirstName(clientEnrolmentRequest.getFirstName());
        individualClient.setLastName(clientEnrolmentRequest.getLastName());
        individualClient.setNationalIdentifier(clientEnrolmentRequest.getNationalIdentifier());
        individualClient.setSex(clientEnrolmentRequest.getSex());

        individualClientDao.addIndividualClient(individualClient);

        //add the client Document
        if(clientEnrolmentRequest.getSerialNo() != null && !clientEnrolmentRequest.getSerialNo().isEmpty()){
            ClientDocument clientDocument = new ClientDocument();
            clientDocument.setClientId(client.getClientId());
            clientDocument.setDocumentTypeId(clientEnrolmentRequest.getDocumentTypeId());
            clientDocument.setSerialNo(clientEnrolmentRequest.getSerialNo());
            clientDocument.setDocNumber(clientEnrolmentRequest.getDocNumber());
            clientDocument.setIssueDate(clientEnrolmentRequest.getIssueDate());
            clientDocument.setExpiryDate(clientEnrolmentRequest.getExpiryDate());
            clientDocumentDao.addNewClientDocument(clientDocument);
        }

        //add the client phone;
        if(clientEnrolmentRequest.getClientPhone() != null && !clientEnrolmentRequest.getClientPhone().isEmpty()){
            ClientPhone clientPhone = new ClientPhone();
            clientPhone.setClientId(client.getClientId());
            clientPhone.setPhoneTypeId(clientEnrolmentRequest.getPhoneTypeId());
            clientPhone.setPhoneNo(clientEnrolmentRequest.getClientPhone());
            clientPhone.setDateIn(FlunkyUtils.getCurrentDate());
            clientPhoneDao.addClientPhone(clientPhone);
        }

        //add the user and credential
        ClientUser clientUser = new ClientUser();
        clientUser.setUserName(clientEnrolmentRequest.getUsername());
        clientUser.setPassword(clientEnrolmentRequest.getPassword());
        clientUser.setSocialMediaId(clientEnrolmentRequest.getSocialMediaId());
        clientUser.setDateIn(FlunkyUtils.getCurrentDate());
        clientUser = clientUserDao.addClientUser(clientUser);

        ClientCredential clientCredential = new ClientCredential();
        clientCredential.setClientId(client.getClientId());
        clientCredential.setUserId(clientUser.getUserId());
        clientCredential.setDateIn(FlunkyUtils.getCurrentDate());
        clientCredentialDao.addClientCredential(clientCredential);
        return client;
    }
}
