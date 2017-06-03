package com.askade.flunky.operations.serviceImpl;

import com.askade.flunky.appconfig.dao.OperationTypeDao;
import com.askade.flunky.appconfig.model.OperationType;
import com.askade.flunky.operations.dao.OperationDao;
import com.askade.flunky.operations.model.Operation;
import com.askade.flunky.operations.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by AdrianIonita on 6/4/2017.
 */
@Service
@Transactional
public class OperationServiceImpl implements OperationService {
    @Autowired
    private OperationDao operationDao;
    @Autowired
    private OperationTypeDao operationTypeDao;
    @Override
    public Operation getOperation(String voucherNr) {
        return operationDao.getOperation(voucherNr);
    }

    @Override
    public Operation initOperation(Operation operation) {
        return operationDao.initOperation(operation);
    }

    @Override
    public Operation executeOperation(String voucherNr) {
        return operationDao.executeOperation(voucherNr);
    }

    @Override
    public List<Operation> getOngoingOperations() {
        return operationDao.getOngoingOperations();
    }

    @Override
    public List<Operation> getAllOperations() {
        return operationDao.getAllOperations();
    }

    @Override
    public Integer getOperationTypeId(String code) {
        return ((OperationType) operationTypeDao.findByCode(code, OperationType.class)).getId();
    }
}
