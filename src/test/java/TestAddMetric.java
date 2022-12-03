import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import controllers.AddMetricController;
import entities.MetricStorageInterface;
import entities.Metric;
import entities.MetricStorage;
import presenters.AddMetricPresenter;
import use_cases.AddMetricInputBoundary;
import use_cases.MetricAdder;

//Tests for the MetricAdder use case
public class TestAddMetric {

    // Tests to see if imports are working
    @Test
    public void testTests() {
        assertEquals(1,1);
    }

    // Tests to see if the MetricAdder can add a metric to a storage
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

    // Tests to see if MetricAdder will prevent adding a metric that already exists
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

    // Tests to see if the AddMetricPresenter can return the correct success response
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

    // Tests to see if the AddMetricPresenter can return the correct fail response
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
