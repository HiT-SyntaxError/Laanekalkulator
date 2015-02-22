package no.hit.laanekalkulator;

import junit.framework.TestCase;

public class LaanTest extends TestCase {

    // Testverdier er hentet fra http://www.laanekalkulator.no/

    public void testSerialTerminBelop() throws Exception {
        Laan laan = new Laan(1000000, 20, 12, Laan.Lanetype.SERIE, 3.2f);
        laan.lagPlan();
        Termin termin10 = laan.getTermin(10);
        assertEquals(6734, termin10.getTerminbelop());
    }

    public void testAnnuitetTerminBelop() throws Exception {
        Laan laan = new Laan(1400000, 25, 6, Laan.Lanetype.ANNUITET, 8f);
        laan.lagPlan();
        Termin termin1 = laan.getTermin(1);
        Termin termin2 = laan.getTermin(2);
        assertEquals(21633, termin1.getTerminbelop());
        assertEquals(termin2.getTerminbelop(), termin1.getTerminbelop());
    }

    public void testSerialRestgjeld() throws Exception {
        Laan laan = new Laan(1000000, 20, 12, Laan.Lanetype.SERIE, 3.2f);
        laan.lagPlan();
        Termin termin10 = laan.getTermin(10);
        assertEquals(958330, termin10.getRestgjeld());
    }

    public void testAnnuitetRenter() throws Exception {
        Laan laanNuitet = new Laan(1500000, 15, 4, Laan.Lanetype.ANNUITET, 3.2f);
        laanNuitet.lagPlan();
        Termin termin12 = laanNuitet.getTermin(12);
        assertEquals(10207, termin12.getRenter());
    }

    public void testAnnuitetAvdrag() throws Exception {
        Laan laanNuitet = new Laan(1500000, 15, 4, Laan.Lanetype.ANNUITET, 3.2f);
        laanNuitet.lagPlan();
        Termin termin58 = laanNuitet.getTermin(58);
        assertEquals(30830, termin58.getAvdrag());
    }

    // Test that restgjeld is 0 after last payment
    public void testSerialRestgjeld2() throws Exception {
        Laan laan = new Laan(1000000, 20, 12, Laan.Lanetype.SERIE, 3.2f);
        laan.lagPlan();
        Termin termin240 = laan.getTermin(240);
        assertEquals(0, termin240.getRestgjeld());
    }

    public void testSerialAvdragLast() throws Exception {
        Laan laan = new Laan(1000000, 20, 12, Laan.Lanetype.SERIE, 3.2f);
        laan.lagPlan();
        Termin termin240 = laan.getTermin(240);
        assertEquals(4087, termin240.getAvdrag());
    }

    public void testEnumConverter() throws Exception {
        assertEquals(Laan.Lanetype.ANNUITET, Laan.Lanetype.getByValue("Annuitetslån"));
        assertEquals(Laan.Lanetype.SERIE, Laan.Lanetype.getByValue("Serielån"));
    }
}
