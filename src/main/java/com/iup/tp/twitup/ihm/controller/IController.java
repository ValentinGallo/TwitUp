package com.iup.tp.twitup.ihm.controller;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;

public abstract class IController {
    public IDatabase database;
    public EntityManager entityManager;

    protected IController(IDatabase database, EntityManager entityManager) {
        this.database = database;
        this.entityManager = entityManager;
    }
}
