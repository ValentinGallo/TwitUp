package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TwitupCreateAccount extends JPanel implements IDatabaseObserver {

    protected JTextField nom, tag, avatar;
    protected JPasswordField password;
    protected JLabel jlblStatus;

    /**
     * Gestionnaire de bdd et de fichier.
     */
    protected EntityManager mEntityManager;

    public TwitupCreateAccount(EntityManager entityManager) {
        this.mEntityManager = entityManager;
        initGUI();
    }

    public void createAccount() {
        String tNom = this.nom.getText();
        String tTag = this.tag.getText();
        String tAvatar = this.nom.getText();
        String tPassword = this.password.getPassword().toString();

        if (tNom.equals("") || tTag.equals("") || tPassword.equals("")) {
            this.jlblStatus.setText("tag | nom | mdp requis.");
        } else if (mEntityManager.tagExist(tTag)) {
            this.jlblStatus.setText("tag déjà existant");
        } else {
            mEntityManager.addUser(tNom, tTag, tAvatar, tPassword);
        }

    }

    /**
     * Initialisation de l'IHM
     */
    protected void initGUI() {
        this.setLayout(new GridLayout(5, 1));

        JLabel jlblNom = new JLabel("nom");
        this.nom = new JTextField();
        this.add(jlblNom);
        this.add(nom);
        JLabel jlblTag = new JLabel("tag");
        this.tag = new JTextField();
        this.add(jlblTag);
        this.add(tag);
        JLabel jlblAvatar = new JLabel("avatar");
        this.avatar = new JTextField();
        this.add(jlblAvatar);
        this.add(avatar);
        JLabel jlblMdp = new JLabel("mot de passe");
        this.password = new JPasswordField();
        this.add(jlblMdp);
        this.add(password);

        jlblStatus = new JLabel("");
        jlblStatus.setForeground(Color.RED);
        this.add(jlblStatus);

        JButton btn = new JButton("Créer un compte");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TwitupCreateAccount.this.createAccount();
            }
        });


        this.add(btn);

    }

    @Override
    public void notifyTwitAdded(Twit addedTwit) {

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
