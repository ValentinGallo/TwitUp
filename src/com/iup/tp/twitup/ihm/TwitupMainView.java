package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.datamodel.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Classe de la vue principale de l'application.
 */
public class TwitupMainView extends JFrame implements IViewObservable<IMainOberserver> {

    /**
     * Liste des observers
     */
    protected IMainOberserver observer;

    /**
     * User courant
     */
    protected User mUser;

    public TwitupMainView() {
        // Init auto de l'IHM
        this.initGUI();
    }

    /**
     * Initialisation de l'IHM
     */
    protected void initGUI() {
        // Création de la fenetre principale
        this.setTitle("TwitUp");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        this.setIconImage(new ImageIcon("src/resources/images/logoIUP_50.jpg").getImage());
        this.setPreferredSize(new Dimension(400, 400));

        this.initMenuBar(null);
        this.pack();
        this.setLayout(new FlowLayout());

    }

    public void initMenuBar(User user) {

        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("Fichier");

        JMenuItem menuOpen = new JMenuItem("Ouvrir...");
        menuFile.add(menuOpen);

        menuOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                switch (fileChooser.showOpenDialog(TwitupMainView.this)) {
                    case JFileChooser.APPROVE_OPTION:
                        TwitupMainView.this.observer.notifyDirectoryChanged(fileChooser.getSelectedFile());
                        break;
                }
            }
        });

        menuFile.addSeparator();

        JMenuItem menuLeave = new JMenuItem("Quitter");
        menuLeave.setIcon(new ImageIcon("src/resources/images/exitIcon_20.png"));
        menuLeave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                TwitupMainView.this.leave();
            }
        });
        menuFile.add(menuLeave);
        menuBar.add(menuFile);

        JMenu jMenuCompte = new JMenu("Compte");
        if (user == null) {
            JMenuItem menuInscription = new JMenuItem("Inscription");
            JMenuItem menuConnexion = new JMenuItem("Connexion");

            menuInscription.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TwitupMainView.this.observer.goToInscriptionPage();
                }
            });

            menuConnexion.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TwitupMainView.this.observer.goToConnexionPage();
                }
            });

            jMenuCompte.add(menuInscription);
            jMenuCompte.add(menuConnexion);
        } else {
            JMenuItem menuUser = new JMenuItem(user.getName());
            if (user.getAvatarPath() != null) {
                ImageIcon imageIcon = new ImageIcon(user.getAvatarPath()); // load the image to a imageIcon
                Image image = imageIcon.getImage(); // transform it
                Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                menuUser.setIcon(new ImageIcon(newimg));
            }
            menuUser.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TwitupMainView.this.observer.goToProfilPage();
                }
            });

            JMenuItem menuDeconnexion = new JMenuItem("Déconnexion");
            menuDeconnexion.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TwitupMainView.this.observer.deconnectUser();
                }
            });

            jMenuCompte.add(menuUser);
            jMenuCompte.addSeparator();
            jMenuCompte.add(menuDeconnexion);
        }
        menuBar.add(jMenuCompte);

        if (user != null) {
            JMenu jMenuListTwit = new JMenu("Liste Twit");
            jMenuListTwit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TwitupMainView.this.observer.goToListTwitPage();
                }
            });
            menuBar.add(jMenuListTwit);

            JMenu jmenuListUser = new JMenu("Liste Users");
            jmenuListUser.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TwitupMainView.this.observer.goToListUserPage();
                }
            });
            menuBar.add(jmenuListUser);

            JMenu jmenuAddTwit = new JMenu("Poster");
            jmenuAddTwit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TwitupMainView.this.observer.goToAddTwitPage();
                }
            });
            menuBar.add(jmenuAddTwit);
        }

        JMenu menuOther = new JMenu("?");
        JMenuItem menuAbout = new JMenuItem("A Propos");
        menuAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(null,
                        new JLabel("<html><h5 class='text-align:'center''>UBO M2 TILL-A<br/>Département Informatique</h5></html>"),
                        "A propos",
                        JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("src/resources/images/logoIUP_50.jpg"));
            }

        });
        menuOther.add(menuAbout);
        menuBar.add(menuOther);

        this.setJMenuBar(menuBar);
    }

    /**
     * Lance l'afficahge de l'IHM.
     */
    public void showGUI() {
        // Affichage dans l'EDT
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Custom de l'affichage
                JFrame frame = TwitupMainView.this;
                Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation((screenSize.width - frame.getWidth()) / 6,
                        (screenSize.height - frame.getHeight()) / 4);

                // Affichage
                frame.setVisible(true);

                frame.pack();
            }
        });
    }

    /**
     * Quitte l'application
     */
    protected void leave() {
        System.exit(0);
    }

    @Override
    public void addObserver(IMainOberserver observer) {
        this.observer = observer;
    }

    @Override
    public void deleteObserver(IMainOberserver observer) {
        this.observer = null;
    }
}
