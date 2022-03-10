package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.User;

import java.io.File;

public interface IMainOberserver extends IDatabaseObserver {
    void notifyDirectoryChanged(File file);

    void goToConnexionPage();

    void goToInscriptionPage();

    void goToProfilPage();

    void deconnectUser();

    void goToListUserPage();

    void goToListTwitPage();

    void goToAddTwitPage();

    void goToTwitterProfilePage(User twitterUser);
}
