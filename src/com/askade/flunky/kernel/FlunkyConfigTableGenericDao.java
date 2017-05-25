package com.askade.flunky.kernel;

import com.askade.flunky.appconfig.model.DocumentType;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by AdrianIonita on 5/25/2017.
 */
public interface FlunkyConfigTableGenericDao<T> {

    /**
     * @param code
     * @param clazz
     * @return
     */
    public T findByCode(String code, Class<T> clazz);

    /**
     * @param id
     * @return
     */
    public T findById(BigInteger id);

    /**
     * @return
     */
    public List<T> getAllRows(Class<T> clazz);

    /**
     * @param row
     */
    public void addRow(T row);

    /**
     * @param row
     */
    public void updateRow(T row);
}
