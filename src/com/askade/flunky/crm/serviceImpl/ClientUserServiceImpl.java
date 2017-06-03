package com.askade.flunky.crm.serviceImpl;

import com.askade.flunky.crm.dao.ClientUserDao;
import com.askade.flunky.crm.model.ClientUser;
import com.askade.flunky.crm.service.ClientUserService;
import com.askade.flunky.exception.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by AdrianIonita on 4/29/2017.
 */
@Service
@Transactional
public class ClientUserServiceImpl implements ClientUserService {

    @Autowired
    private ClientUserDao clientUserDao;
    /**
     * @param clientUser
     */
    @Override
    @Transactional
    public void addClientUser(ClientUser clientUser) {
        clientUserDao.addClientUser(clientUser);
    }

    /**
     * @return
     */
    @Override
    @Transactional
    public List<ClientUser> getAllClientUsers() {
        return clientUserDao.getAllClientUsers();
    }

    /**
     * @param clientUser
     * @return
     */
    @Override
    @Transactional
    public ClientUser updateClientUser(ClientUser clientUser) {
        return clientUserDao.updateClientUser(clientUser);
    }

    /**
     * @param clientUserId
     * @return
     */
    @Override
    public ClientUser getClientUser(int clientUserId) {
        return clientUserDao.getClientUser(clientUserId);
    }

    /**
     * @param userName
     * @return
     */
    @Override
    public ClientUser getClientUserForUserName(String userName) {
        return clientUserDao.getClientUserForUserName(userName);
    }

    /**
     * @param clientUser
     * @return
     */
    @Override
    public Integer loginClientUser(ClientUser clientUser) throws LoginException {
        return clientUserDao.loginClientUser(clientUser);
    }
}
