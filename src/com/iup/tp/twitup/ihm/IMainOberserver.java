package com.iup.tp.twitup.ihm;

import java.io.File;

public interface IMainOberserver {
    void notifyDirectoryChanged(File file);
    void goToConnexionPage();
    void goToInscriptionPage();
}
