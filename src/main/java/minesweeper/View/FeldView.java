package minesweeper.View;

import minesweeper.Model.Zelle;
import minesweeper.Model.Feld;

import javax.swing.*;
import java.awt.*;

public class FeldView extends JFrame {
    private final JButton[][] buttons;
    private final int reihen;
    private final int spalten;

    public FeldView(int reihen, int spalten) {
        this.reihen = reihen;
        this.spalten = spalten;
        this.buttons = new JButton[reihen][spalten];

        setTitle("Minesweeper");
        setLayout(new GridLayout(reihen, spalten));
        setSize(spalten * 40, reihen * 40);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int r = 0; r < reihen; r++) {
            for (int s = 0; s < spalten; s++) {
                JButton button = new JButton();
                button.setMargin(new Insets(0, 0, 0, 0));
                buttons[r][s] = button;
                add(button);
            }
        }
    }

    public JButton getButton(int r, int s) {
        return buttons[r][s];
    }

    public void aktualisiereZelle(int r, int s, Zelle zelle) {
        JButton button = buttons[r][s];

        if (zelle.istAufgedeckt()) {
            button.setEnabled(false);
            if (zelle.istMine()) {
                button.setText("X");
                button.setBackground(Color.RED);
            } else if (zelle.getNachbarminen() > 0) {
                button.setText(String.valueOf(zelle.getNachbarminen()));
            } else {
                button.setText("");
            }
        } else if (zelle.istGeflaggt()) {
            button.setText("F");
        } else {
            button.setText("");
        }
    }

    public void zeigeGanzesFeldAn(Feld feld) {
        for (int r = 0; r < reihen; r++) {
            for (int s = 0; s < spalten; s++) {
                aktualisiereZelle(r, s, feld.getZelle(r, s));
            }
        }
    }
}
