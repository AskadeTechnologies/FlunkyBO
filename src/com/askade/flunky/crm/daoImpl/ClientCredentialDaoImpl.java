package com.askade.flunky.crm.daoImpl;

import com.askade.flunky.crm.dao.ClientCredentialDao;
import com.askade.flunky.crm.model.ClientCredential;
import com.askade.flunky.kernel.FlunkyGenericDaoImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by AdrianIonita on 6/3/2017.
 */
@Repository
public class ClientCredentialDaoImpl implements ClientCredentialDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public ClientCredential addClientCredential(ClientCredential clientCredential) {
        clientCredential.setId(FlunkyGenericDaoImpl.getTableId(sessionFactory, ClientCredential.sequenceName));
        sessionFactory.getCurrentSession().saveOrUpdate(clientCredential);
        return clientCredential;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
