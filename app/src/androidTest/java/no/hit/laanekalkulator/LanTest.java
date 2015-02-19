package no.hit.laanekalkulator;

import junit.framework.TestCase;

public class LanTest extends TestCase {

    public void testSerialTerminBelop() throws Exception {
        Lan lan = new Lan(1000000, 20, 12, Lan.Lanetype.SERIE, 3.2f);
        lan.lagPlan();
        Termin termin10 = lan.getTermin(10);
        assertEquals(6734, termin10.getTerminbelop());
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

}