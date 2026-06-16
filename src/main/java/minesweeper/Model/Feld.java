package minesweeper.Model;

import java.util.Random;

public class Feld {
    private final int reihen;
    private final int spalten;
    private final int anzahlMinen;
    private final Zelle[][] zellen;
    private boolean minenPlatziert;

    public Feld(int reihen, int spalten, int anzahlMinen) {
        this.reihen = reihen;
        this.spalten = spalten;
        this.anzahlMinen = anzahlMinen;
        this.zellen = new Zelle[reihen][spalten];
        this.minenPlatziert = false;

        for (int r = 0; r < reihen; r++) {
            for (int s = 0; s < spalten; s++) {
                zellen[r][s] = new Zelle();
            }
        }
    }

    public int getReihen() {
        return reihen;
    }

    public int getSpalten() {
        return spalten;
    }

    public Zelle getZelle(int r, int s) {
        return zellen[r][s];
    }

    public void platziereMinenAusserhalbVon(int schutzReihe, int schutzSpalte) {
        Random zufall = new Random();
        int platziert = 0;

        while (platziert < anzahlMinen) {
            int r = zufall.nextInt(reihen);
            int s = zufall.nextInt(spalten);

            boolean istSchutzzone = Math.abs(r - schutzReihe) <= 1 && Math.abs(s - schutzSpalte) <= 1;

            if (!zellen[r][s].istMine() && !istSchutzzone) {
                zellen[r][s].setMine(true);
                platziert++;
            }
        }

        berechneNachbarminen();
        minenPlatziert = true;
    }

    private void berechneNachbarminen() {
        for (int r = 0; r < reihen; r++) {
            for (int s = 0; s < spalten; s++) {
                if (!zellen[r][s].istMine()) {
                    zellen[r][s].setNachbarminen(zaehleNachbarminen(r, s));
                }
            }
        }
    }

    private int zaehleNachbarminen(int r, int s) {
        int anzahl = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int ds = -1; ds <= 1; ds++) {
                if (dr == 0 && ds == 0) continue;
                int nr = r + dr, ns = s + ds;
                if (istGueltig(nr, ns) && zellen[nr][ns].istMine()) {
                    anzahl++;
                }
            }
        }
        return anzahl;
    }

    private boolean istGueltig(int r, int s) {
        return r >= 0 && r < reihen && s >= 0 && s < spalten;
    }

    public boolean sindMinenPlatziert() {
        return minenPlatziert;
    }

    public void aufdecken(int r, int s) {
        if (!istGueltig(r, s)) return;
        Zelle zelle = zellen[r][s];
        if (zelle.istAufgedeckt() || zelle.istGeflaggt()) return;

        zelle.aufdecken();

        if (zelle.getNachbarminen() == 0 && !zelle.istMine()) {
            for (int dr = -1; dr <= 1; dr++) {
                for (int ds = -1; ds <= 1; ds++) {
                    if (dr == 0 && ds == 0) continue;
                    aufdecken(r + dr, s + ds);
                }
            }
        }
    }

    public boolean istGewonnen() {
        for (int r = 0; r < reihen; r++) {
            for (int s = 0; s < spalten; s++) {
                Zelle z = zellen[r][s];
                if (!z.istMine() && !z.istAufgedeckt()) {
                    return false;
                }
            }
        }
        return true;
    }
}
