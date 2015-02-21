package no.hit.laanekalkulator;

import java.io.Serializable;

public class Termin implements Serializable {

    public static final String TERMINNR = "terminnr";
    public static final String RENTER = "renter";
    public static final String AVDRAG = "avdrag";
    public static final String RESTGJELD = "restgjeld";
    public static final String TERMINBELOP = "terminbelop";
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

    public int getTerminNr() {
        return terminnr;
    }

    public int getFieldCount() {
        /*
        1. int terminnr
        2. int renter
        3. long avdrag
        4. long restgjeld
        5. long getTerminbelop()
        */
        return 5;
    }
}
