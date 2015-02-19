package no.hit.laanekalkulator;

public class Lan {

    public enum Lanetype {SERIE, ANNUITET}

    private int lanebelop;
    private int lopetid;
    private int arligeTerminer;
    private Lanetype lanetype;
    private float rentesats;

    public Lan(int lanebelop, int lopetid, int arligeTerminer, Lanetype lanetype, float rentesats) {
        this.lanebelop = lanebelop;
        this.lopetid = lopetid;
        this.arligeTerminer = arligeTerminer;
        this.lanetype = lanetype;
        this.rentesats = rentesats;
    }

    public void lagPlan() {
        Termin[] terminer = new Termin[lopetid*arligeTerminer];
    }

    public void termingebyr() {

    }
}
