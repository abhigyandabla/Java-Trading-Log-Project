package ui;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {

    private JButton addTrade;
    private JButton calculateProfit;
    private JButton seeWinRate;
    private JButton viewTrades;
    private JButton saveTradesList;
    private JButton loadTradesList;

    // Constructor
    public ButtonPanel() {
        this.setPreferredSize(new Dimension(450, 80));
        addTrade = new JButton("Add Trade");
        addTrade.setFont(new Font("Sans-serif", Font.PLAIN, 20));
        this.add(addTrade);
        calculateProfit = new JButton("Calculate Net Profit");
        calculateProfit.setFont(new Font("Sans-serif", Font.PLAIN, 20));
        this.add(calculateProfit);
        seeWinRate = new JButton("See Win-Rate Percentage");
        seeWinRate.setFont(new Font("Sans-serif", Font.PLAIN, 20));
        this.add(seeWinRate);
        viewTrades = new JButton("View Trade Summary");
        viewTrades.setFont(new Font("Sans-serif", Font.PLAIN, 20));
        this.add(viewTrades);
        saveTradesList = new JButton("Save Trades");
        saveTradesList.setFont(new Font("Sans-serif", Font.PLAIN, 20));
        this.add(saveTradesList);
        loadTradesList = new JButton("Load Previous Trades");
        loadTradesList.setFont(new Font("Sans-serif", Font.PLAIN, 20));
        this.add(loadTradesList);
    }

    public JButton getAddTrade() {
        return addTrade;
    }

    public JButton getCalculateProfit() {
        return calculateProfit;
    }

    public JButton getSeeWinRate() {
        return seeWinRate;
    }

    public JButton getViewTrades() {
        return viewTrades;
    }

    public JButton getSaveTradesList() {
        return saveTradesList;
    }

    public JButton getLoadTradesList() {
        return loadTradesList;
    }
}
