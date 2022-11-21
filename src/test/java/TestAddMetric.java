import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import controllers.AddMetricController;
import entities.MetricStorageInterface;
import entities.Metric;
import entities.MetricStorage;
import presenters.AddMetricPresenter;
import use_cases.AddMetricInputBoundary;
import use_cases.MetricAdder;


public class TestAddMetric {

    @Test
    public void testTests() {
        assertEquals(1,1);
    }


    @Test
    public void testAddMetricData() {
        MetricStorageInterface metricStorage = new MetricStorage();
        AddMetricPresenter addMetricPresenter = new AddMetricPresenter();
        AddMetricInputBoundary addMetricInputBoundary = new MetricAdder(addMetricPresenter, metricStorage);
        AddMetricController addMetricController = new AddMetricController(addMetricInputBoundary);
        addMetricController.addMetric("test",10, 0);
        MetricStorage expectedStorage = new MetricStorage();
        expectedStorage.addMetric(new Metric("test", 10, 0));
        assertTrue(expectedStorage.getMetricList().get(0).equals(metricStorage.getMetricList().get(0)));
    }

    @Test
    public void testAddMetricWithPreexistingMetric() {
        MetricStorageInterface metricStorage = new MetricStorage();
        AddMetricPresenter addMetricPresenter = new AddMetricPresenter();
        AddMetricInputBoundary addMetricInputBoundary = new MetricAdder(addMetricPresenter, metricStorage);
        AddMetricController addMetricController = new AddMetricController(addMetricInputBoundary);
        addMetricController.addMetric("test",10, 0);
        addMetricController.addMetric("test",10, 0);
        addMetricController.addMetric("Energy",5, 0);
        MetricStorage expectedStorage = new MetricStorage();
        expectedStorage.addMetric(new Metric("test", 10, 0));
        expectedStorage.addMetric(new Metric("Energy",5, 0));
        assertTrue(expectedStorage.getMetricList().get(0).equals(metricStorage.getMetricList().get(0)));
        assertTrue(expectedStorage.getMetricList().get(1).equals(metricStorage.getMetricList().get(1)));
    }
    @Test
    public void testAddMetricPresenterSuccess() {
        MetricStorageInterface metricStorage = new MetricStorage();
        AddMetricPresenter addMetricPresenter = new AddMetricPresenter();
        AddMetricInputBoundary addMetricInputBoundary = new MetricAdder(addMetricPresenter, metricStorage);
        AddMetricController addMetricController = new AddMetricController(addMetricInputBoundary);
        String actualMessage = addMetricController.addMetric("test", 10, 0).getMessage();
        String expectedMessage = "Metric with the name test was added successfully!";
        assertEquals(expectedMessage, actualMessage);
    }
    @Test
    public void testAddMetricPresenterFailure() {
        MetricStorageInterface metricStorage = new MetricStorage();
        AddMetricPresenter addMetricPresenter = new AddMetricPresenter();
        AddMetricInputBoundary addMetricInputBoundary = new MetricAdder(addMetricPresenter, metricStorage);
        AddMetricController addMetricController = new AddMetricController(addMetricInputBoundary);
        addMetricController.addMetric("test",10, 0);
        String actualMessage = addMetricController.addMetric("test", 10, 0).getMessage();
        String expectedMessage = "Metric already exists!";
        assertEquals(expectedMessage, actualMessage);
    }
}
