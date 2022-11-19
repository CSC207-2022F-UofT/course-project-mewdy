package screens;

import controllers.DataLoggerController;
import entities.Metric;
import entities.MetricStorageInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DataLogChooseScreen extends JTabbedPane {

    MetricStorageInterface metricStorage;
    DataLoggerController dataLoggerController;

    public DataLogChooseScreen(MetricStorageInterface metricStorage, DataLoggerController dataLoggerController){
        this.metricStorage = metricStorage;
        this.dataLoggerController = dataLoggerController;

        this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);


        // Add buttons for every Metric in MetricStorage.
        ArrayList<Metric> metricList = metricStorage.getMetricList();
        for (Metric m: metricList){
            String metricName = m.getName();
            double upperBound = m.getUpperBound();
            double lowerBound = m.getLowerBound();
            DataLogInputScreen tab = new DataLogInputScreen(metricName, upperBound,lowerBound, dataLoggerController);
            this.add(metricName, tab);
        }

        this.add("Add Metric", new AddMetricScreen(metricStorage));


    }

}
