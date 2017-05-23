package com.askade.flunky.crm.dao;

import com.askade.flunky.crm.model.ClientUser;
import com.askade.flunky.exception.LoginException;
import com.askade.flunky.utils.FlunkyUtils;
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

    private final String sequenceName = "xxflk_users_s";

    /**
     * @param clientUser
     */
    @Override
    public void addClientUser(ClientUser clientUser) {
        ProcedureCall pc = sessionFactory.getCurrentSession().createStoredProcedureCall(this.sequenceName);
        pc.registerParameter(1, BigInteger.class, ParameterMode.OUT);
        clientUser.setUserId((BigInteger)pc.getOutputs().getOutputParameterValue(1));
        if(clientUser.getDateIn() == null) {
            clientUser.setDateIn(FlunkyUtils.getCurrentDate());
        }
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

    /**
     * @param userName
     * @return
     */
    public ClientUser getClientUserForUserName(String userName) {
        List<ClientUser> clientUserList = sessionFactory.getCurrentSession().createQuery("from ClientUser where userName = ? order by userId desc").setParameter(0, userName).list();
        if(clientUserList != null && clientUserList.size() > 0){
            return clientUserList.get(0);
        }
        return null;
    }

    /**
     * @param clientUser
     * @return
     */
    public BigInteger loginClientUser(ClientUser clientUser) throws LoginException {
        ClientUser foundClientUser = getClientUserForUserName(clientUser.getUserName());
        if(foundClientUser == null){
            throw new LoginException("USERNAME_NOT_FOUND");
        }
        if(foundClientUser.getDateIn().after(FlunkyUtils.getCurrentDate())){
            throw new LoginException("USERNAME_NOT_VALID");
        }
        if(foundClientUser.getDateOut() != null && foundClientUser.getDateOut().before(FlunkyUtils.getCurrentDate())){
            throw new LoginException("USERNAME_NOT_VALID");
        }
        if(!foundClientUser.getPassword().equals(clientUser.getPassword())){
            throw new LoginException("PASSWORD_INCORRECT");
        }

        return foundClientUser.getUserId();
    }
}
