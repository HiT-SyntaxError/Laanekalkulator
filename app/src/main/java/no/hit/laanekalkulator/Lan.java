package no.hit.laanekalkulator;

import java.util.ArrayList;

public class Lan {


    public enum Lanetype {SERIE("Serielån"), ANNUITET("Annuitetslån");

        private String tekst;

        Lanetype(String value) {
            this.tekst = value;
        }

        public String getTekst() {
            return tekst;
        }
    }

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

    public static String[] getLanetyper() {
        ArrayList<String> lanetyper = new ArrayList<String>();
        for (Lanetype lanetype : Lanetype.values()) {
            lanetyper.add(lanetype.getTekst());
        }
        String[] lt = new String[lanetyper.size()];
        return lanetyper.toArray(lt);
    }

    public void lagPlan() {
        Termin[] terminer = new Termin[lopetid*arligeTerminer];
    }

    public void termingebyr() {

    }
}
