import controllers.*;
import entities.MetricStorage;
import entities.MetricStorageInterface;
import presenters.*;
import screens.*;
import use_cases.*;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {

        // Create metric storage
        MetricStorageInterface metricStorage = new MetricStorage();

        // Create necessary classes to run program
        //Import Use Case
        DataImportPresenterOutputBoundary dataImportPresenter = new DataImportPresenter();
        DataImportInputBoundary dataImporter = new DataImporter(metricStorage, dataImportPresenter);
        DataImportController dataImportController = new DataImportController(dataImporter);

        //Metric Summary Use Case
        MetricSumOutputBoundary metricSumPresenter = new MetricSumPresenter();
        MetricSumInputBoundary metricSummarizer = new MetricSummarizer(metricStorage, metricSumPresenter);
        MetricSumController metricSumController = new MetricSumController(metricSummarizer);

        //Data Logging Use Case
        DataLoggerOutputBoundary dataLoggerPresenter = new DataLoggerPresenter();
        DataLoggerInputBoundary dataLogger = new DataLogger(metricStorage, dataLoggerPresenter);
        DataLoggerController dataLoggerController = new DataLoggerController(dataLogger);

        //Export Use Case
        DataExportPresenterOutputBoundary dataExportPresenter = new DataExportPresenter();
        DataExportInputBoundary dataExporter = new DataExporter(metricStorage, dataExportPresenter);
        DataExportController dataExportController = new DataExportController(dataExporter);

        //Delete Metric Use Case
        MetricDelOutputBoundary metricDelPresenter = new MetricDelPresenter();
        MetricDelInputBoundary metricDeleter = new MetricDeleter(metricStorage, metricDelPresenter);
        MetricDelController metricDelController = new MetricDelController(metricDeleter);

        //Add Metric Use Case
        AddMetricOutputBoundary addMetricPresenter = new AddMetricPresenter();
        AddMetricInputBoundary metricAdder = new MetricAdder(addMetricPresenter, metricStorage);
        AddMetricController addMetricController = new AddMetricController(metricAdder);

        //Undo Entry Use Case
        EntryUndoOutputBoundary undoEntryPresenter = new EntryUndoPresenter();
        EntryUndoInputBoundary entryUndo = new EntryUndo(metricStorage,undoEntryPresenter);
        EntryUndoController entryUndoController = new EntryUndoController(entryUndo);


        // Initialize UI components
        JFrame application = new JFrame("Mewdy");
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setSize(800,300);
        application.setResizable(false);

        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);



        // Initialize screens
        JPanel startScreen = new StartScreen(cardLayout, screens, dataImportController);
        JPanel homeScreen = new HomeScreen(cardLayout, screens, dataExportController);
        JPanel chooseMetricSumScreen = new ChooseMetricSumScreen(metricStorage, metricSumController, cardLayout,
                screens);
        JTabbedPane dataLogChooseScreen = new DataLogChooseScreen(metricStorage, dataLoggerController,
                metricDelController, addMetricController, entryUndoController, cardLayout, screens);


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
