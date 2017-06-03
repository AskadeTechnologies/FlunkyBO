package com.askade.flunky.appconfig.serviceImpl;

import com.askade.flunky.appconfig.dao.OperationTypeDao;
import com.askade.flunky.appconfig.model.OperationType;
import com.askade.flunky.appconfig.service.OperationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by AdrianIonita on 6/4/2017.
 */
@Service
@Transactional
public class OperationTypeServiceImpl implements OperationTypeService<OperationType> {
    @Autowired
    private OperationTypeDao operationTypeDao;
    @Override
    public OperationType findByCode(String code) {
        return (OperationType) operationTypeDao.findByCode(code, OperationType.class);
    }

    @Override
    public OperationType findById(BigInteger id) {
        return (OperationType) operationTypeDao.findById(id);
    }

    @Override
    public List<OperationType> getAllRows() {
        return operationTypeDao.getAllRows(OperationType.class);
    }

    @Override
    public void addRow(OperationType row) {
        operationTypeDao.addRow(row);
    }

    @Override
    public void updateRow(OperationType row) {
        operationTypeDao.updateRow(row);
    }
}
