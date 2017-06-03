package com.askade.flunky.operations.daoImpl;

import com.askade.flunky.exception.AuthKeyException;
import com.askade.flunky.operations.dao.AppLoginDao;
import com.askade.flunky.operations.model.AppLogin;
import com.askade.flunky.utils.FlunkyUtils;
import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.ParameterMode;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by AdrianIonita on 5/16/2017.
 */
@Repository
public class AppLoginDaoImpl implements AppLoginDao {
    @Autowired
    private SessionFactory sessionFactory;

    private final String sequenceName = "xxflk_app_login_s";
    private final String authSequence = "xxflk_auth_key_s";

    @Override
    public String addAppLogin(AppLogin appLogin) {
        ProcedureCall appLoginId = sessionFactory.getCurrentSession().createStoredProcedureCall(this.sequenceName);
        appLoginId.registerParameter(1, Integer.class, ParameterMode.OUT);
        appLogin.setId((Integer)appLoginId.getOutputs().getOutputParameterValue(1));
        ProcedureCall authKeySeq = sessionFactory.getCurrentSession().createStoredProcedureCall(this.authSequence);
        authKeySeq.registerParameter(1, String.class, ParameterMode.OUT);
        appLogin.setAuthKey((String)authKeySeq.getOutputs().getOutputParameterValue(1));
        if(appLogin.getStartDate() == null) {
            appLogin.setStartDate(FlunkyUtils.getCurrentDate());
        }
        sessionFactory.getCurrentSession().saveOrUpdate(appLogin);
        return appLogin.getAuthKey();
    }

    @Override
    public AppLogin updateAppLogin(AppLogin appLogin) {
        return null;
    }

    /**
     * @param authKey
     * @return
     * @throws AuthKeyException
     */
    @Override
    public AppLogin getAppLogin(String authKey) throws AuthKeyException {
        List<AppLogin> appLoginList = sessionFactory.getCurrentSession().createQuery("from AppLogin where authKey = ?").setParameter(0, authKey).list();
        if(appLoginList != null && appLoginList.size() > 0){
            AppLogin appLogin = appLoginList.get(0);
            if(!appLogin.getStartDate().before(FlunkyUtils.getCurrentDate()) && !appLogin.getStartDate().equals(FlunkyUtils.getCurrentDate())){
                throw new AuthKeyException("AUTH_KEY_NOT_VALID");
            }
            if(appLogin.getEndDate() != null &&  (!appLogin.getEndDate().after(FlunkyUtils.getCurrentDate()) && !appLogin.getEndDate().equals(FlunkyUtils.getCurrentDate()))){
                throw new AuthKeyException("AUTH_KEY_NOT_VALID");
            }
            return appLogin;
        }else{
            throw new AuthKeyException("AUTH_KEY_NOTFOUND");
        }
    }
}
