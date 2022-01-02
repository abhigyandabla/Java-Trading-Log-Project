package ui;

import model.Trade;
import model.TradesList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Code is inspired by Teller, GitHub link below
// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git

public class TradingLog {
    private static final String JSON_STORE = "./data/workroom.json";
    private Scanner input;
    private TradesList lot;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: runs the trading application
    public TradingLog() {
        init();
        runLog();
    }

    // MODIFIES: this
    // EFFECTS: initializes Scanner and TradesList
    public void init() {
        lot = new TradesList();
        input = new Scanner(System.in);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runLog() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGood luck and good trading!");
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddTrade();
        } else if (command.equals("p")) {
            doCalculateProfit();
        } else if (command.equals("w")) {
            doShowWinrate();
        } else if (command.equals("v")) {
            printTrades();
        } else if (command.equals("s")) {
            saveTradesList();
        } else if (command.equals("l")) {
            loadTradesList();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("Select from:");
        System.out.println("\ta -> add a trade");
        System.out.println("\tw -> see winrate");
        System.out.println("\tp -> check profit");
        System.out.println("\tv -> view trades");
        System.out.println("\ts -> save trades");
        System.out.println("\tl -> load trades");
        System.out.println("\tq -> quit");
    }


    // MODIFIES: this
    // EFFECTS: adds a trade to
    private void doAddTrade() {
        System.out.print("Enter instrument traded: ");
        String instrument = input.next();
        System.out.print("Enter size of stop loss in pips: ");
        int sl = input.nextInt();
        System.out.print("Enter size of take profit in pips: ");
        int tp = input.nextInt();
        System.out.print("Enter duration of trade in minutes: ");
        int dur = input.nextInt();
        System.out.print("Enter outcome as either win, loss, or breakeven: ");
        String result = input.next();
        System.out.print("Enter time of day trade was executed as minutes from midnight: ");
        int time = input.nextInt();
        System.out.print("Enter day of week traded (first three letters of day): ");
        String day = input.next();

        Trade t = new Trade(instrument, sl, tp, dur, result, time, day);
        lot.addTrade(t);

        System.out.println("\nYour trade has been added to the log.\n");
    }

    // MODIFIES: this
    // EFFECTS: calculates overall profit or loss in terms of RR
    private void doCalculateProfit() {
        System.out.println("\nYour unrealized gain or loss from your log of trades is "
                + lot.calculateProfit() + "RR.\n");
    }

    // MODIFIES: this
    // EFFECTS: calculates overall profit or loss in terms of RR
    private void doShowWinrate() {
        if (lot.getWins() == 0 && lot.getLosses() == 0) {
            System.out.println("\nYou haven't won or lost a trade yet! Log more trades and try again.\n");
        } else {
            System.out.println("\nYour winrate for your logged trades is " + lot.winRate() + "%.\n");
        }
    }

    // EFFECTS: prompts user to select an account and prints account to screen
    private void printTrades() {
        if (lot.length() == 0) {
            System.out.println("\nYou haven't logged a trade yet! Log more trades and try again.\n");
        } else {
            System.out.println("\nYour trades will be displayed below: \n");
            for (int i = 0; i < lot.length(); i++) {
                System.out.println(lot.viewTrades().get(i));
            }
        }
    }

    // EFFECTS: saves the TradesList to file
    private void saveTradesList() {
        try {
            jsonWriter.open();
            jsonWriter.write(lot);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads TradesList from file
    private void loadTradesList() {
        try {
            lot = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE + "\n");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE + "\n");
        }
    }

}
