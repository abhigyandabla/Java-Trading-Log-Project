package ui;

import model.Trade;
import model.TradesList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MainFrame extends FrameLayout implements ActionListener {

    private JTextField nameField;
    private JTextField slField;
    private JTextField tpField;
    private JTextField durField;
    private JTextField resultField;
    private JTextField timeField;
    private JTextField dayField;

    private JButton addTradeButton;
    private JButton saveTradesButton;
    private JButton loadTradesButton;
    private JButton seeWinRateButton;
    private JButton calculateProfitButton;
    private JButton viewTradesButton;

    private TradesList lot;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/workroom.json";

    private Title title;
    private ButtonPanel buttonPanel;
    private InputFields inputFields;

    // Constructor
    public MainFrame() {
        super();
        lot = new TradesList();
        title = new Title();
        inputFields = new InputFields();
        buttonPanel = new ButtonPanel();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        this.add(title, BorderLayout.NORTH);
        this.add(inputFields);
        this.add(buttonPanel, BorderLayout.SOUTH);
        getButtons();
        getFields();
        addListeners();
    }

    // EFFECTS: adds action listener to buttons
    private void addListeners() {
        addTradeButton.addActionListener(this);
        calculateProfitButton.addActionListener(this);
        seeWinRateButton.addActionListener(this);
        viewTradesButton.addActionListener(this);
        saveTradesButton.addActionListener(this);
        loadTradesButton.addActionListener(this);
    }

    // EFFECTS: getter method for buttons from ButtonPanel
    public void getButtons() {
        addTradeButton = buttonPanel.getAddTrade();
        calculateProfitButton = buttonPanel.getCalculateProfit();
        seeWinRateButton = buttonPanel.getSeeWinRate();
        viewTradesButton = buttonPanel.getViewTrades();
        saveTradesButton = buttonPanel.getSaveTradesList();
        loadTradesButton = buttonPanel.getLoadTradesList();
    }

    // EFFECTS: getter method for fields from inputFields
    public void getFields() {
        nameField = inputFields.getNameField();
        slField = inputFields.getSLField();
        tpField = inputFields.getTPField();
        durField = inputFields.getDurField();
        resultField = inputFields.getResultField();
        timeField = inputFields.getTimeField();
        dayField = inputFields.getDayField();
    }

    // EFFECTS: based off the button clicked, it calls a method to perform an action
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addTradeButton) {
            makeTradeAndAddToList();
        } else if (e.getSource() == calculateProfitButton) {
            calculateProfit();
        } else if (e.getSource() == seeWinRateButton) {
            seeWinRate();
        } else if (e.getSource() == viewTradesButton) {
            viewTrades();
        } else if (e.getSource() == saveTradesButton) {
            saveTrades();
        } else if (e.getSource() == loadTradesButton) {
            loadTrades();
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds trade to TradesList
    public void makeTradeAndAddToList() {
        String instrument = nameField.getText();
        int sl = Integer.parseInt(slField.getText());
        int tp = Integer.parseInt(tpField.getText());
        int dur = Integer.parseInt(durField.getText());
        String result = resultField.getText();
        int time = Integer.parseInt(timeField.getText());
        String day = dayField.getText();

        lot.addTrade(new Trade(instrument, sl, tp, dur, result, time, day));
    }

    // MODIFIES: JSON_STORE
    // EFFECTS: saves TradesList to Json
    public void saveTrades() {
        try {
            jsonWriter.open();
            jsonWriter.write(lot);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE + "\n");
        } catch (FileNotFoundException exc) {
            System.out.println("Unable to write to file: " + JSON_STORE + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads TradesList from Json
    public void loadTrades() {
        try {
            lot = jsonReader.read();
        } catch (IOException exc) {
            System.out.println("Unable to read from file: " + JSON_STORE + "\n");
        }
    }

    // EFFECTS: opens new calculate profit frame with text as a parameter
    public void calculateProfit() {
        String text = "\nYour unrealized gain or loss from your log of trades is "
                + lot.calculateProfit() + "RR.\n";
        new CalculateProfitFrame(text, lot.calculateProfit() >= 0);
    }

    // EFFECTS: opens new see winrate frame with text as a parameter
    public void seeWinRate() {
        String text;
        if (lot.getWins() == 0 && lot.getLosses() == 0) {
            text = "\nYou haven't won or lost a trade yet! Log more trades and try again.\n";
        } else {
            text = "\nYour winrate for your logged trades is " + lot.winRate() + "%.\n";
        }
        new SeeWinRateFrame(text, lot.winRate() >= 50.0);
    }

    // EFFECTS: opens new view trades frame with text as a parameter
    public void viewTrades() {
        ArrayList<String> texts = new ArrayList<>();
        if (lot.length() == 0) {
            texts.add("\nYou haven't logged a trade yet! Log more trades and try again.\n");
        } else {
            texts.add("\nYour trades will be displayed below: \n");
            for (int i = 0; i < lot.length(); i++) {
                texts.add("\n" + lot.viewTrades().get(i));
            }
        }
        new ViewTradesFrame(texts);
    }
}
