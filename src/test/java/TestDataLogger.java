import UseCases.DataLogger;
import UseCases.DataLoggerInputBoundary;
import UseCases.DataLoggerOutputBoundary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Controllers.DataLoggerController;
import Entities.Metric;
import Entities.MetricStorage;
import Entities.MetricStorageInterface;
import Presenters.DataLoggerPresenter;


public class TestDataLogger {
    MetricStorageInterface storage;
    DataLoggerOutputBoundary presenter;
    DataLoggerInputBoundary dataLogger;
    DataLoggerController controller;

    @BeforeEach
    public void setUp() {
        storage = new MetricStorage();
        presenter = new DataLoggerPresenter();
        dataLogger = new DataLogger(storage, presenter);
        controller = new DataLoggerController(storage, dataLogger, presenter);
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
    public void testLogDataPointNoOverwrite() throws Exception {
        // makes sure that 2 datapoints cannot be added for the same current day
        storage.addMetric(new Metric("mood", 10, 0));
        controller.logDataPoint(1, "mood");
        controller.logDataPoint(10, "mood");
        int length = storage.getMetricList().size();
        assertEquals(1, length);
    }



}