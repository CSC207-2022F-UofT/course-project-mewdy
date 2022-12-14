import java.text.ParseException;

import presenters.EntryUndoOutputBoundary;
import presenters.EntryUndoPresenter;
import entities.MetricStorage;
import entities.Metric;
import entities.DataPoint;
import controllers.EntryUndoController;
import use_cases.EntryUndo;
import use_cases.EntryUndoInputBoundary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests for the EntryUndo use case
class EntryUndoTest{
    Metric metric;
    MetricStorage preMadeStorage;
    MetricStorage metricStorage;
    EntryUndoOutputBoundary entryUndoPresenter;
    EntryUndoInputBoundary entryUndo;
    EntryUndoController entryUndoController;

    @BeforeEach
    void setUp() {
    metric = new Metric("sleep", 24, 0);
    DataPoint testDataA =  new DataPoint("2022-11-16 12:47:18", 1);
    DataPoint testDataB =  new DataPoint("2022-11-17 12:47:18", 2);
    DataPoint testDataC =  new DataPoint("2022-11-18 12:47:18", 3);
    metricStorage = new MetricStorage();

    preMadeStorage = new MetricStorage();

    metricStorage.addMetric(metric);

    preMadeStorage.addMetric(metric);

    metricStorage.addDataPoint("sleep", testDataA);
    metricStorage.addDataPoint("sleep", testDataC);
    metricStorage.addDataPoint("sleep", testDataB);

    preMadeStorage.addDataPoint("sleep", testDataA);
    preMadeStorage.addDataPoint("sleep", testDataC);



    entryUndoPresenter = new EntryUndoPresenter();
    entryUndo = new EntryUndo(metricStorage, entryUndoPresenter);
    entryUndoController = new EntryUndoController(entryUndo);
    }

    //Test for undoing an entry
    @Test
    void undo() {
        entryUndoController.deleteLastEntry("sleep");
        assertTrue(isEqual(metricStorage, preMadeStorage));
    }
    private boolean isEqual(MetricStorage s1, MetricStorage s2) {
        for (int i = 0; i < s1.getMetricList().size(); i++) {
            if (s1.getMetricList().size() != s2.getMetricList().size()) {
                return false;
            } else if (s1.getMetricList().size() == s2.getMetricList().size()) {
                if (!s1.getMetricList().get(i).equals(s2.getMetricList().get(i))){
                    return false;}
            }
        }
        return true;
    }


}
