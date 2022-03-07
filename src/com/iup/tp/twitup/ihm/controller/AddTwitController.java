package com.iup.tp.twitup.ihm.controller;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.ihm.components.addtwit.IAddTwitObserver;

public class AddTwitController extends IController implements IAddTwitObserver {
    public AddTwitController(IDatabase database, EntityManager entityManager) {
        super(database, entityManager);
    }
}
