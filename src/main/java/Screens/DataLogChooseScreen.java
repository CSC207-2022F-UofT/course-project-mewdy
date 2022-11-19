package Screens;

import Entities.Metric;
import Entities.MetricStorageInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DataLogChooseScreen extends JTabbedPane {

    MetricStorageInterface metricStorage;

    public DataLogChooseScreen(MetricStorageInterface metricStorage){
        this.metricStorage = metricStorage;

        this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        JLabel title = new JLabel("Choose Metric to record");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);
        this.setBounds(50,50,200,200);

        // Add buttons for every Metric in MetricStorage.
        ArrayList<Metric> metricList = metricStorage.getMetricList();
        for (Metric m: metricList){
            String metricName = m.getName();
            double upperBound = m.getUpperBound();
            double lowerBound = m.getLowerBound();
            DataLogInputScreen tab = new DataLogInputScreen(metricName, upperBound,lowerBound);
            this.add(metricName, tab);
        }



    }

}
