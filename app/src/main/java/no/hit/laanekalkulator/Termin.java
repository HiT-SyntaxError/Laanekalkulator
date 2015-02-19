package no.hit.laanekalkulator;

public class Termin {

    private final int terminnr;
    private int renter;
    private long avdrag;
    private final long restgjeld;

    public static Termin getSerieTermin(int terminnr, long avdrag, int renter, long restgjeld) {
        Termin termin = new Termin(terminnr, restgjeld);
        termin.avdrag = avdrag;
        termin.renter = renter;
        return termin;
    }

    public static Termin getAnnuitetsTermin(int terminnr, long terminbelop, int renter, long restgjeld) {
        Termin termin = new Termin(terminnr, restgjeld);
        termin.renter = renter;
        termin.avdrag = terminbelop - renter;
        return termin;
    }

    public Termin(int terminnr, long restgjeld) {
        this.terminnr = terminnr;
        this.restgjeld = restgjeld;
    }

    public long getRestgjeld() {
        return restgjeld;
    }

    public long getAvdrag() {
        return avdrag;
    }

    public long getTerminbelop() {
        return avdrag + renter;
    }

    public int getRenter() {
        return renter;
    }
}
