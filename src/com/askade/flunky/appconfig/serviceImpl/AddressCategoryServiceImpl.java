package com.askade.flunky.appconfig.serviceImpl;

import com.askade.flunky.appconfig.dao.AddressCategoryDao;
import com.askade.flunky.appconfig.model.AddressCategory;
import com.askade.flunky.appconfig.service.AddressCategoryService;
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
public class AddressCategoryServiceImpl implements AddressCategoryService<AddressCategory>{
    @Autowired
    private AddressCategoryDao addressCategoryDao;

    @Override
    public AddressCategory findByCode(String code) {
        return (AddressCategory) addressCategoryDao.findByCode(code, AddressCategory.class);
    }

    @Override
    public AddressCategory findById(BigInteger id) {
        return (AddressCategory) addressCategoryDao.findById(id);
    }

    @Override
    public List<AddressCategory> getAllRows() {
        return addressCategoryDao.getAllRows(AddressCategory.class);
    }

    @Override
    public void addRow(AddressCategory row) {
        addressCategoryDao.addRow(row);
    }

    @Override
    public void updateRow(AddressCategory row) {
        addressCategoryDao.updateRow(row);
    }
}
