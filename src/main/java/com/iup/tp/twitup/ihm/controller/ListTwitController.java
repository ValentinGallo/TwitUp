package com.iup.tp.twitup.ihm.controller;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.components.listtwit.IListTwitObserver;

import java.util.HashSet;
import java.util.Set;

public class ListTwitController extends IController implements IListTwitObserver {
    public ListTwitController(IDatabase database, EntityManager entityManager) {
        super(database, entityManager);
    }

    @Override
    public Set<Twit> getUserTwits(String userTag) {
        for (User user : this.database.getUsers()) {
            if (("@" + user.getUserTag()).equals(userTag)) {
                return this.database.getUserTwits(user);
            }
        }

        return new HashSet<>();
    }
}
