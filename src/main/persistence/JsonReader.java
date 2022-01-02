package persistence;

import model.Trade;
import model.TradesList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Code is inspired by JsonSerializationDemo, GitHub link below
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Represents a reader that reads TradesList from JSON data stored in file
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TradesList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTradesList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private TradesList parseTradesList(JSONObject jsonObject) {
        TradesList tl = new TradesList();
        addTrades(tl, jsonObject);
        return tl;
    }

    // MODIFIES: wr
    // EFFECTS: parses trades from JSON object and adds them to TradesList
    private void addTrades(TradesList tl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("trades");
        for (Object json : jsonArray) {
            JSONObject nextTrade = (JSONObject) json;
            addTrade(tl, nextTrade);
        }
    }

    // MODIFIES: tl
    // EFFECTS: parses trade from JSON object and adds it to TradesList
    private void addTrade(TradesList tl, JSONObject jsonObject) {
        String instrument = jsonObject.getString("instrument");
        int sl = jsonObject.getInt("stop loss");
        int tp = jsonObject.getInt("take profit");
        int dur = jsonObject.getInt("duration");
        String result = jsonObject.getString("result");
        int time = jsonObject.getInt("time of day");
        String day = jsonObject.getString("day of week");
        Trade t = new Trade(instrument, sl, tp, dur, result, time, day);
        tl.addTrade(t);
    }

}
