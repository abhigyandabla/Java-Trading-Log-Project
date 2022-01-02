package ui;

import javax.swing.*;
import java.awt.*;

public class InputFields extends JPanel {

    private JTextField nameField;
    private JTextField slField;
    private JTextField tpField;
    private JTextField durField;
    private JTextField resultField;
    private JTextField timeField;
    private JTextField dayField;

    private JLabel nameLabel;
    private JLabel stopLossLabel;
    private JLabel takeProfitLabel;
    private JLabel durationLabel;
    private JLabel resultLabel;
    private JLabel timeLabel;
    private JLabel dayLabel;

    private Dimension textDimension = new Dimension(200, 40);

    // Constructor
    public InputFields() {
        nameLabel = new JLabel("Enter name of instrument traded:");
        nameField = new JTextField();
        nameField.setPreferredSize(textDimension);
        stopLossLabel = new JLabel("Enter your stop loss:");
        slField = new JTextField();
        slField.setPreferredSize(textDimension);
        takeProfitLabel = new JLabel("Enter your take profit:");
        tpField = new JTextField();
        tpField.setPreferredSize(textDimension);
        durationLabel = new JLabel("Duration of trade in minutes:");
        durField = new JTextField();
        durField.setPreferredSize(textDimension);
        resultLabel = new JLabel("Enter the result (win, loss, or breakeven):");
        resultField = new JTextField();
        resultField.setPreferredSize(textDimension);
        timeLabel = new JLabel("Enter the time of the day (in minutes from midnight):");
        timeField = new JTextField();
        timeField.setPreferredSize(textDimension);
        dayLabel = new JLabel("Enter the day of the week (first three letters):");
        dayField = new JTextField();
        dayField.setPreferredSize(textDimension);
        addFields();
    }

    public void addFields() {
        this.add(nameLabel);
        this.add(nameField);
        this.add(stopLossLabel);
        this.add(slField);
        this.add(takeProfitLabel);
        this.add(tpField);
        this.add(durationLabel);
        this.add(durField);
        this.add(resultLabel);
        this.add(resultField);
        this.add(timeLabel);
        this.add(timeField);
        this.add(dayLabel);
        this.add(dayField);
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getSLField() {
        return slField;
    }

    public JTextField getTPField() {
        return tpField;
    }

    public JTextField getDurField() {
        return durField;
    }

    public JTextField getResultField() {
        return resultField;
    }

    public JTextField getTimeField() {
        return timeField;
    }

    public JTextField getDayField() {
        return dayField;
    }

}
