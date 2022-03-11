package com.iup.tp.twitup.ihm.components.twitterprofile;

import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.IViewObservable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TwitUpTwitterProfile extends JPanel implements IViewObservable<ITwitterProfileObserver> {

    protected User twitterUser;
    protected ITwitterProfileObserver observer;
    JButton jbtnFollow = new JButton();

    public TwitUpTwitterProfile(String twitterUserTag, ITwitterProfileObserver observer) {
        this.addObserver(observer);
        this.twitterUser = this.observer.getUserFromTag(twitterUserTag);
        initGUI();
    }

    /**
     * Initialisation de l'IHM
     */
    protected void initGUI() {
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setBackground(Color.ORANGE);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c;


        JLabel jlblTitle = new JLabel("Profil de " + twitterUser.getName());
        jlblTitle.setFont(new Font("Roboto", Font.BOLD, 12));
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        this.add(jlblTitle, c);

        JLabel jlblTag = new JLabel("@" + twitterUser.getUserTag());
        jlblTag.setFont(new Font("Roboto", Font.BOLD + Font.ITALIC, 12));

        ImageIcon imageIcon = new ImageIcon(twitterUser.getAvatarPath()); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way

        jlblTag.setIcon(new ImageIcon(newimg));
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 1;
        this.add(jlblTag, c);

        JLabel jlblNbTwits = new JLabel(this.observer.getNbTwitsPostedByUser(twitterUser) + " twit(s) postée(s)");
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 2;
        this.add(jlblNbTwits, c);

        JLabel jlblNbFollowers = new JLabel(this.observer.getNbFollowersByUser(twitterUser) + " follower(s)");
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 4;
        this.add(jlblNbFollowers, c);

        if (this.observer.getCurrentUser() != null) {
            this.initButtonFollow();
            c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 6;
            c.gridwidth = 2;
            this.add(jbtnFollow, c);
        }
    }

    public void initButtonFollow() {
        if (TwitUpTwitterProfile.this.observer.isAlreadyFollowedByUser(TwitUpTwitterProfile.this.twitterUser)) {
            this.jbtnFollow.setText("Ne plus suivre");
            jbtnFollow.addActionListener(e -> TwitUpTwitterProfile.this.observer.unfollowTwitAuthor(TwitUpTwitterProfile.this.twitterUser));
        } else {
            jbtnFollow.setText("Suivre");
            jbtnFollow.addActionListener(e -> TwitUpTwitterProfile.this.observer.followTwitAuthor(TwitUpTwitterProfile.this.twitterUser));
        }
    }

    @Override
    public void addObserver(ITwitterProfileObserver observer) {
        this.observer = observer;
    }

    @Override
    public void deleteObserver(ITwitterProfileObserver observer) {
        // TODO document why this method is empty
    }
}