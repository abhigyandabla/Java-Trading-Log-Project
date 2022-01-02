package persistence;

import model.Trade;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Code is inspired by JsonSerializationDemo, GitHub link below
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonTest {
    protected void checkTrade(String instrument, int sl, int tp, int dur, String result,
                              int time, String day, Trade t) {
        assertEquals(instrument, t.getInstrument());
        assertEquals(sl, t.getSL());
        assertEquals(tp, t.getTP());
        assertEquals(dur, t.getDur());
        assertEquals(result, t.getResult());
        assertEquals(time, t.getTime());
        assertEquals(day, t.getDay());

    }
}
