package com.iup.tp.twitup.ihm.components.twit;

import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.ihm.IMainOberserver;
import com.iup.tp.twitup.ihm.IViewObservable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Date;

public class TwitupTwit extends JPanel implements IViewObservable<ITwitObserver> {

    protected Twit twit;
    protected ITwitObserver observer;
    protected IMainOberserver mainOberserver;

    public TwitupTwit(Twit twit, ITwitObserver observe, IMainOberserver mainOberserver) {
        this.twit = twit;
        this.mainOberserver = mainOberserver;
        this.addObserver(observe);
        initGUI();
    }

    /**
     * Initialisation de l'IHM
     */
    protected void initGUI() {
        Font font = new Font("Roboto", Font.BOLD, 12);
        this.removeAll();

        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        this.setBackground(Color.WHITE);


        JLabel jlblTitle = new JLabel(this.twit.getTwiter().getName() + " @" + this.twit.getTwiter().getUserTag());
        jlblTitle.setFont(font);
        ImageIcon imageIcon = new ImageIcon(this.twit.getTwiter().getAvatarPath()); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        jlblTitle.setIcon(new ImageIcon(newimg));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        this.add(jlblTitle, c);

        JLabel jlblDate = new JLabel(new Date(this.twit.getEmissionDate()).toString(), SwingConstants.RIGHT);
        jlblDate.setFont(font);
        c.gridx = 1;
        c.gridy = 0;
        this.add(jlblDate, c);

        JLabel jlblText = new JLabel(this.twit.getText(), SwingConstants.CENTER);
        jlblText.setFont(font);
        c.gridx = 0;
        c.gridy = 2;
        this.add(jlblText, c);

        JButton profilBtn = new JButton("Consulter le profil");
        profilBtn.addActionListener(e -> TwitupTwit.this.mainOberserver.goToTwitterProfilePage(twit.getTwiter()));
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        this.add(profilBtn, c);
    }

    @Override
    public void addObserver(ITwitObserver observer) {
        this.observer = observer;
    }

    @Override
    public void deleteObserver(ITwitObserver observer) {
        this.observer = null;
    }
}
