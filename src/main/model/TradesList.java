package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class TradesList implements Writable {

    private List<Trade> listOfTrade;

    // EFFECTS: constructs an empty list of trades
    public TradesList() {
        listOfTrade = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds trade to listOfTrade
    public void addTrade(Trade t) {
        EventLog.getInstance().logEvent(new Event("Trade on " + t.getInstrument() + " added to TradesList."));
        listOfTrade.add(t);
    }

    // EFFECTS: prints out message for each trade taken in list
    public List<String> viewTrades() {
        EventLog.getInstance().logEvent(new Event("Summary of TradesList was made."));
        List<String> resume = new ArrayList<>();

        if (listOfTrade.isEmpty()) {
            return new ArrayList<>();
        } else {
            for (Trade t : listOfTrade) {
                resume.add("You traded " + t.getInstrument() + " on " + t.getDay()
                        + " in " + t.sessionTraded() + " for a " + t.getResult() + " result, making "
                        + t.unrealizedGainOrLoss() + "RR. Your trade lasted " + t.getDur() + " minutes.\n");
            }
            return resume;
        }
    }

    // REQUIRES: there is at least one win or loss in listOfTrade
    // EFFECTS: calculates percentage of trades won out of all trades excluding the breakeven ones
    public double winRate() {
        EventLog.getInstance().logEvent(new Event("Win-rate of TradesList was calculated."));

        double wins = 0.0;
        double losses = 0.0;

        for (Trade t : listOfTrade) {
            if (t.getResult().equals("win")) {
                wins++;
            } else if (t.getResult().equals("loss")) {
                losses++;
            }
        }
        return wins / (wins + losses) * 100;
    }

    // EFFECTS: calculates net reward to risk ratio of trades in listOfTrade
    public int calculateProfit() {
        int rr = 0;

        for (Trade t : listOfTrade) {
            rr = rr + t.unrealizedGainOrLoss();
        }

        EventLog.getInstance().logEvent(new Event("Profit of TradesList was calculated."));
        return rr;
    }

    // EFFECTS: returns size of TradesList
    public int length() {
        return listOfTrade.size();
    }

    // EFFECTS: returns number of wins out of list of trades
    public int getWins() {
        int wins = 0;
        for (Trade t : listOfTrade) {
            if (t.getResult().equals("win")) {
                wins++;
            }
        }
        return wins;
    }

    // EFFECTS: returns number of losses out of listOTrade
    public int getLosses() {
        int losses = 0;
        for (Trade t : listOfTrade) {
            if (t.getResult().equals("loss")) {
                losses++;
            }
        }
        return losses;
    }

    public List<Trade> getTrades() {
        return listOfTrade;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("trades", tradesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray tradesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Trade t : listOfTrade) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

}
