package com.iup.tp.twitup.ihm.components.twit;

import com.iup.tp.twitup.datamodel.User;

public interface ITwitObserver {
    Boolean isAlreadyFollowedByUser(User currentUser, User twitUser);
    void followTwitAuthor(User currentUser, User twitUser);
    void unfollowTwitAuthor(User currentUser, User twitUser);
}
