package com.iup.tp.twitup.ihm.controller;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.ihm.components.connexion.IConnexionObserver;
import com.iup.tp.twitup.ihm.components.profil.IProfilObserver;

public class ProfilController extends IController implements IProfilObserver {
    public ProfilController(IDatabase database, EntityManager entityManager) {
        super(database, entityManager);
    }

}
