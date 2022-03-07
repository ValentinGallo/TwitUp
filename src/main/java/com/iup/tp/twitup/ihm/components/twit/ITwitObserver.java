package com.iup.tp.twitup.ihm.components.twit;

import com.iup.tp.twitup.datamodel.User;

public interface ITwitObserver {
    Boolean isAlreadyFollowedByUser(User twitUser);

    void followTwitAuthor(User twitUser);

    void unfollowTwitAuthor(User twitUser);

    User getCurrentUser();
}
