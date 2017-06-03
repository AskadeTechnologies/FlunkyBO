package com.askade.flunky.operations.service;

import com.askade.flunky.operations.model.Operation;

import java.util.List;

/**
 * Created by AdrianIonita on 6/4/2017.
 */
public interface OperationService {

    public Integer getOperationTypeId(String code);

    public Operation getOperation(String voucherNr);

    public Operation initOperation(Operation operation);

    public Operation executeOperation(String voucherNr);

    public List<Operation> getOngoingOperations();

    public List<Operation> getAllOperations();
}
