package no.hit.laanekalkulator;

import junit.framework.TestCase;

import java.util.List;

public class LanTest extends TestCase {

    public void testSerialTerminBelop() throws Exception {
        Lan lan = new Lan(1000000, 20, 12, Lan.Lanetype.SERIE, 3.2f);
        lan.lagPlan();
        Termin termin10 = lan.getTermin(10);
        assertEquals(6734, termin10.getTerminbelop());
    }

    public void testAnnuitetTerminBelop() throws Exception {
        Lan lan = new Lan(1400000, 25, 6, Lan.Lanetype.ANNUITET, 8f);
        lan.lagPlan();
        Termin termin1 = lan.getTermin(1);
        Termin termin2 = lan.getTermin(2);
        assertEquals(21633, termin1.getTerminbelop());
        assertEquals(termin2.getTerminbelop(), termin1.getTerminbelop());
    }

    public void testSerialRestgjeld() throws Exception {
        Lan lan = new Lan(1000000, 20, 12, Lan.Lanetype.SERIE, 3.2f);
        lan.lagPlan();
        Termin termin10 = lan.getTermin(10);
        assertEquals(958330, termin10.getRestgjeld());
    }
//    public void testSerialTerminBelop2() throws Exception {
//        Lan lan = new Lan(1000000, 20, 12, Lan.Lanetype.SERIE, 3.2f);
//        lan.lagPlan();
//        Termin termin240 = lan.getTermin(240);
//        assertEquals(4178, termin240.getTerminbelop());
//    }

    public void testAnnuitetRenter() throws Exception {
        Lan lanNuitet = new Lan(1500000, 15, 4, Lan.Lanetype.ANNUITET, 3.2f);
        lanNuitet.lagPlan();
        Termin termin12 = lanNuitet.getTermin(12);
        assertEquals(10207, termin12.getRenter());
    }

    public void testAnnuitetAvdrag() throws Exception {
        Lan lanNuitet = new Lan(1500000, 15, 4, Lan.Lanetype.ANNUITET, 3.2f);
        lanNuitet.lagPlan();
        Termin termin58 = lanNuitet.getTermin(58);
        assertEquals(30830, termin58.getAvdrag());
    }

    // Test that restgjeld is 0 after last payment
    public void testSerialRestgjeld2() throws Exception {
        Lan lan = new Lan(1000000, 20, 12, Lan.Lanetype.SERIE, 3.2f);
        lan.lagPlan();
        Termin termin240 = lan.getTermin(240);
        assertEquals(0, termin240.getRestgjeld());
    }

    //    public void testAnnuitetTerminBelopLast() throws Exception {
//        Lan lanNuitet = new Lan(1500000, 15, 4, Lan.Lanetype.ANNUITET, 3.2f);
//        lanNuitet.lagPlan();
//        Termin termin60 = lanNuitet.getTermin(60);
//        assertEquals(31574, termin60.getTerminbelop());
//    }
    public void testSerialAvdragLast() throws Exception {
        Lan lan = new Lan(1000000, 20, 12, Lan.Lanetype.SERIE, 3.2f);
        lan.lagPlan();
        Termin termin240 = lan.getTermin(240);
        assertEquals(4087, termin240.getAvdrag());
    }

    public void testEnumConverter() throws Exception {
        assertEquals(Lan.Lanetype.ANNUITET, Lan.Lanetype.getByValue("Annuitetslån"));
        assertEquals(Lan.Lanetype.SERIE, Lan.Lanetype.getByValue("Serielån"));
    }

    public void testTerminGroups() throws Exception {
        Lan lan = new Lan(1000000, 20, 12, Lan.Lanetype.SERIE, 3.2f);
        List<TerminGroup> terminGroups = lan.getTerminGroups();
        TerminGroup terminGroup = terminGroups.get(1);
        long termin1 = lan.getTermin(1).getTerminbelop();
        long termin2 = lan.getTermin(2).getTerminbelop();
        long termin3 = lan.getTermin(3).getTerminbelop();
        long termin4 = lan.getTermin(4).getTerminbelop();
        long termin5 = lan.getTermin(5).getTerminbelop();
        long termin6 = lan.getTermin(6).getTerminbelop();
        long termin7 = lan.getTermin(7).getTerminbelop();
        long termin8 = lan.getTermin(8).getTerminbelop();
        long termin9 = lan.getTermin(9).getTerminbelop();
        long termin10 = lan.getTermin(10).getTerminbelop();
        long termin11 = lan.getTermin(11).getTerminbelop();
        long termin12 = lan.getTermin(12).getTerminbelop();
        long sum = termin1 + termin2 + termin3 + termin4 + termin5 + termin6 + termin7 + termin8 + termin9 + termin10 + termin11 + termin12;
        assertEquals(sum, terminGroup.getSumTerminbelop());
    }

}