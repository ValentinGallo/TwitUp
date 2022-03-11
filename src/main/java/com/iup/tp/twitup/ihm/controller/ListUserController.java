package com.iup.tp.twitup.ihm.controller;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.ihm.components.listuser.IListUserObserver;

public class ListUserController extends IController implements IListUserObserver {
    public ListUserController(IDatabase database, EntityManager entityManager) {
        super(database, entityManager);
    }

}
