package com.askade.flunky.crm.daoImpl;

import com.askade.flunky.crm.dao.ClientDao;
import com.askade.flunky.crm.model.Client;
import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.ParameterMode;
import java.math.BigInteger;

/**
 * Created by AdrianIonita on 6/3/2017.
 */
@Repository
public class ClientDaoImpl implements ClientDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Client addNewClient(Client client) {
        //generate clientSequence
        ProcedureCall pc = sessionFactory.getCurrentSession().createStoredProcedureCall(Client.sequenceName);
        pc.registerParameter(1, Integer.class, ParameterMode.OUT);
        Integer clientId = (Integer)pc.getOutputs().getOutputParameterValue(1);

        client.setClientId(clientId);
        sessionFactory.getCurrentSession().saveOrUpdate(client);
        return client;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
