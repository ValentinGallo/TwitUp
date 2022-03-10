package com.iup.tp.twitup.ihm.components.profil;

import com.iup.tp.twitup.datamodel.User;

import java.util.Set;
import java.util.UUID;

public interface IProfilObserver {
    void modifyAccount(User user);
}
