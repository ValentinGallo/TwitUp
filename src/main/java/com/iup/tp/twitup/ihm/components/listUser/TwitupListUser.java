package com.iup.tp.twitup.ihm.components.listUser;

import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.IViewObservable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwitupListUser extends JPanel implements IViewObservable<IListUserObserver> {

    protected Set<User> list_user;
    protected IListUserObserver observer;
    protected Set<User> list_user_filtered;
    JTextField jtfSearchUser;

    public TwitupListUser(Set<User> list) {
        this.list_user = list;
        this.list_user_filtered = list_user;
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

        jtfSearchUser = new JTextField();
        jlblTitle.setFont(new Font("Roboto", Font.BOLD, 12));
        c.gridy++;
        this.add(jtfSearchUser, c);

        JButton jbtnSearch = new JButton("Rechercher");
        jbtnSearch.setFont(new Font("Roboto", Font.BOLD, 12));
        c.gridy++;
        jbtnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TwitupListUser.this.search();
            }
        });
        this.add(jbtnSearch, c);
        this.setBackground(Color.ORANGE);

        this.loadUsers();
    }

    @Override
    public void addObserver(IListUserObserver observer) {
        this.observer = observer;
    }

    @Override
    public void deleteObserver(IListUserObserver observer) {

    }

    public void loadUsers() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 2;
        for (User user : this.list_user_filtered) {
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
        this.revalidate();
    }

    public void search() {
        Pattern pattern = Pattern.compile(jtfSearchUser.getText());

        this.list_user_filtered = new HashSet<User>();

        for (User user : this.list_user) {
            if (("@" + user.getUserTag()).equals(jtfSearchUser.getText())) {
                this.list_user_filtered.add(user);
                break;
            }
            Matcher matcher = pattern.matcher(user.getName());
            while (matcher.find()) {
                this.list_user_filtered.add(user);
            }

        }

        this.removeAll();
        this.initGUI();
    }
}
