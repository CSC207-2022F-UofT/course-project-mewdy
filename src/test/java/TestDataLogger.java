import entities.DataPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import controllers.DataLoggerController;
import entities.Metric;
import entities.MetricStorage;
import entities.MetricStorageInterface;
import presenters.DataLoggerOutputBoundary;
import presenters.DataLoggerPresenter;
import use_cases.DataLogger;
import use_cases.DataLoggerInputBoundary;

import java.util.ArrayList;


public class TestDataLogger {
    MetricStorageInterface storage;
    DataLoggerInputBoundary dataLogger;
    DataLoggerController controller;

    @BeforeEach
    public void setUp() {
        storage = new MetricStorage();
        DataLoggerOutputBoundary presenter = new DataLoggerPresenter();
        dataLogger = (DataLoggerInputBoundary) new DataLogger(storage, presenter);
        controller = new DataLoggerController(dataLogger);
    }

    @Test
    public void testLogDataPointMissingMetric() {
        // tests the case where there is no metric to add datapoints to
        String message = controller.logDataPoint(7, "mood").getMessage();
        assertEquals("Metric does not exist", message);
    }

    @Test
    public void testLogDataPointMissingMetricName() {
        // tests the case where the incorrect metric name is used as input
        storage.addMetric(new Metric("mood", 10, 0));
        String message = controller.logDataPoint(7, "meals eaten").getMessage();
        assertEquals("Metric does not exist", message);
    }

    @Test
    public void testLogDataPointSuccess() {
        // tests a successful additon of a datapoint
        storage.addMetric(new Metric("mood", 10, 0));
        String message = controller.logDataPoint(7, "mood").getMessage();
        String expectedMsg = "Successfully added datapoint with value 7.0 to metric mood";
        assertEquals(expectedMsg, message);
    }

    @Test
    public void testLogDataPointMultipleMetrics() {
        // tests adding datapoint to one of multiple metrics
        storage.addMetric(new Metric("mood", 10, 0));
        storage.addMetric(new Metric("meals eaten", 5, 0));
        String message = controller.logDataPoint(3, "meals eaten").getMessage();
        String expectedMsg = "Successfully added datapoint with value 3.0 to metric meals eaten";
        assertEquals(expectedMsg, message);
    }

    @Test
    public void testInvalidValue() {
        // tests that an invalid value (a value beyond the upperlower bounds) cannot be added
        storage.addMetric(new Metric("mood", 10, 0));
        String message = controller.logDataPoint(-1, "mood").getMessage();
        String expectedMsg = "Failed to add datapoint, invalid value";
        assertEquals(expectedMsg, message);
    }

    @Test
    public void testLogDataPointOverwrite() throws Exception {
        // makes sure that adding multiple datapoints on the same day doesn't add more
        storage.addMetric(new Metric("mood", 10, 0));
        for (int i = 1; i<=10;i++){
            controller.logDataPoint(i, "mood");
        }
        int length = storage.getMetric("mood").getDataPoints().size();

        assertEquals(1, length);
    }

    @Test
    public void testLogDataPointNoOverwrite2() throws Exception {
        // makes sure that datapoints added on the same day don't overwrite their values
        storage.addMetric(new Metric("mood", 10, 0));
        for (int i = 1; i<=10;i++){
            controller.logDataPoint(i, "mood");
        }
        double value = storage.getMetric("mood").getDataPoints().get(0).getValue();
        assertEquals(1, value);
    }

    @Test
    public void testLogDataPointOnLongList() throws Exception {
        // makes sure that datapoints can be added while other datapoints are in the arraylist at other dates
        Metric mood = new Metric("mood", 10, 0);
        mood.addDataPoint(new DataPoint("2022-10-23 01:00:00", 3));
        storage.addMetric(mood);
        controller.logDataPoint(4, "mood");
        int length = storage.getMetric("mood").getDataPoints().size();
        assertEquals(2, length);
    }

    @Test
    public void testLogDataPointOnLongListValuesCorrect() throws Exception {
        // makes sure that datapoints values are consistent after adding datapoints on different days
        Metric mood = new Metric("mood", 10, 0);
        mood.addDataPoint(new DataPoint("2022-10-23 01:00:00", 3));
        storage.addMetric(mood);
        controller.logDataPoint(4, "mood");
        ArrayList<DataPoint> dataPoints = storage.getMetric("mood").getDataPoints();
        double first = dataPoints.get(0).getValue();
        double second = dataPoints.get(1).getValue();
        assertEquals(3, first);
        assertEquals(4, second);
    }

    @Test
    public void testLogDataPointReallyLongList() throws Exception {
        // tests that adding many datapoints doesn't interfere with the usecase
        Metric mood = new Metric("mood", 10, 0);
        for (Integer i = 1; i<=9;i++) {
            mood.addDataPoint(new DataPoint("2022-11-0" + i.toString() + " 01:00:00", i));
        }

        storage.addMetric(mood);
        controller.logDataPoint(4, "mood");
        ArrayList<DataPoint> dataPoints = storage.getMetric("mood").getDataPoints();
        int length = storage.getMetric("mood").getDataPoints().size();
        assertEquals(10, length);
    }


}