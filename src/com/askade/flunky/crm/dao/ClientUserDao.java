package com.askade.flunky.crm.dao;

import com.askade.flunky.crm.model.ClientUser;

import java.util.List;

/**
 * Created by AdrianIonita on 4/29/2017.
 */
public interface ClientUserDao {

    /**
     * @param clientUser
     */
    public void addClientUser(ClientUser clientUser);

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
}
