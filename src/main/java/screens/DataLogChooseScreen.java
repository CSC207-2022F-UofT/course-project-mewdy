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

/**
 * Subclass of JTabbedPane that displays the metrics that are currently being logged and implements Refreshable
 */
public class DataLogChooseScreen extends JTabbedPane implements Refreshable {

    MetricStorageInterface metricStorage;
    DataLoggerController dataLoggerController;
    MetricDelController metricDelController;
    AddMetricController addMetricController;
    EntryUndoController entryUndoController;
    CardLayout cardLayout;
    JPanel screens;

    /**
     * Constructor for DataLogChooseScreen
     *
     * @param metricStorage represents the metric storage
     * @param dataLoggerController represents the data logger controller
     * @param metricDelController represents the metric delete controller
     * @param addMetricController represents the add metric controller
     * @param entryUndoController represents the entry undo controller
     * @param cardLayout represents the card layout of the main frame
     * @param screens represents the JPanel that contains all the screens
     */
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

    /**
     * refreshes the screen
     */
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
