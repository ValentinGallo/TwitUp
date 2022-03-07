package com.iup.tp.twitup.ihm.components.listUser;

import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.IViewObservable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Set;

public class TwitupListUser extends JPanel implements IViewObservable<IListUserObserver> {

    protected Set<User> list_user;
    protected IListUserObserver observer;

    public TwitupListUser(Set<User> list) {
        this.list_user = list;
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


        JLabel jlblTitle = new JLabel("Liste des users");
        jlblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        jlblTitle.setFont(new Font("Roboto", Font.BOLD, 12));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        this.add(jlblTitle, c);


        for (User user : this.list_user) {
            c.gridx = 0;
            c.gridy++;
            c.gridwidth = 2;
            this.add(new JSeparator(), c);
            c.gridwidth = 1;
            c.gridy++;

            JLabel jlblUser = new JLabel(user.getName());
            jlblUser.setFont(new Font("Roboto", Font.BOLD, 12));
            this.add(jlblUser, c);

            JButton btnFollow = new JButton("Suivre");
            btnFollow.setFont(new Font("Roboto", Font.BOLD, 12));
            c.gridx++;
            this.add(btnFollow, c);
        }


        this.setBackground(Color.ORANGE);


    }

    @Override
    public void addObserver(IListUserObserver observer) {
        this.observer = observer;
    }

    @Override
    public void deleteObserver(IListUserObserver observer) {

    }
}
