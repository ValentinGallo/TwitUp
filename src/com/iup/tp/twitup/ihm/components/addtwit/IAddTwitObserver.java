package com.iup.tp.twitup.ihm.components.addtwit;

public interface IAddTwitObserver {
    Boolean isTwitOk(String message);
    void twit(String message);
}
