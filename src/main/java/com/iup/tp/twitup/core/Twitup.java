package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.Database;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.events.file.IWatchableDirectory;
import com.iup.tp.twitup.events.file.WatchableDirectory;
import com.iup.tp.twitup.ihm.IMainOberserver;
import com.iup.tp.twitup.ihm.TwitupMainView;
import com.iup.tp.twitup.ihm.TwitupMock;
import com.iup.tp.twitup.ihm.components.addtwit.AddTwitView;
import com.iup.tp.twitup.ihm.components.connexion.TwitConnexionView;
import com.iup.tp.twitup.ihm.components.inscription.TwitupCreateAccount;
import com.iup.tp.twitup.ihm.components.listTwit.TwitupListTwit;
import com.iup.tp.twitup.ihm.components.listUser.TwitupListUser;
import com.iup.tp.twitup.ihm.components.profil.TwitupProfil;
import com.iup.tp.twitup.ihm.controller.*;

import javax.swing.*;
import java.io.File;

/**
 * Classe principale l'application.
 *
 * @author S.Lucas
 */
public class Twitup implements IMainOberserver {
    /**
     * Base de données.
     */
    protected IDatabase mDatabase;

    /**
     * Gestionnaire des entités contenu de la base de données.
     */
    protected EntityManager mEntityManager;

    /**
     * Vue principale de l'application.
     */
    protected TwitupMainView mMainView;

    /**
     * Classe de surveillance de répertoire
     */
    protected IWatchableDirectory mWatchableDirectory;

    /**
     * Répertoire d'échange de l'application.
     */
    protected String mExchangeDirectoryPath;

    /**
     * Idnique si le mode bouchoné est activé.
     */
    protected boolean mIsMockEnabled = true;

    /**
     * Nom de la classe de l'UI.
     */
    protected String mUiClassName;

    protected JPanel currentPanel;

    protected User currentUser = null;

    /**
     * Constructeur.
     */
    public Twitup() {
        // Init du look and feel de l'application
        this.initLookAndFeel();

        // Initialisation de la base de données
        this.initDatabase();

        if (this.mIsMockEnabled) {
            // Initialisation du bouchon de travail
            this.initMock();
        }

        // Initialisation de l'IHM
        this.initGui();

        // Initialisation du répertoire d'échange
        this.initDirectory();

        this.goToListTwitPage();
    }

    /**
     * Initialisation du look and feel de l'application.
     */
    protected void initLookAndFeel() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

    }

    /**
     * Initialisation de l'interface graphique.
     */
    protected void initGui() {
        this.mMainView = new TwitupMainView();
        this.mMainView.addObserver(this);
        mDatabase.addObserver(this);
    }

    /**
     * Initialisation du répertoire d'échange (depuis la conf ou depuis un file
     * chooser). <br/>
     * <b>Le chemin doit obligatoirement avoir été saisi et être valide avant de
     * pouvoir utiliser l'application</b>
     */
    protected void initDirectory() {
        this.initDirectory("src/main/resources");
    }

    /**
     * Indique si le fichier donné est valide pour servire de répertoire
     * d'échange
     *
     * @param directory , Répertoire à tester.
     */
    protected boolean isValideExchangeDirectory(File directory) {
        // Valide si répertoire disponible en lecture et écriture
        return directory != null && directory.exists() && directory.isDirectory() && directory.canRead()
                && directory.canWrite();
    }

    /**
     * Initialisation du mode bouchoné de l'application
     */
    protected void initMock() {
        TwitupMock mock = new TwitupMock(this.mDatabase, this.mEntityManager);
        mock.showGUI();
    }

    /**
     * Initialisation de la base de données
     */
    protected void initDatabase() {
        mDatabase = new Database();
        mEntityManager = new EntityManager(mDatabase);
    }

    /**
     * Initialisation du répertoire d'échange.
     *
     * @param directoryPath
     */
    public void initDirectory(String directoryPath) {
        mExchangeDirectoryPath = directoryPath;
        mWatchableDirectory = new WatchableDirectory(directoryPath);
        mEntityManager.setExchangeDirectory(directoryPath);

        mWatchableDirectory.initWatching();
        mWatchableDirectory.addObserver(mEntityManager);
    }

    public void show() {
        this.mMainView.showGUI();
    }

    public void changeCurrentPanelMainView(JPanel panel) {
        if (this.currentPanel != null) this.mMainView.remove(this.currentPanel);
        this.currentPanel = panel;
        this.mMainView.add(currentPanel);
        this.mMainView.revalidate();
        this.mMainView.repaint();
    }

    @Override
    public void notifyDirectoryChanged(File file) {
        Twitup.this.initDirectory(file.getAbsolutePath());
    }

    @Override
    public void goToConnexionPage() {
        TwitConnexionView twitConnexionView = new TwitConnexionView();
        this.changeCurrentPanelMainView(twitConnexionView);
        twitConnexionView.addObserver(new ConnexionController(this.mDatabase, this.mEntityManager));
    }

    @Override
    public void goToInscriptionPage() {
        TwitupCreateAccount twitupCreateAccount = new TwitupCreateAccount();
        this.changeCurrentPanelMainView(twitupCreateAccount);
        twitupCreateAccount.addObserver(new CreateAccountController(this.mDatabase, this.mEntityManager));
    }

    @Override
    public void goToProfilPage() {
        TwitupProfil twitupProfil = new TwitupProfil(this.currentUser);
        this.changeCurrentPanelMainView(twitupProfil);
        twitupProfil.addObserver(new ProfilController(this.mDatabase, this.mEntityManager));
    }

    @Override
    public void deconnectUser() {
        this.currentUser = null;
        this.mMainView.initMenuBar(null);
        this.goToInscriptionPage();
    }

    @Override
    public void goToListUserPage() {
        TwitupListUser twitupListUser = new TwitupListUser(this.mDatabase.getUsers());
        this.changeCurrentPanelMainView(twitupListUser);
        twitupListUser.addObserver(new ListUserController(this.mDatabase, this.mEntityManager));
    }

    @Override
    public void goToListTwitPage() {
        TwitupListTwit twitupListTwit = new TwitupListTwit(this.mDatabase.getTwits(), new TwitController(this.mDatabase, this.mEntityManager, this.currentUser));
        this.changeCurrentPanelMainView(twitupListTwit);
        twitupListTwit.addObserver(new ListTwitController(this.mDatabase, this.mEntityManager));
    }

    @Override
    public void goToAddTwitPage() {
        AddTwitView addTwitView = new AddTwitView();
        this.changeCurrentPanelMainView(addTwitView);
        addTwitView.addObserver(new AddTwitController(this.mDatabase, this.mEntityManager, this.currentUser));
    }

    @Override
    public void notifyTwitAdded(Twit addedTwit) {

    }

    @Override
    public void notifyLoggedUser(User user) {
        this.currentUser = user;
        this.mMainView.initMenuBar(user);
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