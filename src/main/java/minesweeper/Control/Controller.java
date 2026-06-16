package minesweeper.Control;

import minesweeper.Model.Feld;
import minesweeper.View.FeldView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller {
    private final Feld feld;
    private final FeldView view;

    public Controller(Feld feld, FeldView view) {
        this.feld = feld;
        this.view = view;
        registriereListener();
    }

    private void registriereListener() {
        for (int r = 0; r < feld.getReihen(); r++) {
            for (int s = 0; s < feld.getSpalten(); s++) {
                final int row = r;
                final int col = s;
                JButton button = view.getButton(r, s);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Linksklick(row, col);
                    }
                });

                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            Rechtsklick(row, col);
                        }
                    }
                });
            }
        }
    }

    private void Linksklick(int r, int s) {
        if (!feld.sindMinenPlatziert()) {
            feld.platziereMinenAusserhalbVon(r, s);
        }

        feld.aufdecken(r, s);
        view.zeigeGanzesFeldAn(feld);

        if (feld.getZelle(r, s).istMine()) {
            JOptionPane.showMessageDialog(view, "Verloren :((");
        } else if (feld.istGewonnen()) {
            JOptionPane.showMessageDialog(view, "Gewonnen :))");
        }
    }

    private void Rechtsklick(int r, int s) {
        feld.getZelle(r, s).toggleFlagge();
        view.aktualisiereZelle(r, s, feld.getZelle(r, s));
    }
}
