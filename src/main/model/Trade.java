package model;

import org.json.JSONObject;
import persistence.Writable;

public class Trade implements Writable {

    // fields
    private String instrument; // currency/pair/index traded
    private int sl; // size of stop loss in pips
    private int tp; // side of take profit in pips
    private int dur; // duration of trade in minutes
    private String result; // "win", "loss", or "breakeven"
    private int time; // time executed in minutes from midnight (EST)
    private String day; // day of week traded

    // constants
    private int londonStart = 120;
    private int londonEnd = 300;
    private int newYorkStart = 420;
    private int newYorkEnd = 600;
    private int londonCloseStart = 600;
    private int londonCloseEnd = 720;

    // REQUIRES: instrument is either "EU", "GU", "Spooz", or "Dow"
    // sl, tp, dur cannot be 0
    // result must be either "win", "loss", "be"
    // time must be within 0 and 1439
    // day must be either "Mon", "Tue", "Wed", "Thu", "Fri"
    // MODIFIES: this
    // EFFECTS: constructor for Trade
    public Trade(String instrument, int sl, int tp, int dur, String result, int time, String day) {
        this.instrument = instrument;
        this.sl = sl;
        this.tp = tp;
        this.dur = dur;
        this.result = result;
        this.time = time;
        this.day = day;
    }

    // EFFECTS: calculates reward to risk ratio of trades
    public int rewardToRisk() {
        return tp / sl;
    }

    // Explanation of trading sessions
    // London Open is from 2 to 5 am
    // New York Open is from 7 to 10 am
    // London Close is from 10 am to 12 pm
    // Everything else is deemed out of session
    // EFFECTS: depending on time of trade taken, will return a string with name of session
    public String sessionTraded() {
        if (time >= londonStart && time <= londonEnd) {
            return "London Open";
        } else if (time >= newYorkStart && time <= newYorkEnd) {
            return "New York Open";
        } else if (time >= londonCloseStart && time <= londonCloseEnd) {
            return "London Close";
        } else {
            return "No Session";
        }
    }

    // EFFECTS: calculates how much profit/loss trade resulted in terms of RR
    public int unrealizedGainOrLoss() {
        if (result.equals("win")) {
            return rewardToRisk();
        } else if (result.equals("loss")) {
            return -1;
        } else {
            return 0;
        }
    }

    public String getInstrument() {
        return instrument;
    }

    public String getDay() {
        return day;
    }

    public String getResult() {
        return result;
    }

    public int getDur() {
        return dur;
    }

    public int getSL() {
        return sl;
    }

    public int getTP() {
        return tp;
    }

    public int getTime() {
        return time;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("instrument", instrument);
        json.put("stop loss", sl);
        json.put("take profit", tp);
        json.put("duration", dur);
        json.put("result", result);
        json.put("time of day", time);
        json.put("day of week", day);
        return json;
    }

}
