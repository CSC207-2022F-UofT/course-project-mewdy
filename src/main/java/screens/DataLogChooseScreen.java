package screens;

import controllers.AddMetricController;
import controllers.DataLoggerController;
import controllers.EntryUndoController;
import controllers.MetricDelController;
import entities.Metric;
import entities.MetricStorageInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DataLogChooseScreen extends JTabbedPane implements Refreshable {

    MetricStorageInterface metricStorage;
    DataLoggerController dataLoggerController;
    MetricDelController metricDelController;
    AddMetricController addMetricController;
    EntryUndoController entryUndoController;
    CardLayout cardLayout;
    JPanel screens;

    public DataLogChooseScreen(MetricStorageInterface metricStorage, DataLoggerController dataLoggerController,
                               MetricDelController metricDelController, AddMetricController addMetricController,
                               EntryUndoController entryUndoController, CardLayout cardLayout, JPanel screens){
        this.metricStorage = metricStorage;
        this.dataLoggerController = dataLoggerController;
        this.metricDelController = metricDelController;
        this.addMetricController = addMetricController;
        this.entryUndoController = entryUndoController;
        this.cardLayout = cardLayout;
        this.screens = screens;

        this.setName("DataLogChoose");

        this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);


        // Add tabs for every Metric in MetricStorage.
        ArrayList<Metric> metricList = metricStorage.getMetricList();
        for (Metric m: metricList){
            String metricName = m.getName();
            double upperBound = m.getUpperBound();
            double lowerBound = m.getLowerBound();
            DataLogInputScreen tab = new DataLogInputScreen(metricName, upperBound,lowerBound, entryUndoController,
                    dataLoggerController, cardLayout, screens);
            this.add(metricName, tab);

            int i = metricList.indexOf(m);
            MetricTab metricTab = new MetricTab(this, metricName, metricDelController);
            this.setTabComponentAt(i, metricTab);

        }

        this.add("Add Metric", new AddMetricScreen(metricStorage, addMetricController,
                DataLogChooseScreen.this, cardLayout, screens));


    }

    @Override
    public void refresh(){
        this.removeAll();

        this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        ArrayList<Metric> metricList = metricStorage.getMetricList();
        for (Metric m: metricList){
            String metricName = m.getName();
            double upperBound = m.getUpperBound();
            double lowerBound = m.getLowerBound();
            DataLogInputScreen tab = new DataLogInputScreen(metricName, upperBound,lowerBound, entryUndoController,
                    dataLoggerController, cardLayout, screens);
            this.add(metricName, tab);

            int i = metricList.indexOf(m);
            this.setTabComponentAt(i, new MetricTab(this, metricName, metricDelController));
        }

        this.add("Add Metric", new AddMetricScreen(metricStorage, addMetricController,
                DataLogChooseScreen.this, cardLayout, screens));

        this.revalidate();
        this.repaint();
    }


}
