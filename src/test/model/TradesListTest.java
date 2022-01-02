package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TradesListTest {

    TradesList lot;

    // all Trade instances being used for testing
    Trade t1 = new Trade("EU", 10, 10, 15, "win", 150, "Tue");
    Trade t2 = new Trade("GU", 10, 15, 15, "loss", 500, "Tue");
    Trade t3 = new Trade("Spooz", 5, 20, 15, "breakeven", 150, "Tue");
    Trade t4 = new Trade("EU", 10, 15, 15, "loss", 720, "Tue");
    Trade t5 = new Trade("EU", 10, 15, 15, "win", 1050, "Tue");
    Trade t6 = new Trade("Spooz", 5, 20, 15, "win", 570, "Tue");
    Trade t7 = new Trade("Dow", 5, 20, 15, "loss", 910, "Tue");
    Trade t8 = new Trade("EU", 10, 8, 15, "win", 150, "Tue");

    @BeforeEach
    void setup() {
        lot = new TradesList();
    }

    @Test
    void testAddTrade() {
        assertEquals(0, lot.length());
        lot.addTrade(t1);
        assertEquals(1, lot.length());
        lot.addTrade(t2);
        lot.addTrade(t3);
        lot.addTrade(t4);
        assertEquals(4, lot.length());
    }

    @Test
    void testViewTradesEmpty() {
        assertEquals(new ArrayList<String>(), lot.viewTrades());
    }

    @Test
    void testViewTradesOneTrade() {
        lot.addTrade(t1);
        String t1Answer = "You traded EU on Tue in London Open for a win result, making 1RR. " +
                "Your trade lasted 15 minutes.\n";
        List<String> testAnswer = new ArrayList<>();
        testAnswer.add(t1Answer);
        assertEquals(testAnswer, lot.viewTrades());
    }

    @Test
    void testViewTradesMultipleTrades() {
        lot.addTrade(t1);
        String t1Answer = "You traded EU on Tue in London Open for a win result, making 1RR. " +
                "Your trade lasted 15 minutes.\n";
        lot.addTrade(t2);
        String t2Answer = "You traded GU on Tue in New York Open for a loss result, making -1RR. " +
                "Your trade lasted 15 minutes.\n";
        lot.addTrade(t3);
        String t3Answer = "You traded Spooz on Tue in London Open for a breakeven result, making 0RR. " +
                "Your trade lasted 15 minutes.\n";
        List<String> testAnswer = new ArrayList<>();
        testAnswer.add(t1Answer);
        testAnswer.add(t2Answer);
        testAnswer.add(t3Answer);
        assertEquals(testAnswer, lot.viewTrades());
    }

    @Test
    void testWinRate() {
        lot.addTrade(t1);
        assertEquals(100, lot.winRate());
        lot.addTrade(t2);
        assertEquals(100 / 2, lot.winRate());
        lot.addTrade(t3);
        assertEquals(100 / 2, lot.winRate());
        lot.addTrade(t4);
        assertEquals(1.0 / 3.0 * 100.0, lot.winRate());
        lot.addTrade(t5);
        assertEquals(100 / 2, lot.winRate());
        lot.addTrade(t6);
        assertEquals(60, lot.winRate());
    }

    @Test
    void testCalculateProfit() {
        assertEquals(0, lot.calculateProfit());
        lot.addTrade(t1);
        assertEquals(1, lot.calculateProfit());
        lot.addTrade(t2);
        assertEquals(0, lot.calculateProfit());
        lot.addTrade(t3);
        assertEquals(0, lot.calculateProfit());
        lot.addTrade(t4);
        assertEquals(-1, lot.calculateProfit());
        lot.addTrade(t5);
        assertEquals(0, lot.calculateProfit());
        lot.addTrade(t6);
        assertEquals(4, lot.calculateProfit());
        lot.addTrade(t7);
        assertEquals(3, lot.calculateProfit());
        lot.addTrade(t8);
        assertEquals(3, lot.calculateProfit());
    }

    @Test
    void testGetWins() {
        lot.addTrade(t1);
        lot.addTrade(t2);
        lot.addTrade(t3);
        lot.addTrade(t4);
        lot.addTrade(t5);
        lot.addTrade(t6);
        lot.addTrade(t7);
        lot.addTrade(t8);

        assertEquals(4, lot.getWins());
    }

    @Test
    void testGetLosses() {
        lot.addTrade(t1);
        lot.addTrade(t2);
        lot.addTrade(t3);
        lot.addTrade(t4);
        lot.addTrade(t5);
        lot.addTrade(t6);
        lot.addTrade(t7);
        lot.addTrade(t8);

        assertEquals(3, lot.getLosses());
    }

}
