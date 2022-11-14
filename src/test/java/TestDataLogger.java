import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Controllers.DataLoggerController;
import Entities.Metric;
import Entities.MetricStorage;
import Entities.MetricStorageInterface;
import Presenters.DataLoggerPresenter;


public class TestDataLogger {

    @Test
    public void testTests() {
        assertEquals(1,1);
    }
    @Test
    public void testLogDataPointMissingMetric() {
        MetricStorageInterface storage = new MetricStorage();
        DataLoggerPresenter presenter = new DataLoggerPresenter();
        DataLoggerController controller = new DataLoggerController(storage, presenter);
        String message = controller.logDataPoint(7, "mood").getMessage();
        assertEquals("Metric does not exist", message);
    }

    @Test
    public void testLogDataPointMissingMetricName() {
        MetricStorageInterface storage = new MetricStorage();
        DataLoggerPresenter presenter = new DataLoggerPresenter();
        storage.addMetric(new Metric("mood", 10, 0));
        DataLoggerController controller = new DataLoggerController(storage, presenter);
        String message = controller.logDataPoint(7, "meals eaten").getMessage();
        assertEquals("Metric does not exist", message);
    }

    @Test
    public void testLogDataPointSuccess() {
        MetricStorageInterface storage = new MetricStorage();
        DataLoggerPresenter presenter = new DataLoggerPresenter();
        storage.addMetric(new Metric("mood", 10, 0));
        DataLoggerController controller = new DataLoggerController(storage, presenter);
        String message = controller.logDataPoint(7, "mood").getMessage();
        String expectedMsg = "Successfully added datapoint with value 7.0 to metric mood";
        assertEquals(expectedMsg, message);
    }

    @Test
    public void testLogDataPointMultipleMetrics() {
        MetricStorageInterface storage = new MetricStorage();
        DataLoggerPresenter presenter = new DataLoggerPresenter();
        storage.addMetric(new Metric("mood", 10, 0));
        storage.addMetric(new Metric("meals eaten", 5, 0));
        DataLoggerController controller = new DataLoggerController(storage, presenter);
        String message = controller.logDataPoint(3, "meals eaten").getMessage();
        String expectedMsg = "Successfully added datapoint with value 3.0 to metric meals eaten";
        assertEquals(expectedMsg, message);
    }

    @Test
    public void testInvalidValue() {
        MetricStorageInterface storage = new MetricStorage();
        DataLoggerPresenter presenter = new DataLoggerPresenter();
        storage.addMetric(new Metric("mood", 10, 0));
        DataLoggerController controller = new DataLoggerController(storage, presenter);
        String message = controller.logDataPoint(-1, "mood").getMessage();
        String expectedMsg = "Failed to add datapoint, invalid value";
        assertEquals(expectedMsg, message);
    }



}
