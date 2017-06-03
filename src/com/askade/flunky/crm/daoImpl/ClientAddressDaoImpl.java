package com.askade.flunky.crm.daoImpl;

import com.askade.flunky.crm.dao.ClientAddressDao;
import com.askade.flunky.crm.model.ClientAddress;
import com.askade.flunky.kernel.FlunkyGenericDaoImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by AdrianIonita on 6/3/2017.
 */
@Repository
public class ClientAddressDaoImpl implements ClientAddressDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ClientAddress addClientAddress(ClientAddress clientAddress) {
        clientAddress.setId(FlunkyGenericDaoImpl.getTableId(sessionFactory, ClientAddress.sequenceName));
        sessionFactory.getCurrentSession().saveOrUpdate(clientAddress);
        return clientAddress;
    }
}
