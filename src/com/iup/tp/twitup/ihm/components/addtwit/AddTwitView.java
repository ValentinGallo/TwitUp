package com.iup.tp.twitup.ihm.components.addtwit;

import com.iup.tp.twitup.ihm.IViewObservable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class AddTwitView extends JPanel implements IViewObservable<IAddTwitObserver> {

    private IAddTwitObserver observer;

    public AddTwitView() {
        this.showGUI();
    }

    /**
     * Lance l'afficahge de l'IHM.
     */
    public void showGUI() {
        this.initGUI();
    }

    /**
     * Initialisation de l'IHM
     */
    protected void initGUI() {
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        this.setBackground(Color.WHITE);


        JLabel jlblTitle = new JLabel("sdsd" + " @" + "sd");
        jlblTitle.setFont(new Font("Roboto", Font.BOLD, 12));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        this.add(jlblTitle, c);



        JLabel jlblText = new JLabel("twit text", SwingConstants.CENTER);
        jlblText.setFont(new Font("Roboto", Font.ITALIC, 12));
        c.gridx = 0;
        c.gridy = 2;
        this.add(jlblText, c);

        JButton jbtnFollow = new JButton("Suivre");
        jlblText.setFont(new Font("Roboto", Font.ITALIC, 12));
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        this.add(jbtnFollow, c);
    }

    @Override
    public void addObserver(IAddTwitObserver observer) {
        this.observer = observer;
    }

    @Override
    public void deleteObserver(IAddTwitObserver observer) {

    }
}
