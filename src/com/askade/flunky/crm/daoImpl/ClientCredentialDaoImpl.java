package com.askade.flunky.crm.daoImpl;

import com.askade.flunky.crm.dao.ClientCredentialDao;
import com.askade.flunky.crm.model.ClientCredential;
import com.askade.flunky.kernel.FlunkyGenericDaoImpl;
import com.askade.flunky.utils.FlunkyUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
        if(clientCredential.getDateIn() == null){
            clientCredential.setDateIn(FlunkyUtils.getCurrentDate());
        }
        sessionFactory.getCurrentSession().saveOrUpdate(clientCredential);
        return clientCredential;
    }

    @Override
    @SuppressWarnings("JpaQlInspection")
    public ClientCredential findByUserId(Integer userId) {
        List<ClientCredential> clientCredentials = new ArrayList<ClientCredential>();
        clientCredentials = sessionFactory.getCurrentSession()
                .createQuery("from ClientCredential where userId=?")
                .setParameter(0, userId).list();
        if (clientCredentials.size() > 0) {
            for(ClientCredential clientCredential : clientCredentials){
                if(clientCredential.getDateIn().before(FlunkyUtils.getCurrentDate()) || clientCredential.getDateIn().equals(FlunkyUtils.getCurrentDate())){
                    if(clientCredential.getDateOut() == null || (clientCredential.getDateOut() != null && clientCredential.getDateOut().after(FlunkyUtils.getCurrentDate()))){
                        return clientCredential;
                    }
                }
            }
        } else {
            return null;
        }
        return null;
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    public ClientCredential findByClientId(Integer clientId) {
        List<ClientCredential> clientCredentials = new ArrayList<ClientCredential>();
        clientCredentials = sessionFactory.getCurrentSession()
                .createQuery("from ClientCredential where clientId=?")
                .setParameter(0, clientId).list();
        if (clientCredentials.size() > 0) {
            for(ClientCredential clientCredential : clientCredentials){
                if(clientCredential.getDateIn().before(FlunkyUtils.getCurrentDate()) || clientCredential.getDateIn().equals(FlunkyUtils.getCurrentDate())){
                    if(clientCredential.getDateOut() == null || (clientCredential.getDateOut() != null && clientCredential.getDateOut().after(FlunkyUtils.getCurrentDate()))){
                        return clientCredential;
                    }
                }
            }
        } else {
            return null;
        }
        return null;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
