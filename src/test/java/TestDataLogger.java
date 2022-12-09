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

//Tests for the DataLogger use case
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

    //Tests the case where there is no metric to add datapoints to
    @Test
    public void testLogDataPointMissingMetric() {
        String message = controller.logDataPoint(7, "mood").getMessage();
        assertEquals("Metric does not exist", message);
    }

    //Tests the case where the incorrect metric name is used as input
    @Test
    public void testLogDataPointMissingMetricName() {

        storage.addMetric(new Metric("mood", 10, 0));
        String message = controller.logDataPoint(7, "meals eaten").getMessage();
        assertEquals("Metric does not exist", message);
    }

    //Tests the successful addition of a datapoint to a metric
    @Test
    public void testLogDataPointSuccess() {

        storage.addMetric(new Metric("mood", 10, 0));
        String message = controller.logDataPoint(7, "mood").getMessage();
        String expectedMsg = "Successfully added datapoint with value 7.0 to metric mood";
        assertEquals(expectedMsg, message);
    }

    //Tests adding datapoints to the correct metric with multiple metrics in the storage
    @Test
    public void testLogDataPointMultipleMetrics() {
        storage.addMetric(new Metric("mood", 10, 0));
        storage.addMetric(new Metric("meals eaten", 5, 0));
        String message = controller.logDataPoint(3, "meals eaten").getMessage();
        String expectedMsg = "Successfully added datapoint with value 3.0 to metric meals eaten";
        assertEquals(expectedMsg, message);
    }

    @Test
    public void testInvalidValue() {
        // Tests that an invalid value (a value beyond the upperlower bounds) cannot be added
        storage.addMetric(new Metric("mood", 10, 0));
        String message = controller.logDataPoint(-1, "mood").getMessage();
        String expectedMsg = "Failed to add datapoint, invalid value - Please enter a number within the bounds of the metric";
        assertEquals(expectedMsg, message);
    }

    // Tests that you cannot add multiple datapoints on the same day
    @Test
    public void testLogDataPointOverwrite() {
        // Makes sure that adding multiple datapoints on the same day doesn't add more
        storage.addMetric(new Metric("mood", 10, 0));
        for (int i = 1; i<=10;i++){
            controller.logDataPoint(i, "mood");
        }
        int length = storage.getMetric("mood").getDataPoints().size();

        assertEquals(1, length);
    }

    // Tests that adding new datapoints to a day with existing datapoints do not overwrite the existing datapoint
    @Test
    public void testLogDataPointNoOverwrite2() {
        storage.addMetric(new Metric("mood", 10, 0));
        for (int i = 1; i<=10;i++){
            controller.logDataPoint(i, "mood");
        }
        double value = storage.getMetric("mood").getDataPoints().get(0).getValue();
        assertEquals(1, value);
    }

    // Tests that datapoints can be added while there are other datapoints in the metric at other dates
    @Test
    public void testLogDataPointOnLongList() {
        Metric mood = new Metric("mood", 10, 0);
        mood.addDataPoint(new DataPoint("2022-10-23 01:00:00", 3));
        storage.addMetric(mood);
        controller.logDataPoint(4, "mood");
        int length = storage.getMetric("mood").getDataPoints().size();
        assertEquals(2, length);
    }

    // Tests that the datapoint values are consistent after adding datapoints on different days
    @Test
    public void testLogDataPointOnLongListValuesCorrect() {
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

    // Tests that adding many datapoints does not interfere with the use case
    @Test
    public void testLogDataPointReallyLongList() {
        Metric mood = new Metric("mood", 10, 0);
        for (int i = 1; i<=9; i++) {
            mood.addDataPoint(new DataPoint("2022-11-0" + i + " 01:00:00", i));
        }

        storage.addMetric(mood);
        controller.logDataPoint(4, "mood");
        ArrayList<DataPoint> dataPoints = storage.getMetric("mood").getDataPoints();
        int length = storage.getMetric("mood").getDataPoints().size();
        assertEquals(10, length);
    }


}