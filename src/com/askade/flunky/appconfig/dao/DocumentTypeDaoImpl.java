package com.askade.flunky.appconfig.dao;

import com.askade.flunky.appconfig.model.DocumentType;
import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.ParameterMode;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AdrianIonita on 5/1/2017.
 */
@Repository
public class DocumentTypeDaoImpl implements DocumentTypeDao {

    private final static String sequenceName = "xxflk_document_types_s";

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public DocumentType findByCode(String code) {
        List<DocumentType> documentTypes = new ArrayList<DocumentType>();
        documentTypes = getSessionFactory().getCurrentSession()
                .createQuery("from DocumentType where code=?")
                .setParameter(0, code).list();
        if (documentTypes.size() > 0) {
            return documentTypes.get(0);
        } else {
            return null;
        }
    }

    @Override
    public DocumentType findById(BigInteger documentId) {
        return (DocumentType) sessionFactory.getCurrentSession().get(DocumentType.class, documentId);
    }

    @Override
    public List<DocumentType> getAllDocumentTypes() {
        return sessionFactory.getCurrentSession().createQuery("from DocumentType order by id desc").list();
    }

    @Override
    public void addDocumentType(DocumentType documentType) {
        ProcedureCall pc = sessionFactory.getCurrentSession().createStoredProcedureCall(this.sequenceName);
        pc.registerParameter(1, BigInteger.class, ParameterMode.OUT);
        documentType.setId((BigInteger)pc.getOutputs().getOutputParameterValue(1));
        sessionFactory.getCurrentSession().saveOrUpdate(documentType);
    }

    @Override
    public void updateDocumentType(DocumentType documentType) {
        sessionFactory.getCurrentSession().update(documentType);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
