package no.hit.laanekalkulator;

import java.util.ArrayList;
import java.util.EnumSet;

public class Lan {


    public enum Lanetype {SERIE("Serielån"), ANNUITET("Annuitetslån");

        private String tekst;

        Lanetype(String value) {
            this.tekst = value;
        }

        public String getTekst() {
            return tekst;
        }

        public static Lanetype getByValue(String value){
            for (final Lanetype element : EnumSet.allOf(Lanetype.class)) {
                if (element.toString().equals(value)) {
                    return element;
                }
            }
            return null;
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

    @Override
    public String toString() {
        return this.lanetype + "\n" +
               this.lanebelop + "\n" +
               this.lopetid + "\n" +
               this.arligeTerminer + "\n" +
               this.rentesats;
    }
}
