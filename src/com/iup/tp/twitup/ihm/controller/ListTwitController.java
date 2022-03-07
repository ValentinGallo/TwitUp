package com.iup.tp.twitup.ihm.controller;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.ihm.components.listTwit.IListTwitObserver;
import com.iup.tp.twitup.ihm.components.profil.IProfilObserver;

public class ListTwitController extends IController implements IListTwitObserver {
    public ListTwitController(IDatabase database, EntityManager entityManager) {
        super(database, entityManager);
    }
}
