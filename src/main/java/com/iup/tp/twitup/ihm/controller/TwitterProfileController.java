package com.iup.tp.twitup.ihm.controller;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.IMainOberserver;
import com.iup.tp.twitup.ihm.components.twitterprofile.ITwitterProfileObserver;

public class TwitterProfileController extends IController implements ITwitterProfileObserver {
    protected User currentUser;
    protected IMainOberserver mainController;

    public TwitterProfileController(IDatabase database, EntityManager entityManager, User currentUser, IMainOberserver mainController) {
        super(database, entityManager);
        this.currentUser = currentUser;
        this.mainController = mainController;
    }

    @Override
    public Boolean isAlreadyFollowedByUser(User twitUser) {
        return currentUser.isFollowing(twitUser);
    }

    @Override
    public void followTwitAuthor(User twitUser) {
        this.currentUser.addFollowing(twitUser.getUserTag());
        this.entityManager.sendUser(this.currentUser);
        this.mainController.goToTwitterProfilePage(twitUser.getUserTag());
    }

    @Override
    public void unfollowTwitAuthor(User twitUser) {
        this.currentUser.removeFollowing(twitUser.getUserTag());
        this.entityManager.sendUser(this.currentUser);
        this.mainController.goToTwitterProfilePage(twitUser.getUserTag());

    }

    @Override
    public User getCurrentUser() {
        return this.currentUser;
    }

    @Override
    public int getNbTwitsPostedByUser(User twitterUser) {
        return this.database.getUserTwits(twitterUser).size();
    }

    @Override
    public int getNbFollowersByUser(User twitterUser) {
        return this.database.getFollowersCount(twitterUser);
    }

    @Override
    public User getUserFromTag(String tag) {
        return this.entityManager.getUserFromTag(tag);
    }
}
