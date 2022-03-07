package com.iup.tp.twitup.ihm.components.listTwit;

import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.ihm.IViewObservable;
import com.iup.tp.twitup.ihm.components.twit.TwitupTwit;
import com.iup.tp.twitup.ihm.controller.TwitController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwitupListTwit extends JPanel implements IViewObservable<IListTwitObserver> {

    protected Set<Twit> list_twit;
    protected IListTwitObserver observer;
    protected Set<Twit> list_twit_filtered;
    protected TwitController twitController;
    JTextField jtfSearch;

    public TwitupListTwit(Set<Twit> list, TwitController twitController) {
        this.list_twit = list;
        this.list_twit_filtered = list_twit;
        this.twitController = twitController;
        initGUI();
    }

    /**
     * Initialisation de l'IHM
     */
    protected void initGUI() {
        this.removeAll();

        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //this.setPreferredSize(new Dimension(200, 100));


        JLabel jlblTitle = new JLabel("Liste des Twit");
        jlblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        jlblTitle.setFont(new Font("Roboto", Font.BOLD, 12));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 0;
        this.add(jlblTitle, c);

        jtfSearch = new JTextField();
        jtfSearch.setFont(new Font("Roboto", Font.BOLD, 12));
        c.gridy++;
        this.add(jtfSearch, c);

        JButton jbtnSearch = new JButton("Rechercher");
        jbtnSearch.setFont(new Font("Roboto", Font.BOLD, 12));
        jbtnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TwitupListTwit.this.search();
            }
        });
        c.gridy++;
        this.add(jbtnSearch, c);


        c.gridwidth = 2;
        c.gridx = 0;

        System.out.println(this.twitController);
        for (Twit twit : this.list_twit_filtered) {
            c.gridy++;
            this.add(new JSeparator(), c);
            c.gridy++;

            TwitupTwit twitView = new TwitupTwit(twit, this.twitController);
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

    }

    public void search() {
        Pattern pattern = Pattern.compile(jtfSearch.getText());

        this.list_twit_filtered = new HashSet<Twit>();

        for (Twit twit : this.list_twit) {
            Matcher matcher = pattern.matcher(twit.getText());
            while (matcher.find()) {
                this.list_twit_filtered.add(twit);
            }
        }
        
        this.list_twit_filtered.addAll(this.observer.getUserTwits(jtfSearch.getText()));

        this.initGUI();
    }
}
