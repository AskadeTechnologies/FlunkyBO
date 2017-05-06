package com.askade.flunky.appconfig.dao;

import com.askade.flunky.appconfig.model.DocumentType;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by AdrianIonita on 5/1/2017.
 */
public interface DocumentTypeDao {

    /**
     * @param code
     * @return
     */
    public DocumentType findByCode(String code);

    /**
     * @param documentId
     * @return
     */
    public DocumentType findById(BigInteger documentId);

    /**
     * @return
     */
    public List<DocumentType> getAllDocumentTypes();

    /**
     * @param documentType
     */
    public void addDocumentType(DocumentType documentType);

    /**
     * @param documentType
     */
    public void updateDocumentType(DocumentType documentType);
}
