import java.text.ParseException;

import Presenters.EntryUndoOutputBoundary;
import Presenters.EntryUndoPresenter;
import Entities.MetricStorage;
import Entities.Metric;
import Entities.DataPoint;
import Controllers.EntryUndoController;
import UseCases.EntryUndo;
import UseCases.EntryUndoInputBoundary;

public class Main {
    public static void main(String[] args) throws ParseException {
        Metric metric = new Metric("sleep", 24, 0);
        DataPoint testDataA =  new DataPoint("2022-11-16 12:47:18", 1);
        DataPoint testDataB =  new DataPoint("2022-11-17 12:47:18", 2);
        DataPoint testDataC =  new DataPoint("2022-11-18 12:47:18", 3);
        MetricStorage metricStorage = new MetricStorage();
        metricStorage.addMetric(metric);
        metricStorage.addDataPoint("sleep", testDataA);
        metricStorage.addDataPoint("sleep", testDataC);
        metricStorage.addDataPoint("sleep", testDataB);


        EntryUndoOutputBoundary entryUndoPresenter = new EntryUndoPresenter();
        EntryUndoInputBoundary entryUndo = new EntryUndo(metricStorage, entryUndoPresenter);
        EntryUndoController entryUndoController = new EntryUndoController(entryUndo);
        entryUndoController.deleteLastEntry("sleep");

        for (Metric metricB:
                metricStorage.getMetricList()) {
            System.out.println(metricB.getName() + " " + metricB.getUpperBound() + " " + metricB.getLowerBound());
            for (DataPoint dp:
                    metricB.getDataPoints()) {
                System.out.println(dp.getDate() + ", " + dp.getValue());
            }
            System.out.println();
        }

    }
}
