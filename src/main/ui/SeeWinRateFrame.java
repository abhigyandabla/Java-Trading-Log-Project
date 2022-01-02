package ui;

import javax.swing.*;
import java.awt.*;

public class SeeWinRateFrame extends FrameLayout {

    private JLabel label;
    private ImageIcon image;
    private JLabel imgLabel;

    // Constructor
    public SeeWinRateFrame(String text, Boolean bool) {
        super();
        this.setLayout(new FlowLayout());

        if (bool) {
            image = new ImageIcon("data/nice.gif");
        } else {
            image = new ImageIcon("data/sad.gif");
        }

        imgLabel = new JLabel(image);
        label = new JLabel(text);

        this.add(imgLabel);
        this.add(label);
    }

}
