package com.askade.flunky.appconfig.serviceImpl;

import com.askade.flunky.appconfig.dao.AddressTypeDao;
import com.askade.flunky.appconfig.model.AddressType;
import com.askade.flunky.appconfig.service.AddressTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by AdrianIonita on 6/2/2017.
 */
@Service
@Transactional
public class AddressTypeServiceImpl implements AddressTypeService<AddressType> {
    @Autowired
    private AddressTypeDao addressTypeDao;
    @Override
    public AddressType findByCode(String code) {
        return (AddressType) addressTypeDao.findByCode(code, AddressType.class);
    }

    @Override
    public AddressType findById(BigInteger id) {
        return (AddressType) addressTypeDao.findById(id);
    }

    @Override
    public List<AddressType> getAllRows() {
        return addressTypeDao.getAllRows(AddressType.class);
    }

    @Override
    public void addRow(AddressType row) {
        addressTypeDao.addRow(row);
    }

    @Override
    public void updateRow(AddressType row) {
        addressTypeDao.updateRow(row);
    }
}
