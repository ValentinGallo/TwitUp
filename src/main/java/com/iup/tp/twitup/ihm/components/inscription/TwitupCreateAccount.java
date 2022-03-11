package com.iup.tp.twitup.ihm.components.inscription;

import com.iup.tp.twitup.ihm.IViewObservable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.util.HashSet;

public class TwitupCreateAccount extends JPanel implements IViewObservable<ICreateAccountObserver> {

    protected JTextField nom;
    protected JTextField tag;
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
            observer.notifyCreateAccount(tTag, tPassword, tNom, new HashSet<>(), tAvatar);
        }

    }

    /**
     * Initialisation de l'IHM
     */
    protected void initGUI() {
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setBackground(Color.ORANGE);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        JLabel jlblTitle = new JLabel("Liste des Twit");
        jlblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        jlblTitle.setFont(new Font("Roboto", Font.BOLD, 12));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 0;
        this.add(jlblTitle, c);

        c.gridwidth = 1;
        JLabel jlblNom = new JLabel("nom");
        c.gridy++;
        this.add(jlblNom, c);
        this.nom = new JTextField();
        c.gridx++;
        this.add(nom, c);
        JLabel jlblTag = new JLabel("tag");
        c.gridy++;
        c.gridx = 0;
        this.add(jlblTag, c);
        this.tag = new JTextField();
        c.gridx++;
        this.add(tag, c);
        JLabel jlblAvatar = new JLabel("avatar");
        c.gridy++;
        c.gridx = 0;
        this.add(jlblAvatar, c);
        JButton avatarBtn = new JButton("Choisir");
        avatarBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
            fileChooser.setFileFilter(filter);
            if (fileChooser.showOpenDialog(TwitupCreateAccount.this) == JFileChooser.APPROVE_OPTION) {
                TwitupCreateAccount.this.avatar = fileChooser.getSelectedFile().toString();
                avatarBtn.setText(fileChooser.getSelectedFile().getName());
            }
        });

        c.gridx++;
        this.add(avatarBtn, c);

        JLabel jlblMdp = new JLabel("mot de passe");
        c.gridy++;
        c.gridx = 0;
        this.add(jlblMdp, c);

        this.password = new JPasswordField();
        c.gridx++;
        this.add(password, c);

        jlblStatus = new JLabel("");
        jlblStatus.setForeground(Color.RED);
        c.gridy++;
        c.gridx = 0;
        this.add(jlblStatus, c);

        JButton btn = new JButton("Créer un compte");
        btn.addActionListener(e -> TwitupCreateAccount.this.createAccount());
        c.gridx = 0;
        c.gridwidth = 2;
        this.add(btn, c);

    }

    @Override
    public void addObserver(ICreateAccountObserver observer) {
        this.observer = observer;
    }

    @Override
    public void deleteObserver(ICreateAccountObserver observer) {
        // TODO document why this method is empty
    }
}
