package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TradeTest {

    // all Trade instances being used for testing
    Trade t1 = new Trade("EU", 10, 10, 15, "win", 150, "Tue");
    Trade t2 = new Trade("GU", 10, 15, 15, "loss", 500, "Tue");
    Trade t3 = new Trade("Spooz", 5, 20, 15, "breakeven", 150, "Tue");
    Trade t4 = new Trade("EU", 10, 15, 15, "loss", 720, "Tue");
    Trade t5 = new Trade("EU", 10, 15, 15, "win", 1050, "Tue");
    Trade t6 = new Trade("Spooz", 5, 20, 15, "win", 570, "Tue");
    Trade t7 = new Trade("Dow", 5, 20, 15, "loss", 910, "Tue");
    Trade t8 = new Trade("EU", 10, 8, 15, "win", 150, "Wed");
    Trade t9 = new Trade("EU", 10, 10, 15, "win", 80, "Tue");
    Trade t10 = new Trade("EU", 10, 10, 15, "win", 360, "Tue");
    Trade t11 = new Trade("EU", 10, 10, 15, "win", 660, "Tue");



    @Test
    void testRewardToRisk() {
        assertEquals(1, t1.rewardToRisk());
        assertEquals(1, t2.rewardToRisk());
        assertEquals(4, t3.rewardToRisk());
    }

    @Test
    void testSessionTraded() {
        assertEquals("London Open", t1.sessionTraded());
        assertEquals("New York Open", t2.sessionTraded());
        assertEquals("London Close", t4.sessionTraded());
        assertEquals("No Session", t5.sessionTraded());
        assertEquals("New York Open", t6.sessionTraded());
        assertEquals("No Session", t7.sessionTraded());
        assertEquals("No Session", t9.sessionTraded());
        assertEquals("No Session", t10.sessionTraded());
        assertEquals("London Close", t11.sessionTraded());
    }

    @Test
    void testUnrealizedGainOrLoss() {
        assertEquals(1, t1.unrealizedGainOrLoss());
        assertEquals(-1, t2.unrealizedGainOrLoss());
        assertEquals(0, t3.unrealizedGainOrLoss());
        assertEquals(4, t6.unrealizedGainOrLoss());
        assertEquals(-1, t7.unrealizedGainOrLoss());
        assertEquals(0, t8.unrealizedGainOrLoss());
    }

    @Test
    void testGetInstrument() {
        assertEquals("EU", t1.getInstrument());
        assertEquals("GU", t2.getInstrument());
    }

    @Test
    void testGetDay() {
        assertEquals("Tue", t1.getDay());
        assertEquals("Wed", t8.getDay());
    }

    @Test
    void testGetResult() {
        assertEquals("win", t1.getResult());
        assertEquals("loss", t2.getResult());
        assertEquals("breakeven", t3.getResult());
    }

    @Test
    void testGetDur() {
        assertEquals(15, t1.getDur());
    }

}