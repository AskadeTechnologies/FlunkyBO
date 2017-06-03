package com.askade.flunky.appconfig.serviceImpl;

import com.askade.flunky.appconfig.dao.PhoneTypeDao;
import com.askade.flunky.appconfig.model.PhoneType;
import com.askade.flunky.appconfig.service.PhoneTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by AdrianIonita on 6/3/2017.
 */
@Service
@Transactional
public class PhoneTypeServiceImpl implements PhoneTypeService<PhoneType> {
    @Autowired
    private PhoneTypeDao phoneTypeDao;
    @Override
    public PhoneType findByCode(String code) {
        return (PhoneType) phoneTypeDao.findByCode(code, PhoneType.class);
    }

    @Override
    public PhoneType findById(BigInteger id) {
        return (PhoneType) phoneTypeDao.findById(id);
    }

    @Override
    public List<PhoneType> getAllRows() {
        return phoneTypeDao.getAllRows(PhoneType.class);
    }

    @Override
    public void addRow(PhoneType row) {
        phoneTypeDao.addRow(row);
    }

    @Override
    public void updateRow(PhoneType row) {
        phoneTypeDao.updateRow(row);
    }
}
