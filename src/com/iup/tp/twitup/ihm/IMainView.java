package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.datamodel.IDatabaseObserver;

public interface IMainView {
    void addObserver(IMainOberserver observer);
    void deleteObserver(IMainOberserver observer);
}
