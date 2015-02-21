package no.hit.laanekalkulator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class Lan implements Serializable {


    public List<Termin> getTerminer() {
        return Arrays.asList(terminer);
    }

    public enum Lanetype {ANNUITET("Annuitetslån"), SERIE("Serielån");

        private String tekst;

        Lanetype(String value) {
            this.tekst = value;
        }

        public String getTekst() {
            return tekst;
        }

        public static Lanetype getByValue(String value){
            for (final Lanetype element : EnumSet.allOf(Lanetype.class)) {
                if (element.getTekst().equals(value)) {
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
    private float renteFaktor;
    private Termin[] terminer;

    public Lan(int lanebelop, int lopetid, int arligeTerminer, Lanetype lanetype, float renteSats) {
        this.lanebelop = lanebelop;
        this.lopetid = lopetid;
        this.arligeTerminer = arligeTerminer;
        this.lanetype = lanetype;
        this.renteFaktor = renteSats / 100;
        this.lagPlan();
    }

    public static String[] getLanetyper() {
        ArrayList<String> lanetyper = new ArrayList<>();
        for (Lanetype lanetype : Lanetype.values()) {
            lanetyper.add(lanetype.getTekst());
        }
        String[] lt = new String[lanetyper.size()];
        return lanetyper.toArray(lt);
    }

    public void lagPlan() {
        terminer = new Termin[lopetid*arligeTerminer];
        long restGjeld = lanebelop;

        int totalTerminCount = arligeTerminer * lopetid;
        for(int i = 1; i <= totalTerminCount; i++) {
            Termin termin;
            int renter = calcRenter(restGjeld, renteFaktor, arligeTerminer);
            switch (lanetype) {
                case SERIE:
                    long avdrag = Math.round((float) lanebelop / (lopetid * arligeTerminer));
                    restGjeld = restGjeld - avdrag;
                    if(i == totalTerminCount) {
                        avdrag = avdrag + restGjeld;
                        restGjeld = 0;
                    }
                    termin = Termin.getSerieTermin(i, avdrag, renter, restGjeld);
                    break;
                case ANNUITET:
                default:
                    long terminbelop = Math.round((float) lanebelop * (renteFaktor / arligeTerminer) / (1 - Math.pow((1 + (renteFaktor / arligeTerminer)), -lopetid * arligeTerminer)));
                    restGjeld = restGjeld - terminbelop + renter;
                    if(i == totalTerminCount) {
                        terminbelop = terminbelop + restGjeld;
                        restGjeld = 0;
                    }
                    termin = Termin.getAnnuitetsTermin(i, terminbelop, renter, restGjeld);
                    break;
            }
            terminer[i-1] = termin;
            System.out.println("Termin " + i + " | Avdrag: " + termin.getAvdrag() + " | Renter: " + termin.getRenter() + " | Terminbeløp: " + termin.getTerminbelop() + " | Restgjeld: " + termin.getRestgjeld());
        }
    }

    private int calcRenter(long restGjeld, float renteFaktor, int arligeTerminer) {
        return Math.round((float) restGjeld * renteFaktor / arligeTerminer);
    }

    public Termin getTermin(int i) {
        return terminer[i-1];
    }

    @Override
    public String toString() {
        return this.lanetype + "\n" +
               this.lanebelop + "\n" +
               this.lopetid + "\n" +
               this.arligeTerminer + "\n" +
               this.renteFaktor;
    }
}
