package com.askade.flunky.crm.service;

import com.askade.flunky.crm.model.Client;
import com.askade.flunky.request.ClientEnrolmentRequest;

/**
 * Created by AdrianIonita on 6/3/2017.
 */
public interface ClientEnrolmentService {

    public Client enrollIndividualClient(ClientEnrolmentRequest clientEnrolmentRequest);

}
