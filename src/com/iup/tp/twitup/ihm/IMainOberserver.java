package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.datamodel.IDatabaseObserver;

import java.io.File;

public interface IMainOberserver {
    void notifyDirectoryChanged(File file);
}
