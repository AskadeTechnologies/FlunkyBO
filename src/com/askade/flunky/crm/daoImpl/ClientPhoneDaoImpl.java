package com.askade.flunky.crm.daoImpl;

import com.askade.flunky.crm.dao.ClientPhoneDao;
import com.askade.flunky.crm.model.ClientPhone;
import com.askade.flunky.kernel.FlunkyGenericDaoImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by AdrianIonita on 6/3/2017.
 */
@Repository
public class ClientPhoneDaoImpl implements ClientPhoneDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ClientPhone addClientPhone(ClientPhone clientPhone) {
        clientPhone.setId(FlunkyGenericDaoImpl.getTableId(sessionFactory, ClientPhone.sequenceName));
        sessionFactory.getCurrentSession().saveOrUpdate(clientPhone);
        return clientPhone;
    }
}
