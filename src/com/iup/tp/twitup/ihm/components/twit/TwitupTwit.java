package com.iup.tp.twitup.ihm.components.twit;

import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.ihm.IViewObservable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TwitupTwit extends JPanel implements IViewObservable<ITwitObserver> {

    protected Twit twit;

    protected ITwitObserver observer;

    public TwitupTwit(Twit twit) {
        this.twit = twit;
        initGUI();
    }

    /**
     * Initialisation de l'IHM
     */
    protected void initGUI() {
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(new GridLayout(3, 1));
        this.setPreferredSize(new Dimension(200, 100));

        this.setBackground(Color.ORANGE);

        JLabel jlblTitle = new JLabel("Twit - " + "Michel");
        jlblTitle.setFont(new Font("Roboto", Font.BOLD, 12));
        jlblTitle.setIcon(new ImageIcon("src/resources/images/logoIUP_20.jpg"));
        this.add(jlblTitle);

        JLabel jlblText = new JLabel(this.twit.getText(), SwingConstants.CENTER);
        jlblText.setFont(new Font("Roboto", Font.ITALIC, 12));
        this.add(jlblText);

        JLabel jlblDate = new JLabel("30/11/2022", SwingConstants.RIGHT);
        jlblDate.setFont(new Font("Roboto", 0, 12));
        this.add(jlblDate);

    }

    @Override
    public void addObserver(ITwitObserver observer) {
        this.observer = observer;
    }

    @Override
    public void deleteObserver(ITwitObserver observer) {
        this.observer = null;
    }
}
