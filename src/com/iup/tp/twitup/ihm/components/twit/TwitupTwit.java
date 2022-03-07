package com.iup.tp.twitup.ihm.components.twit;

import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.IViewObservable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class TwitupTwit extends JPanel implements IViewObservable<ITwitObserver> {

    protected Twit twit;
    protected ITwitObserver observer;
    protected User currentUser;

    public TwitupTwit(Twit twit, User currentUser) {
        this.twit = twit;
        this.currentUser = currentUser;
        initGUI();
    }

    /**
     * Initialisation de l'IHM
     */
    protected void initGUI() {
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        this.setBackground(Color.WHITE);


        JLabel jlblTitle = new JLabel(this.twit.getTwiter().getName() + " @" + this.twit.getTwiter().getUserTag());
        jlblTitle.setFont(new Font("Roboto", Font.BOLD, 12));
        jlblTitle.setIcon(new ImageIcon(this.twit.getTwiter().getAvatarPath()));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        this.add(jlblTitle, c);

        JLabel jlblDate = new JLabel(new Date(this.twit.getEmissionDate()).toString(), SwingConstants.RIGHT);
        jlblDate.setFont(new Font("Roboto", 0, 12));
        c.gridx = 1;
        c.gridy = 0;
        this.add(jlblDate, c);

        JLabel jlblText = new JLabel(this.twit.getText(), SwingConstants.CENTER);
        jlblText.setFont(new Font("Roboto", Font.ITALIC, 12));
        c.gridx = 0;
        c.gridy = 2;
        this.add(jlblText, c);

        if(this.currentUser != null) {
            JButton jbtnFollow = new JButton("Suivre");
            jlblText.setFont(new Font("Roboto", Font.ITALIC, 12));
            c.gridx = 0;
            c.gridy = 3;
            c.gridwidth = 2;

            jbtnFollow.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(TwitupTwit.this.observer.isAlreadyFollowedByUser(TwitupTwit.this.currentUser, TwitupTwit.this.twit.getTwiter())) {
                        jbtnFollow.setText("Ne plus suivre");
                        TwitupTwit.this.observer.unfollowTwitAuthor(TwitupTwit.this.currentUser, TwitupTwit.this.twit.getTwiter());
                    }
                    else {
                        jbtnFollow.setText("Suivre");
                        TwitupTwit.this.observer.followTwitAuthor(TwitupTwit.this.currentUser, TwitupTwit.this.twit.getTwiter());
                    }
                }
            });

            this.add(jbtnFollow, c);
        }


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
