package minesweeper;

import minesweeper.Model.Feld;
import minesweeper.View.FeldView;
import minesweeper.Control.Controller;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int reihen = 10;
            int spalten = 10;
            int minen = 15;

            Feld feld = new Feld(reihen, spalten, minen);
            FeldView view = new FeldView(reihen, spalten);
            new Controller(feld, view);

            view.setVisible(true);
        });
    }
}