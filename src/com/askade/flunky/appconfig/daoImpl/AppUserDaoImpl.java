package com.askade.flunky.appconfig.daoImpl;

import com.askade.flunky.appconfig.dao.AppUserDao;
import com.askade.flunky.appconfig.model.AppUser;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AdrianIonita on 5/1/2017.
 */
@Repository
public class AppUserDaoImpl implements AppUserDao {
    @Autowired
    private SessionFactory sessionFactory;


    /**
     * @param userName
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public AppUser findByUserName(String userName) {
        List<AppUser> users = new ArrayList<AppUser>();
        users = getSessionFactory().getCurrentSession()
                .createQuery("from AppUser where userName=?")
                .setParameter(0, userName).list();
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
