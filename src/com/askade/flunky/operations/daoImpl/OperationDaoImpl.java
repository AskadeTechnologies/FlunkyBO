package com.askade.flunky.operations.daoImpl;

import com.askade.flunky.appconfig.model.DocumentType;
import com.askade.flunky.operations.dao.OperationDao;
import com.askade.flunky.operations.model.Operation;
import com.askade.flunky.utils.FlunkyUtils;
import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.ParameterMode;
import java.util.List;

/**
 * Created by AdrianIonita on 6/4/2017.
 */
@Repository
public class OperationDaoImpl implements OperationDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Operation getOperation(String voucherNr) {
        return (Operation) sessionFactory.getCurrentSession().get(Operation.class, voucherNr);
    }

    @Override
    public Operation initOperation(Operation operation) {
        ProcedureCall voucherNrSeq = sessionFactory.getCurrentSession().createStoredProcedureCall(Operation.sequenceName);
        voucherNrSeq.registerParameter(1, String.class, ParameterMode.OUT);
        operation.setVoucherNr((String)voucherNrSeq.getOutputs().getOutputParameterValue(1));
        operation.setDateIn(FlunkyUtils.getCurrentDate());
        operation.setStatus(Operation.OperationStatus.INIT);
        sessionFactory.getCurrentSession().saveOrUpdate(operation);
        return operation;
    }

    @Override
    public Operation executeOperation(String voucherNr) {
        Operation operation = null;
        operation = getOperation(voucherNr);
        operation.setDateOut(FlunkyUtils.getCurrentDate());
        operation.setStatus(Operation.OperationStatus.DONE);
        sessionFactory.getCurrentSession().update(operation);
        return operation;
    }

    @Override
    public List<Operation> getOngoingOperations() {
        return null;
    }

    @Override
    public List<Operation> getAllOperations() {
        return sessionFactory.getCurrentSession().createQuery("from Operation order by dateIn desc").list();
    }
}
