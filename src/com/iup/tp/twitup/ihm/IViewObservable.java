package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.datamodel.IDatabaseObserver;

public interface IViewObservable<T> {
    void addObserver(T observer);
    void deleteObserver(T observer);
}
