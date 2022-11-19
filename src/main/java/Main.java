import controllers.DataImportController;
import controllers.DataLoggerController;
import controllers.MetricSumController;
import entities.DataPoint;
import entities.Metric;
import entities.MetricStorage;
import entities.MetricStorageInterface;
import presenters.DataImportPresenter;
import presenters.DataLoggerPresenter;
import presenters.MetricSumPresenter;
import screens.*;
import use_cases.*;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws ParseException {


        // Create metric storage
        MetricStorageInterface metricStorage = new MetricStorage();

        //Create data points *test*
        ArrayList<DataPoint> dataPoints = new ArrayList<>();
        dataPoints.add(new DataPoint("2018-01-01 19:34:50", 1.0));
        dataPoints.add(new DataPoint("2018-01-02 19:34:50", 2.0));
        dataPoints.add(new DataPoint("2018-01-04 19:34:50", 3.0));
        dataPoints.add(new DataPoint("2018-01-04 19:34:50", 4.0));
        dataPoints.add(new DataPoint("2018-01-05 19:34:50", 5.0));
        dataPoints.add(new DataPoint("2018-01-06 19:34:50", 6.0));
        dataPoints.add(new DataPoint("2018-01-07 19:34:50", 7.0));

        //Create metrics *test*
        Metric m1 = new Metric("sleep",dataPoints, 24, 0);
        Metric m2 = new Metric("empty", 10,0);

        metricStorage.addMetric(m1);
        metricStorage.addMetric(m2);

        // Create necessary classess to run program
        //Metric Summary Use Case
        MetricSumOutputBoundary metricSumPresenter = new MetricSumPresenter();
        MetricSumInputBoundary metricSummarizer = new MetricSummarizer(metricStorage, metricSumPresenter);
        MetricSumController metricSumController = new MetricSumController(metricSummarizer);

        //Data Logging Use Case
        DataLoggerPresenter dataLoggerPresenter = new DataLoggerPresenter();
        DataLoggerInputBoundary dataLogger = new DataLogger(metricStorage);
        DataLoggerController dataLoggerController = new DataLoggerController(dataLogger);


        // Initialize UI components
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        new MainFrame(screens, cardLayout);

        // Initialize screens
        JPanel startScreen = new StartScreen(cardLayout, screens);
        JPanel homeScreen = new HomeScreen(cardLayout, screens);
        JPanel chooseMetricSumScreen = new ChooseMetricSumScreen(metricStorage, metricSumController);
        JTabbedPane dataLogChooseScreen = new DataLogChooseScreen(metricStorage, dataLoggerController);


        screens.add(startScreen, "start");
        screens.add(homeScreen, "home");
        screens.add(chooseMetricSumScreen, "chooseMetricSum");
        screens.add(dataLogChooseScreen, "dataLogChoose");


        // Build GUI
        cardLayout.show(screens, "start");

    }
}
