package com.iup.tp.twitup.ihm.controller;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.components.inscription.ICreateAccountObserver;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CreateAccountController extends IController implements ICreateAccountObserver {

    public CreateAccountController(IDatabase database, EntityManager entityManager) {
        super(database, entityManager);
    }

    @Override
    public boolean isPasswordValid(String tPassword) {
        return tPassword.length() > 1;
    }

    @Override
    public void notifyCreateAccount(String tTag, String tPassword, String tNom, Set<String> follows, String tAvatar) {
        User newUser = new User(UUID.randomUUID(), tTag, tPassword, tNom, new HashSet<String>(), tAvatar);
        this.entityManager.sendUser(newUser);
    }

    @Override
    public boolean isAccountExist(String tag) {
        return this.entityManager.tagExist(tag);
    }
}
