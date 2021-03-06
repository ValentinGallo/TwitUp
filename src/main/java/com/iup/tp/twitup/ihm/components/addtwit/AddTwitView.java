package com.iup.tp.twitup.ihm.components.addtwit;

import com.iup.tp.twitup.ihm.IViewObservable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AddTwitView extends JPanel implements IViewObservable<IAddTwitObserver> {

    private final JTextArea jtfTwitContent = new JTextArea();
    private final JButton jbtTwit = new JButton("Twitter");
    private final JLabel jlblStatus = new JLabel(" ");

    private IAddTwitObserver observer;

    public AddTwitView() {
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
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.setBackground(Color.ORANGE);

        JLabel jlblTitle = new JLabel("Poster un twit");
        jlblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        jlblTitle.setFont(new Font("Roboto", Font.BOLD, 12));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        this.add(jlblTitle, c);

        jtfTwitContent.setFont(new Font("Roboto", Font.CENTER_BASELINE, 11));
        jtfTwitContent.setLineWrap(true);
        c.gridx = 0;
        c.gridy++;
        this.add(jtfTwitContent, c);

        c.gridx = 0;
        c.gridy++;
        jlblStatus.setForeground(Color.RED);
        jlblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(jlblStatus, c);


        c.gridx = 0;
        c.gridy++;

        jbtTwit.addActionListener(e -> {
            if (AddTwitView.this.observer.isTwitOk(jtfTwitContent.getText())) {
                AddTwitView.this.observer.twit(jtfTwitContent.getText());
                jtfTwitContent.setText("");
                jlblStatus.setForeground(Color.GREEN);
                jlblStatus.setText("Votre Twit a été posté");
            } else {
                jlblStatus.setForeground(Color.RED);
                jlblStatus.setText("Votre message fait plus de 250 caractères");
            }
        });

        this.add(jbtTwit, c);
    }

    @Override
    public void addObserver(IAddTwitObserver observer) {
        this.observer = observer;
    }

    @Override
    public void deleteObserver(IAddTwitObserver observer) {
        // TODO document why this method is empty
    }
}
