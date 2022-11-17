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
        String message = controller.logDataPoint(7, "mood").getMessage();
        assertEquals("Metric does not exist", message);
    }

    @Test
    public void testLogDataPointMissingMetricName() {
        storage.addMetric(new Metric("mood", 10, 0));
        String message = controller.logDataPoint(7, "meals eaten").getMessage();
        assertEquals("Metric does not exist", message);
    }

    @Test
    public void testLogDataPointSuccess() {
        storage.addMetric(new Metric("mood", 10, 0));
        String message = controller.logDataPoint(7, "mood").getMessage();
        String expectedMsg = "Successfully added datapoint with value 7.0 to metric mood";
        assertEquals(expectedMsg, message);
    }

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
        storage.addMetric(new Metric("mood", 10, 0));
        String message = controller.logDataPoint(-1, "mood").getMessage();
        String expectedMsg = "Failed to add datapoint, invalid value";
        assertEquals(expectedMsg, message);
    }

    @Test
    public void testLogDataPointNoOverwrite() throws Exception {
        storage.addMetric(new Metric("mood", 10, 0));
        controller.logDataPoint(1, "mood");
        controller.logDataPoint(10, "mood");
        int length = storage.getMetricList().size();
        assertEquals(1, length);
    }



}