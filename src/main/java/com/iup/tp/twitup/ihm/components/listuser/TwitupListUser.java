package com.iup.tp.twitup.ihm.components.listuser;

import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.IViewObservable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwitupListUser extends JPanel implements IViewObservable<IListUserObserver> {

    protected Set<User> listUser;
    protected IListUserObserver observer;
    protected Set<User> listUserFiltered;
    JTextField jtfSearchUser;
    Font fontRoboto = new Font("Roboto", Font.BOLD, 12);

    public TwitupListUser(Set<User> list) {
        this.listUser = list;
        this.listUserFiltered = listUser;
        initGUI();
    }

    /**
     * Initialisation de l'IHM
     */
    protected void initGUI() {
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        JLabel jlblTitle = new JLabel("Liste des users");
        jlblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        jlblTitle.setFont(fontRoboto);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        this.add(jlblTitle, c);

        jtfSearchUser = new JTextField();
        jlblTitle.setFont(fontRoboto);
        c.gridy++;
        this.add(jtfSearchUser, c);

        JButton jbtnSearch = new JButton("Rechercher");
        jbtnSearch.setFont(fontRoboto);
        c.gridy++;
        jbtnSearch.addActionListener(e -> TwitupListUser.this.search());
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
        // TODO document why this method is empty
    }

    public void loadUsers() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 2;
        for (User user : this.listUserFiltered) {
            c.gridx = 0;
            c.gridy++;
            c.gridwidth = 2;
            this.add(new JSeparator(), c);
            c.gridwidth = 1;
            c.gridy++;

            JLabel jlblUser = new JLabel(user.getName());
            jlblUser.setFont(fontRoboto);
            this.add(jlblUser, c);

            JButton btnFollow = new JButton("Suivre");
            btnFollow.setFont(fontRoboto);
            c.gridx++;
            this.add(btnFollow, c);
        }
        this.revalidate();
    }

    public void search() {
        Pattern pattern = Pattern.compile(jtfSearchUser.getText());

        this.listUserFiltered = new HashSet<User>();

        for (User user : this.listUser) {
            if (("@" + user.getUserTag()).equals(jtfSearchUser.getText())) {
                this.listUserFiltered.add(user);
                break;
            }
            Matcher matcher = pattern.matcher(user.getName());
            while (matcher.find()) {
                this.listUserFiltered.add(user);
            }

        }

        this.removeAll();
        this.initGUI();
    }
}
