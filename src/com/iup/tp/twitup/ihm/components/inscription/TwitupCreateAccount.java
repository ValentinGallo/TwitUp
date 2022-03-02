package com.iup.tp.twitup.ihm.components.inscription;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.UUID;

public class TwitupCreateAccount extends JPanel implements IDatabaseObserver {

    protected JTextField nom, tag;
    protected String avatar;
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
        String tAvatar = this.avatar;
        String tPassword = new String(this.password.getPassword());

        if (tNom.equals("") || tTag.equals("") || password.getPassword().length < 1) {
            this.jlblStatus.setText("tag | nom | mdp requis.");
        } else if (mEntityManager.tagExist(tTag)) {
            this.jlblStatus.setText("tag déjà existant");
        } else {
            this.jlblStatus.setText("");

            User newUser = new User(UUID.randomUUID(), tTag, tPassword, tNom, new HashSet<String>(), tAvatar);
            System.out.println(newUser.getAvatarPath());
            mEntityManager.sendUser(newUser);
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
        JButton avatarBtn = new JButton();
        avatarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                switch (fileChooser.showOpenDialog(TwitupCreateAccount.this)) {
                    case JFileChooser.APPROVE_OPTION:
                        TwitupCreateAccount.this.avatar = fileChooser.getSelectedFile().toString();
                        avatarBtn.setText(fileChooser.getSelectedFile().getName());
                        break;
                }
            }
        });

        this.add(jlblAvatar);
        this.add(avatarBtn);
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
