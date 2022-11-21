

import controllers.DataExportController;
import controllers.DataImportController;
import controllers.DataLoggerController;
import controllers.MetricSumController;
import entities.DataPoint;
import entities.Metric;
import entities.MetricStorage;
import entities.MetricStorageInterface;
import presenters.DataExportPresenter;
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

        // Create necessary classes to run program
        //Import Use Case
        DataImportPresenter dataImportPresenter = new DataImportPresenter();
        DataImportInputBoundary dataImporter = new DataImporter(metricStorage, dataImportPresenter);
        DataImportController dataImportController = new DataImportController(dataImporter);

        //Metric Summary Use Case
        MetricSumOutputBoundary metricSumPresenter = new MetricSumPresenter();
        MetricSumInputBoundary metricSummarizer = new MetricSummarizer(metricStorage, metricSumPresenter);
        MetricSumController metricSumController = new MetricSumController(metricSummarizer);

        //Data Logging Use Case
        DataLoggerPresenter dataLoggerPresenter = new DataLoggerPresenter();
        DataLoggerInputBoundary dataLogger = new DataLogger(metricStorage);
        DataLoggerController dataLoggerController = new DataLoggerController(dataLogger);

        //Export Use Case
        DataExportPresenter dataExportPresenter = new DataExportPresenter();
        DataExportInputBoundary dataExporter = new DataExporter(metricStorage, dataExportPresenter);
        DataExportController dataExportController = new DataExportController(dataExporter);


        // Initialize UI components
        JFrame application = new JFrame("Mewdy");
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setSize(800,300);

        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);



        // Initialize screens
        JPanel startScreen = new StartScreen(cardLayout, screens, dataImportController);
        JPanel homeScreen = new HomeScreen(cardLayout, screens, dataExportController);
        JPanel chooseMetricSumScreen = new ChooseMetricSumScreen(metricStorage, metricSumController, cardLayout,
                screens);
        JTabbedPane dataLogChooseScreen = new DataLogChooseScreen(metricStorage, dataLoggerController, cardLayout,
                screens);


        screens.add(startScreen, "start");
        screens.add(homeScreen, "home");
        screens.add(chooseMetricSumScreen, "chooseMetricSum");
        screens.add(dataLogChooseScreen, "dataLogChoose");


        // Build GUI
        application.add(screens);
        cardLayout.show(screens, "start");
        application.setVisible(true);

    }
}