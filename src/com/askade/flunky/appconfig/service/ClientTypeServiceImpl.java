package com.askade.flunky.appconfig.service;

import com.askade.flunky.appconfig.dao.ClientTypeDao;
import com.askade.flunky.appconfig.model.ClientType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by AdrianIonita on 5/25/2017.
 */
@Service
@Transactional
public class ClientTypeServiceImpl implements ClientTypeService<ClientType> {
    @Autowired
    private ClientTypeDao clientTypeDao;
    @Override
    public ClientType findByCode(String code) {
        return (ClientType) clientTypeDao.findByCode(code, ClientType.class);
    }

    @Override
    public ClientType findById(BigInteger id) {
        return (ClientType) clientTypeDao.findById(id);
    }

    @Override
    public List getAllRows() {
        return clientTypeDao.getAllRows(ClientType.class);
    }

    @Override
    public void addRow(ClientType row) {
        clientTypeDao.addRow(row);
    }

    @Override
    public void updateRow(ClientType row) {
        clientTypeDao.updateRow(row);
    }
}
