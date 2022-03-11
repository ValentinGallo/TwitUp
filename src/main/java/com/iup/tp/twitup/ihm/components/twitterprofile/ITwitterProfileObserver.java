package com.iup.tp.twitup.ihm.components.twitterprofile;

import com.iup.tp.twitup.datamodel.User;

public interface ITwitterProfileObserver {
    Boolean isAlreadyFollowedByUser(User twitUser);

    void followTwitAuthor(User twitUser);

    void unfollowTwitAuthor(User twitUser);

    User getCurrentUser();

    int getNbTwitsPostedByUser(User twitterUser);

    int getNbFollowersByUser(User twitterUser);

    User getUserFromTag(String tag);
}
