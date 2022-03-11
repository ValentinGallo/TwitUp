package com.iup.tp.twitup.ihm.components.listtwit;

import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.ihm.IMainOberserver;
import com.iup.tp.twitup.ihm.IViewObservable;
import com.iup.tp.twitup.ihm.components.twit.TwitupTwit;
import com.iup.tp.twitup.ihm.controller.TwitController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwitupListTwit extends JPanel implements IViewObservable<IListTwitObserver> {

    protected Set<Twit> listTwit;
    protected IListTwitObserver observer;
    protected Set<Twit> listTwitFiltered;
    protected TwitController twitController;
    protected IMainOberserver mainOberserver;
    JTextField jtfSearch;

    public TwitupListTwit(Set<Twit> list, TwitController twitController, IMainOberserver mainOberserver) {
        this.listTwit = list;
        this.listTwitFiltered = listTwit;
        this.twitController = twitController;
        this.mainOberserver = mainOberserver;
        jtfSearch = new JTextField();
        initGUI();
    }

    /**
     * Initialisation de l'IHM
     */
    protected void initGUI() {
        Font fontRoboto = new Font("Roboto", Font.BOLD, 12);
        this.removeAll();

        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        JLabel jlblTitle = new JLabel("Liste des Twit");
        jlblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        jlblTitle.setFont(fontRoboto);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 0;
        this.add(jlblTitle, c);

        jtfSearch.setFont(fontRoboto);
        c.gridy++;
        this.add(jtfSearch, c);

        JButton jbtnSearch = new JButton("Rechercher");
        jbtnSearch.setFont(fontRoboto);
        jbtnSearch.addActionListener(e -> TwitupListTwit.this.search());
        c.gridy++;
        this.add(jbtnSearch, c);


        c.gridwidth = 2;
        c.gridx = 0;

        for (Twit twit : this.listTwitFiltered) {
            c.gridy++;
            this.add(new JSeparator(), c);
            c.gridy++;

            TwitupTwit twitView = new TwitupTwit(twit, this.twitController, this.mainOberserver);
            this.add(twitView, c);

        }


        this.setBackground(Color.ORANGE);
        this.revalidate();
    }

    @Override
    public void addObserver(IListTwitObserver observer) {
        this.observer = observer;
    }

    @Override
    public void deleteObserver(IListTwitObserver observer) {
        // TODO document why this method is empty
    }

    public void search() {
        Pattern pattern = Pattern.compile(jtfSearch.getText());

        this.listTwitFiltered = new HashSet<Twit>();

        for (Twit twit : this.listTwit) {
            Matcher matcher = pattern.matcher(twit.getText());
            while (matcher.find()) {
                this.listTwitFiltered.add(twit);
            }
        }

        this.listTwitFiltered.addAll(this.observer.getUserTwits(jtfSearch.getText()));

        this.initGUI();
    }
}
