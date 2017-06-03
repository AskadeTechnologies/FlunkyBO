package com.askade.flunky.kernel;

import com.askade.flunky.crm.model.Client;
import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;

import javax.persistence.ParameterMode;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AdrianIonita on 5/25/2017.
 */
@Repository
public class FlunkyGenericDaoImpl<T> implements FlunkyConfigTableGenericDao<T>{
    @Autowired
    private SessionFactory sessionFactory;


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public T findByCode(String code, Class<T> clazz) {
        List<T> configTypes = new ArrayList<T>();
        configTypes = getSessionFactory().getCurrentSession()
                .createQuery("from "+clazz.getName()+" where code=?")
                .setParameter(0, code).list();
        if (configTypes.size() > 0) {
            return configTypes.get(0);
        } else {
            return null;
        }
    }

    @Override
    public T findById(BigInteger id) {
        Class<T> genericType = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        return (T) sessionFactory.getCurrentSession().get(genericType, id);
    }

    @Override
    public List<T> getAllRows(Class<T> clazz) {
        return sessionFactory.getCurrentSession().createQuery("from "+clazz.getName()+" order by id desc").list();
    }

    @Override
    public void addRow(T row) {
        sessionFactory.getCurrentSession().saveOrUpdate(row);
    }

    @Override
    public void updateRow(T row) {
        sessionFactory.getCurrentSession().update(row);
    }

    public static Integer getTableId(SessionFactory sessionFactory, String sequenceName){
        ProcedureCall pc = sessionFactory.getCurrentSession().createStoredProcedureCall(sequenceName);
        pc.registerParameter(1, Integer.class, ParameterMode.OUT);
        Integer tableId = (Integer)pc.getOutputs().getOutputParameterValue(1);
        return tableId;

    }
}
