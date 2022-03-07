package com.iup.tp.twitup.ihm.components.inscription;

import com.iup.tp.twitup.ihm.IViewObservable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class TwitupCreateAccount extends JPanel implements IViewObservable<ICreateAccountObserver> {

    protected JTextField nom, tag;
    protected String avatar;
    protected JPasswordField password;
    protected JLabel jlblStatus;
    protected ICreateAccountObserver observer;

    /**
     * Gestionnaire de bdd et de fichier.
     */

    public TwitupCreateAccount() {
        initGUI();
    }

    public void createAccount() {
        String tNom = this.nom.getText();
        String tTag = this.tag.getText();
        String tAvatar = this.avatar;
        String tPassword = new String(this.password.getPassword());
        jlblStatus.setForeground(Color.RED);

        if (tNom.equals("") || tTag.equals("") || password.getPassword().length < 1) {
            this.jlblStatus.setText("Tag | Nom | Mdp requis");
        } else if (observer.isAccountExist(tTag)) {
            this.jlblStatus.setText("Tag déjà existant");
        } else if (!observer.isPasswordValid(tPassword)) {
            this.jlblStatus.setText("Le mot de passe n'est pas assez sécurisé");
        } else {
            observer.notifyCreateAccount(tTag, tPassword, tNom, new HashSet<String>(), tAvatar);
            jlblStatus.setForeground(Color.GREEN);
            this.jlblStatus.setText("Compte créé");
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
    public void addObserver(ICreateAccountObserver observer) {
        this.observer = observer;
    }

    @Override
    public void deleteObserver(ICreateAccountObserver observer) {

    }
}
