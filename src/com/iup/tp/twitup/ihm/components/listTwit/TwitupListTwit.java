package com.iup.tp.twitup.ihm.components.listTwit;

import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.IViewObservable;
import com.iup.tp.twitup.ihm.components.profil.IProfilObserver;
import com.iup.tp.twitup.ihm.components.twit.TwitupTwit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

public class TwitupListTwit extends JPanel implements IViewObservable<IListTwitObserver> {

    protected Set<Twit> list_twit;
    protected IListTwitObserver observer;

    public TwitupListTwit(Set<Twit> list) {
        this.list_twit = list;
        initGUI();
    }

    /**
     * Initialisation de l'IHM
     */
    protected void initGUI() {
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //this.setPreferredSize(new Dimension(200, 100));


        JLabel jlblTitle = new JLabel("Liste des Twit");
        jlblTitle.setFont(new Font("Roboto", Font.BOLD, 12));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        this.add(jlblTitle,c);



        for (Twit twit:this.list_twit) {
            c.gridy++;
            this.add(new TwitupTwit(twit),c);
        }


        this.setBackground(Color.ORANGE);


    }

    @Override
    public void addObserver(IListTwitObserver observer) {
        this.observer = observer;
    }

    @Override
    public void deleteObserver(IListTwitObserver observer) {

    }
}
