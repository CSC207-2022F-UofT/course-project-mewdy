import controllers.*;
import entities.MetricStorage;
import entities.MetricStorageInterface;
import presenters.*;
import screens.*;
import use_cases.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {

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

        // Goal tracking decorator for metric summarizer
        MetricSumInputBoundary metricSummarizerWithGoal = new GoalTrackingDecorator(metricSummarizer, metricStorage);
        MetricSumController metricSumWithGoalController = new MetricSumController(metricSummarizerWithGoal);

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

        //Set Goal Use Case
        SetGoalOutputBoundary setGoalPresenter = new SetGoalPresenter();
        SetGoalInputBoundary goalSetter = new GoalSetter(metricStorage, setGoalPresenter);
        SetGoalController setGoalController = new SetGoalController(goalSetter);


        // Initialize UI components
        JFrame application = new JFrame("Mewdy");

        application.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        application.setSize(800, 300);
        application.setResizable(false);


        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);


        // Initialize screens
        JPanel startScreen = new StartScreen(cardLayout, screens, dataImportController);
        JPanel homeScreen = new HomeScreen(cardLayout, screens, dataExportController, metricStorage);
        JPanel chooseMetricSumScreen = new ChooseMetricSumScreen(metricStorage, metricSumController, cardLayout,
                screens, metricSumWithGoalController);
        JTabbedPane dataLogChooseScreen = new DataLogChooseScreen(metricStorage, dataLoggerController,
                metricDelController, addMetricController, entryUndoController, cardLayout, screens);
        JTabbedPane setGoalScreen = new SetGoalScreen(metricStorage, setGoalController, cardLayout, screens);


        screens.add(startScreen, "start");
        screens.add(homeScreen, "home");
        screens.add(chooseMetricSumScreen, "chooseMetricSum");
        screens.add(dataLogChooseScreen, "dataLogChoose");
        screens.add(setGoalScreen, "setGoal");


        // Build GUI
        application.add(screens);
        cardLayout.show(screens, "start");
        application.setVisible(true);

        // Confirm exit if files are unsaved
        application.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (dataExportController.getSaveStatus()) {
                    application.dispose();
                    System.exit(0);
                } else {
                    int confirmed = JOptionPane.showConfirmDialog(
                            application, "Are you sure you want to exit the program with unsaved changes?",
                            "Exit Program",
                            JOptionPane.YES_NO_OPTION);

                    if (confirmed == JOptionPane.YES_OPTION) {
                        application.dispose();
                        System.exit(0);
                    }
                }
            }
        });

    }
}
