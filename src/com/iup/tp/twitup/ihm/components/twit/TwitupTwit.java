package com.iup.tp.twitup.ihm.components.twit;

import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TwitupTwit extends JPanel implements IDatabaseObserver {

    protected final long mEmissionDate;
    protected String text;

    public TwitupTwit() {
        this.text = "Max and Lewis are now reportedly earning the same amount to drive in F1 ";
        this.mEmissionDate = 1889994;
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

        JLabel jlblText = new JLabel(this.text, SwingConstants.CENTER);
        jlblText.setFont(new Font("Roboto", Font.ITALIC, 12));
        this.add(jlblText);

        JLabel jlblDate = new JLabel("30/11/2022", SwingConstants.RIGHT);
        jlblDate.setFont(new Font("Roboto", 0, 12));
        this.add(jlblDate);

    }

    @Override
    public void notifyTwitAdded(Twit addedTwit) {

    }

    @Override
    public void notifyLoggedUser(User user) {

    }

    @Override
    public void notifyTwitDeleted(Twit deletedTwit) {

    }

    @Override
    public void notifyTwitModified(Twit modifiedTwit) {

    }

    @Override
    public void notifyUserAdded(User addedUser) {

    }

    @Override
    public void notifyUserDeleted(User deletedUser) {

    }

    @Override
    public void notifyUserModified(User modifiedUser) {

    }
}
