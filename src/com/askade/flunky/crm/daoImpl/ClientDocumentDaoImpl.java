package com.askade.flunky.crm.daoImpl;

import com.askade.flunky.crm.dao.ClientDocumentDao;
import com.askade.flunky.crm.model.ClientDocument;
import com.askade.flunky.kernel.FlunkyGenericDaoImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by AdrianIonita on 6/3/2017.
 */
@Repository
public class ClientDocumentDaoImpl implements ClientDocumentDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public ClientDocument addNewClientDocument(ClientDocument clientDocument) {
        clientDocument.setId(FlunkyGenericDaoImpl.getTableId(sessionFactory,ClientDocument.sequenceName));
        sessionFactory.getCurrentSession().saveOrUpdate(clientDocument);
        return clientDocument;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
