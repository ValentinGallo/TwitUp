package com.iup.tp.twitup.ihm.components.inscription;

import java.util.HashSet;
import java.util.Set;

public interface ICreateAccountObserver {
    void notifyCreateAccount(String tTag, String tPassword, String tNom, Set<String> follows, String tAvatar);
    boolean isAccountExist(String tag);
    boolean isPasswordValid(String tPassword);
}
