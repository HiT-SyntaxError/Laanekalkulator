package no.hit.laanekalkulator;

import java.io.Serializable;

public class TerminGroup implements Serializable {

    private int fromTerminnr;
    private int toTerminnr;
    private int terminCount = 0;
    private int sumRenter = 0;
    private long sumAvdrag = 0;
    private long sumRestgjeld = 0;

    public TerminGroup add(Termin termin) {
        this.fromTerminnr = Math.min(termin.getTerminNr(),fromTerminnr);
        this.toTerminnr = Math.max(termin.getTerminNr(), fromTerminnr);
        this.sumAvdrag += termin.getAvdrag();
        this.sumRenter += termin.getRenter();
        this.sumRestgjeld += termin.getRestgjeld();
        this.terminCount += 1;
        return this;
    }

    public int getFromTerminnr() {
        return fromTerminnr;
    }

    public int getToTerminnr() {
        return toTerminnr;
    }

    public int getTerminCount() {
        return terminCount;
    }

    public int getSumRenter() {
        return sumRenter;
    }

    public long getSumAvdrag() {
        return sumAvdrag;
    }

    public long getSumTerminbelop() {
        return sumAvdrag + sumRenter;
    }

    public long getSumRestgjeld() {
        return sumRestgjeld;
    }
}
