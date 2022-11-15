import Controllers.MetricSumController;
import Entities.MetricStorage;
import Entities.MetricStorageInterface;
import Presenters.MetricSumPresenter;
import Screens.HomeScreen;
import Screens.StartScreen;
import UseCases.MetricSumInputBoundary;
import UseCases.MetricSumOutputBoundary;
import UseCases.MetricSummarizer;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        // Build the main program window
        JFrame application = new StartScreen();
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);

        // Create necessary classes to run program
        MetricStorageInterface metricStorage = new MetricStorage();

        MetricSumOutputBoundary metricSumPresenter = new MetricSumPresenter();
        MetricSumInputBoundary metricSummarizer = new MetricSummarizer(metricStorage, metricSumPresenter);
        MetricSumController metricSumController = new MetricSumController(metricSummarizer);

        // Build GUI

        application.pack();
        application.setVisible(true);

    }
}
