package com.askade.flunky.crm.dao;

import com.askade.flunky.crm.model.ClientUser;
import com.askade.flunky.exception.LoginException;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by AdrianIonita on 4/29/2017.
 */
public interface ClientUserDao {

    /**
     * @param clientUser
     */
    public ClientUser addClientUser(ClientUser clientUser);

    /**
     * @return
     */
    public List<ClientUser> getAllClientUsers();

    /**
     * @param clientUser
     * @return
     */
    public ClientUser updateClientUser(ClientUser clientUser);

    /**
     * @param clientUserId
     * @return
     */
    public ClientUser getClientUser(int clientUserId);

    /**
     * @param userName
     * @return
     */
    public ClientUser getClientUserForUserName(String userName);

    /**
     * @param clientUser
     * @return
     * @throws LoginException
     */
    public Integer loginClientUser(ClientUser clientUser) throws LoginException;
}
