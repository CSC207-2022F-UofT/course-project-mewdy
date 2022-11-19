package screens;

import javax.swing.*;
import java.awt.*;

public class DataLogInputScreen extends JPanel {

    String metricName;
    double upperBound;
    double lowerBound;

    public DataLogInputScreen(String metricName, double upperBound, double lowerBound){
        this.metricName = metricName;
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;

        JLabel title = new JLabel("Input data for " + metricName);
        JLabel bounds = new JLabel("Upper Bound: " + upperBound + " Lower Bound: " +
                lowerBound);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        bounds.setAlignmentX(Component.CENTER_ALIGNMENT);


        JButton recordButton = new JButton("Record");
        JTextField dataInput = new JTextField();
        dataInput.setPreferredSize(new Dimension(250,40));

        this.add(title);
        this.add(bounds);
        this.add(dataInput);
        this.add(recordButton);

    }
}
