package com.iup.tp.twitup.ihm.controller;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.ihm.components.twit.ITwitObserver;

public class TwitController extends IController implements ITwitObserver {

    public TwitController(IDatabase database, EntityManager entityManager) {
        super(database, entityManager);
    }
}
