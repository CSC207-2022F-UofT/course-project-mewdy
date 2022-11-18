import Controllers.MetricSumController;
import Entities.DataPoint;
import Entities.Metric;
import Entities.MetricStorage;
import Entities.MetricStorageInterface;
import Presenters.MetricSumPresenter;
import Screens.StartScreen;
import UseCases.MetricSumInputBoundary;
import UseCases.MetricSumOutputBoundary;
import UseCases.MetricSummarizer;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws ParseException {


        // Create necessary classes to run program
        MetricStorageInterface metricStorage = new MetricStorage();

        //Create data points
        ArrayList<DataPoint> dataPoints = new ArrayList<>();
        dataPoints.add(new DataPoint("2018-01-01 19:34:50", 1.0));
        dataPoints.add(new DataPoint("2018-01-02 19:34:50", 2.0));
        dataPoints.add(new DataPoint("2018-01-04 19:34:50", 3.0));
        dataPoints.add(new DataPoint("2018-01-04 19:34:50", 4.0));
        dataPoints.add(new DataPoint("2018-01-05 19:34:50", 5.0));
        dataPoints.add(new DataPoint("2018-01-06 19:34:50", 6.0));
        dataPoints.add(new DataPoint("2018-01-07 19:34:50", 7.0));

        //Create metrics
        Metric m1 = new Metric("sleep",dataPoints, 24, 0);
        Metric m2 = new Metric("empty", 10,0);

        metricStorage.addMetric(m1);
        metricStorage.addMetric(m2);

        MetricSumOutputBoundary metricSumPresenter = new MetricSumPresenter();
        MetricSumInputBoundary metricSummarizer = new MetricSummarizer(metricStorage, metricSumPresenter);
        MetricSumController metricSumController = new MetricSumController(metricSummarizer);

        // Build the main program window
        JFrame application = new StartScreen(metricStorage, metricSumController);

        // Build GUI

        application.setVisible(true);


    }
}
