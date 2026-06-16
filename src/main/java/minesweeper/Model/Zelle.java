package minesweeper.Model;

public class Zelle {
    private boolean mine;
    private boolean aufgedeckt;
    private boolean geflaggt;
    private int nachbarminen; 

    public Zelle() {
        this.mine = false;
        this.aufgedeckt = false;
        this.geflaggt = false;
        this.nachbarminen = 0;
    }

    public boolean istMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public boolean istAufgedeckt() {
        return aufgedeckt;
    }

    public void aufdecken() {
        this.aufgedeckt = true;
    }

    public boolean istGeflaggt() {
        return geflaggt;
    }

    public void toggleFlagge() {
        if (!aufgedeckt) {
            this.geflaggt = !this.geflaggt;
        }
    }

    public int getNachbarminen() {
        return nachbarminen;
    }

    public void setNachbarminen(int anzahl) {
        this.nachbarminen = anzahl;
    }
}
