package ui;

import javax.swing.*;
import java.awt.*;

public class Title extends JPanel {

    // Constructor
    public Title() {
        this.setPreferredSize(new Dimension(300, 90));

        JLabel title = new JLabel("Trading Log");
        title.setPreferredSize(new Dimension(225, 80));
        title.setFont(new Font("Sans-serif", Font.BOLD, 20));
        title.setHorizontalAlignment(JLabel.CENTER);
        this.add(title);

    }
}
