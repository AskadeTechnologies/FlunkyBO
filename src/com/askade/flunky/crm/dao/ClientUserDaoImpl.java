package com.askade.flunky.crm.dao;

import com.askade.flunky.crm.model.ClientUser;
import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.ParameterMode;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by AdrianIonita on 4/29/2017.
 */
@Repository
public class ClientUserDaoImpl implements ClientUserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private final String sequenceName = "xxptm_users_s";

    /**
     * @param clientUser
     */
    @Override
    public void addClientUser(ClientUser clientUser) {
        ProcedureCall pc = sessionFactory.getCurrentSession().createStoredProcedureCall(this.sequenceName);
        pc.registerParameter(1, BigInteger.class, ParameterMode.OUT);
        clientUser.setUserId((BigInteger)pc.getOutputs().getOutputParameterValue(1));
        sessionFactory.getCurrentSession().saveOrUpdate(clientUser);
    }

    /**
     * @return
     */
    @Override
    public List<ClientUser> getAllClientUsers() {
        return sessionFactory.getCurrentSession().createQuery("from ClientUser order by userId desc").list();
    }

    /**
     * @param clientUser
     * @return
     */
    @Override
    public ClientUser updateClientUser(ClientUser clientUser) {
        sessionFactory.getCurrentSession().update(clientUser);
        return clientUser;
    }

    /**
     * @param clientUserId
     * @return
     */
    @Override
    public ClientUser getClientUser(int clientUserId) {
        return (ClientUser) sessionFactory.getCurrentSession().get(ClientUser.class, clientUserId);
    }
}
