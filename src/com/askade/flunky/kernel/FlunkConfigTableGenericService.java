package com.askade.flunky.kernel;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by AdrianIonita on 5/25/2017.
 */
public interface FlunkConfigTableGenericService<T> {

    /**
     * @param code
     * @return
     */
    public T findByCode(String code);

    /**
     * @param id
     * @return
     */
    public T findById(BigInteger id);

    /**
     * @return
     */
    public List<T> getAllRows();

    /**
     * @param row
     */
    public void addRow(T row);

    /**
     * @param row
     */
    public void updateRow(T row);
}
