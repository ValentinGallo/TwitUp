package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
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
public class TwitupMainView extends JFrame implements IDatabaseObserver {

    protected JPanel currentPanel;

    /**
     * Base de donénes de l'application.
     */
    protected IDatabase mDatabase;

    /**
     * Gestionnaire de bdd et de fichier.
     */
    protected EntityManager mEntityManager;

    public TwitupMainView(IDatabase database, EntityManager entityManager) {
        this.mDatabase = database;
        this.mEntityManager = entityManager;
        // Init auto de l'IHM
        this.initGUI();
    }

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
        this.pack();
        this.setLayout(new FlowLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("Fichier");

        JMenuItem menuOpen = new JMenuItem("Ouvrir...");
        menuFile.add(menuOpen);
        /*
        menuOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                switch (fileChooser.showOpenDialog(mFrame)) {
                    case JFileChooser.APPROVE_OPTION:
                        mEntityManager.setExchangeDirectory(fileChooser.getSelectedFile().toString());
                        break;
                }
            }
        });*/

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
        JMenuItem menuInscription = new JMenuItem("Inscription");
        JMenuItem menuConnexion = new JMenuItem("Connexion");

        menuInscription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TwitupMainView.this.changeCurrentPanel(new TwitupCreateAccount(TwitupMainView.this.mEntityManager));
            }
        });

        menuConnexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TwitupMainView.this.changeCurrentPanel(new TwitConnexionView(TwitupMainView.this.mEntityManager));
            }
        });

        jMenuCompte.add(menuInscription);
        jMenuCompte.add(menuConnexion);
        menuBar.add(jMenuCompte);

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

    public void changeCurrentPanel(JPanel panel) {
        if (this.currentPanel != null) this.remove(this.currentPanel);
        this.currentPanel = panel;
        this.add(currentPanel);
        this.revalidate();
        this.repaint();
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
