package com.askade.flunky.appconfig.serviceImpl;

import com.askade.flunky.appconfig.dao.ClientSubcategoryDao;
import com.askade.flunky.appconfig.model.ClientSubcategory;
import com.askade.flunky.appconfig.service.ClientSubcategoryService;
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
public class ClientSubcategoryServiceImpl implements ClientSubcategoryService<ClientSubcategory> {
    @Autowired
    private ClientSubcategoryDao clientSubcategoryDao;

    @Override
    public ClientSubcategory findByCode(String code) {
        return (ClientSubcategory) clientSubcategoryDao.findByCode(code, ClientSubcategory.class);
    }

    @Override
    public ClientSubcategory findById(BigInteger id) {
        return (ClientSubcategory) clientSubcategoryDao.findById(id);
    }

    @Override
    public List<ClientSubcategory> getAllRows() {
        return clientSubcategoryDao.getAllRows(ClientSubcategory.class);
    }

    @Override
    public void addRow(ClientSubcategory row) {
        clientSubcategoryDao.addRow(row);
    }

    @Override
    public void updateRow(ClientSubcategory row) {
        clientSubcategoryDao.updateRow(row);
    }
}
