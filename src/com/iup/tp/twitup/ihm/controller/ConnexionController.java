package com.iup.tp.twitup.ihm.controller;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.ihm.components.connexion.IConnexionObserver;

public class ConnexionController extends IController implements IConnexionObserver {
    public ConnexionController(IDatabase database, EntityManager entityManager) {
        super(database, entityManager);
    }

    @Override
    public boolean checkConnexion(String tag, String password) {
        return this.entityManager.checkUser(tag, password);
    }
}
