import Controllers.MetricSumController;
import Entities.Metric;
import Entities.MetricStorage;
import Entities.MetricStorageInterface;
import Presenters.MetricSumPresenter;
import Screens.HomeScreen;
import Screens.StartScreen;
import UseCases.MetricSumInputBoundary;
import UseCases.MetricSumOutputBoundary;
import UseCases.MetricSummarizer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {


        // Create necessary classes to run program
        MetricStorageInterface metricStorage = new MetricStorage();

        //test
        metricStorage.addMetric(new Metric("sleep", 0, 24));
        metricStorage.addMetric(new Metric("schleep", 0, 24));


        MetricSumOutputBoundary metricSumPresenter = new MetricSumPresenter();
        MetricSumInputBoundary metricSummarizer = new MetricSummarizer(metricStorage, metricSumPresenter);
        MetricSumController metricSumController = new MetricSumController(metricSummarizer);

        // Build the main program window
        JFrame application = new StartScreen(metricStorage, metricSumController);

        // Build GUI

        application.setVisible(true);


    }
}
