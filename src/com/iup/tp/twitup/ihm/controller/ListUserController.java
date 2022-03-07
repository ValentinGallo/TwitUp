package com.iup.tp.twitup.ihm.controller;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.components.listUser.IListUserObserver;

import java.util.Set;

public class ListUserController extends IController implements IListUserObserver {
    public ListUserController(IDatabase database, EntityManager entityManager) {
        super(database, entityManager);
    }
}
