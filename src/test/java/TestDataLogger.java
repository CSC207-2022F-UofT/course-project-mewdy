import static org. junit.Assert.*;

import Controllers.DataLoggerController;
import Entities.Metric;
import Entities.MetricStorage;
import Entities.MetricStorageInterface;
import Presenters.DataLoggerPresenter;
import org.junit.Test;

public class TestDataLogger {
    @Test
    public void testLogDataPointMissingMetric() {
        MetricStorageInterface storage = new MetricStorage();
        DataLoggerPresenter presenter = new DataLoggerPresenter();
        DataLoggerController controller = new DataLoggerController(storage, presenter);
        assertEquals(controller.logDataPoint(7, "mood").getMessage(),"Metric does not exist");
    }

    @Test
    public void testLogDataPointWithMetric() {
        MetricStorageInterface storage = new MetricStorage();
        DataLoggerPresenter presenter = new DataLoggerPresenter();
        DataLoggerController controller = new DataLoggerController(storage, presenter);
        storage.addMetric(new Metric("mood", 0, 10));
        assert(controller.logDataPoint(7, "mood").getMessage() == "Successfully added datapoint with value 7 to metric mood");
    }
}
