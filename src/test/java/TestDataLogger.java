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
        // makes sure that adding multiple datapoints on the same day doesn't add more
        storage.addMetric(new Metric("mood", 10, 0));
        for (int i = 1; i<=10;i++){
            controller.logDataPoint(i, "mood");
        }
        int length = storage.getMetricList().size();
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



}