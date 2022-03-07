package com.iup.tp.twitup.ihm.components.listTwit;

import com.iup.tp.twitup.datamodel.Twit;

import java.util.Set;

public interface IListTwitObserver {
    Set<Twit> getUserTwits(String userTag);
}
