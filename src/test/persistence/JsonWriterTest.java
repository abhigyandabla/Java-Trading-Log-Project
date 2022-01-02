package persistence;

import model.Trade;
import model.TradesList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Code is inspired by JsonSerializationDemo, GitHub link below
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            TradesList trades = new TradesList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTradesList() {
        try {
            TradesList trades = new TradesList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTradesList.json");
            writer.open();
            writer.write(trades);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTradesList.json");
            trades = reader.read();
            assertEquals(0, trades.length());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            TradesList trades = new TradesList();
            trades.addTrade(new Trade("EU", 10, 20, 30, "win", 170, "Wed"));
            trades.addTrade(new Trade("Dow", 10, 20, 30, "loss", 800, "Thu"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(trades);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            trades = reader.read();
            List<Trade> tl = trades.getTrades();
            assertEquals(2, trades.length());
            checkTrade("EU", 10, 20, 30, "win", 170, "Wed", tl.get(0));
            checkTrade("Dow", 10, 20, 30, "loss", 800, "Thu", tl.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}