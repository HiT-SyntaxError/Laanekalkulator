package no.hit.laanekalkulator;

public class Termin {

    private int terminnr;
    private float avdrag;
    private float renter;
    private float restgjeld;

    public Termin(int terminnr, float avdrag, float renter, float restgjeld) {
        this.terminnr = terminnr;
        this.avdrag = avdrag;
        this.renter = renter;
        this.restgjeld = restgjeld;
    }

    public float terminBelop() {
        return 0; // TODO: Implement method
    }

}
