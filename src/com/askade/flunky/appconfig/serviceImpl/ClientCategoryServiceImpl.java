package com.askade.flunky.appconfig.serviceImpl;

import com.askade.flunky.appconfig.dao.ClientCategoryDao;
import com.askade.flunky.appconfig.model.ClientCategory;
import com.askade.flunky.appconfig.service.ClientCategoryService;
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
public class ClientCategoryServiceImpl implements ClientCategoryService<ClientCategory> {
    @Autowired
    private ClientCategoryDao clientCategoryDao;
    @Override
    public ClientCategory findByCode(String code) {
        return (ClientCategory) clientCategoryDao.findByCode(code, ClientCategory.class);
    }

    @Override
    public ClientCategory findById(BigInteger id) {
        return (ClientCategory) clientCategoryDao.findById(id);
    }

    @Override
    public List<ClientCategory> getAllRows() {
        return clientCategoryDao.getAllRows(ClientCategory.class);
    }

    @Override
    public void addRow(ClientCategory row) {
        clientCategoryDao.addRow(row);
    }

    @Override
    public void updateRow(ClientCategory row) {
        clientCategoryDao.updateRow(row);
    }
}
