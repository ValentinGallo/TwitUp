package com.iup.tp.twitup.ihm.components.connexion;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.ihm.IMainOberserver;
import com.iup.tp.twitup.ihm.IViewObservable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TwitConnexionView extends JPanel implements IViewObservable<IConnexionObserver> {

    private final JLabel jlblUsername = new JLabel("Tag");
    private final JLabel jlblPassword = new JLabel("Mot de passe");
    private final JTextField jtfUsername = new JTextField(15);
    private final JPasswordField jpfPassword = new JPasswordField();
    private final JButton jbtOk = new JButton("Connexion");
    private final JButton jbtCancel = new JButton("Retour");
    private final JLabel jlblStatus = new JLabel(" ");

    private IConnexionObserver observer;

    public TwitConnexionView() {
        this.showGUI();
    }

    /**
     * Lance l'afficahge de l'IHM.
     */
    public void showGUI() {
        this.initGUI();
    }

    /**
     * Initialisation de l'IHM
     */
    protected void initGUI() {
        this.setLayout(new GridLayout(6, 1));

        this.add(jlblUsername);
        this.add(jtfUsername);
        this.add(jlblPassword);
        this.add(jpfPassword);


        JPanel buttons = new JPanel(new GridLayout(1, 2));
        buttons.add(jbtCancel);
        buttons.add(jbtOk);

        this.add(buttons, BorderLayout.CENTER);

        jlblStatus.setForeground(Color.RED);
        jlblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(jlblStatus);

        jbtOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jtfUsername.getText().length() == 0 && jpfPassword.getPassword().length == 0) {
                    jlblStatus.setText("Veuillez entrer un tag et un mot de passe");
                } else {
                    if (observer.notifyConnexion(jtfUsername.getText(), new String(jpfPassword.getPassword()))) {
                        TwitConnexionView.this.setVisible(false);
                    } else {
                        jlblStatus.setText("Tag ou mot de passe invalide");
                    }
                }
            }
        });

        jbtCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    @Override
    public void addObserver(IConnexionObserver observer) {
        this.observer = observer;
    }

    @Override
    public void deleteObserver(IConnexionObserver observer) {

    }
}
