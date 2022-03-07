package com.iup.tp.twitup.ihm.controller;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.components.addtwit.IAddTwitObserver;

public class AddTwitController extends IController implements IAddTwitObserver {
    private User connectedUser;
    public AddTwitController(IDatabase database, EntityManager entityManager, User user) {
        super(database, entityManager);
        this.connectedUser = user;
    }

    @Override
    public Boolean isTwitOk(String message) {
        return message.length() <= 250 && message.length() != 0;
    }

    @Override
    public void twit(String message) {
        this.entityManager.sendTwit(new Twit(connectedUser, message));
    }
}
