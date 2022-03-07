package com.iup.tp.twitup.ihm.controller;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.components.twit.ITwitObserver;

public class TwitController extends IController implements ITwitObserver {

    public TwitController(IDatabase database, EntityManager entityManager) {
        super(database, entityManager);
    }

    @Override
    public Boolean isAlreadyFollowedByUser(User currentUser, User twitUser) {
        System.out.println(twitUser.getUserTag());
        System.out.println(currentUser.getUserTag());
        System.out.println(currentUser.isFollowing(twitUser));
        return currentUser.isFollowing(twitUser);
    }

    @Override
    public void followTwitAuthor(User currentUser, User twitUser) {
        currentUser.addFollowing(twitUser.getUserTag());
    }

    @Override
    public void unfollowTwitAuthor(User currentUser, User twitUser) {
        currentUser.removeFollowing(twitUser.getUserTag());
    }
}
