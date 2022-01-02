package persistence;

import model.Trade;
import model.TradesList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Code is inspired by JsonSerializationDemo, GitHub link below
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TradesList tl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTradesList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTradesList.json");
        try {
            TradesList trades = reader.read();
            assertEquals(0, trades.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTradesList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTradesList.json");
        try {
            TradesList tl = reader.read();
            List<Trade> trades = tl.getTrades();
            assertEquals(2, trades.size());
            checkTrade("EU", 20, 40, 30, "win", 120, "Fri", trades.get(0));
            checkTrade("GU", 90, 110, 100, "loss", 800, "Tue", trades.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}