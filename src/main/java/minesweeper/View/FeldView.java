package minesweeper.View;

import minesweeper.Model.Zelle;
import minesweeper.Model.Feld;

import javax.swing.*;
import java.awt.*;

    public class FeldView extends JFrame {
    private final JButton[][] buttons;
    private final JLabel timerLabel;
    private final JButton loesenButton;
    private final JButton neustartButton;
    private final int reihen;
    private final int spalten;

    public FeldView(int reihen, int spalten) {
        this.reihen = reihen;
        this.spalten = spalten;
        this.buttons = new JButton[reihen][spalten];

        this.timerLabel = new JLabel("Timer: 0");
        this.loesenButton = new JButton("Lösen //funktioniert noch nicht");
        this.neustartButton = new JButton("Neustart");

        Timer timer = new Timer(1000, e -> {
            String text = this.timerLabel.getText();
            int sekunden = Integer.parseInt(text.replaceAll("\\D+", ""));
            this.timerLabel.setText("Timer: " + (sekunden + 1));
        });
        timer.start();

        setTitle("Minesweeper");
        setLayout(new BorderLayout());
        setSize(spalten * 40 + 100, reihen * 40);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel feldPanel = new JPanel(new GridLayout(reihen, spalten));
        for (int r = 0; r < reihen; r++) {
            for (int s = 0; s < spalten; s++) {
                JButton button = new JButton();
                button.setMargin(new Insets(0, 0, 0, 0));
                buttons[r][s] = button;
                feldPanel.add(button);
            }
        }
        add(feldPanel, BorderLayout.CENTER);

        JPanel seitenPanel = new JPanel();
        seitenPanel.setLayout(new BoxLayout(seitenPanel, BoxLayout.Y_AXIS));

        seitenPanel.add(timerLabel);
        seitenPanel.add(loesenButton);
        seitenPanel.add(neustartButton);

        add(seitenPanel, BorderLayout.EAST);
    }

    public JButton getNeustartButton() {
        return neustartButton;
    }

    public JButton getLoesenButton() {
        return loesenButton;
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
