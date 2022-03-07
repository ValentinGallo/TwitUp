package com.iup.tp.twitup.ihm.controller;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.components.twit.ITwitObserver;

public class TwitController extends IController implements ITwitObserver {

    protected User currentUser;

    public TwitController(IDatabase database, EntityManager entityManager, User currentUser) {
        super(database, entityManager);
        this.currentUser = currentUser;
    }

    @Override
    public Boolean isAlreadyFollowedByUser(User twitUser) {
        return currentUser.isFollowing(twitUser);
    }

    @Override
    public void followTwitAuthor(User twitUser) {
        this.currentUser.addFollowing(twitUser.getUserTag());
        this.entityManager.sendUser(this.currentUser);
    }

    @Override
    public void unfollowTwitAuthor(User twitUser) {
        this.currentUser.removeFollowing(twitUser.getUserTag());
        this.entityManager.sendUser(this.currentUser);
    }

    @Override
    public User getCurrentUser() {
        return this.currentUser;
    }
}
