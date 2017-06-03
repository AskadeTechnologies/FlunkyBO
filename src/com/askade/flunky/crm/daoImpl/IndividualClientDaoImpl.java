package com.askade.flunky.crm.daoImpl;

import com.askade.flunky.crm.dao.IndividualClientDao;
import com.askade.flunky.crm.model.IndividualClient;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * Created by AdrianIonita on 6/3/2017.
 */
@Repository
public class IndividualClientDaoImpl implements IndividualClientDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public IndividualClient addIndividualClient(IndividualClient individualClient) {
        sessionFactory.getCurrentSession().saveOrUpdate(individualClient);
        return individualClient;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
