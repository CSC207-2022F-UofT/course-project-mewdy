import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import entities.MetricStorageInterface;
import entities.MetricStorage;
import entities.DataPoint;
import entities.Metric;
import models.MetricDelRequestModel;
import models.MetricDelResponseModel;
import presenters.MetricDelOutputBoundary;
import presenters.MetricDelPresenter;
import screens.MetricDeleterFailed;
import use_cases.MetricDelInputBoundary;
import use_cases.MetricDeleter;

// Tests for MetricDeleter use case
class MetricDeleterTest {

    // Tests whether the use case can delete a metric from the metric storage
    @Test
    public void testMetricDeleterSuccess() {

        //Initializes a presenter
        MetricDelOutputBoundary metricDelPresenter = new MetricDelPresenter();

        //Creates a list of DataPoints
        ArrayList<DataPoint> dataPoints = new ArrayList<>();
        dataPoints.add(new DataPoint("2022-11-17 11:11:11", 6.4));
        dataPoints.add(new DataPoint("2022-11-18 11:11:11", 4.1));
        dataPoints.add(new DataPoint("2022-11-19 11:11:11", 7.6));

        //Initializes a metric
        Metric m = new Metric("sleep", dataPoints, 24, 0);

        //Creates a metricStorage and adds metric "m" to it
        MetricStorageInterface metricStorage = new MetricStorage();
        metricStorage.addMetric(m);

        //Initializes the use case interactor, metricDeleter
        MetricDelInputBoundary metricDeleter = new MetricDeleter(metricStorage, metricDelPresenter);

        //Creates a request model
        MetricDelRequestModel requestModel = new MetricDelRequestModel("sleep");

        //This is what is expected to be return after the metricDeleter runs
        MetricDelResponseModel expected = new MetricDelResponseModel("sleep", 3);

        //This is what actually returns after the metricDeleter runs
        MetricDelResponseModel actual = metricDeleter.create(requestModel);

        //Verifies that the metric has actually been removed
        assertEquals(0, metricStorage.getMetricList().size());

        //Verifies that the expected values are returned
        assertEquals(expected.getMetricName(), actual.getMetricName());
        assertEquals(expected.getNumDataPoints(), actual.getNumDataPoints());
    }

    // Tests that the view is called when the metric fails to delete
    @Test
    public void testMetricDeleterFailure() {
        //Initializes a presenter
        MetricDelOutputBoundary metricDelPresenter = new MetricDelPresenter();

        //Creates a list of DataPoints
        ArrayList<DataPoint> dataPoints = new ArrayList<>();
        dataPoints.add(new DataPoint("2022-11-17 11:11:11", 6.4));
        dataPoints.add(new DataPoint("2022-11-18 11:11:11", 4.1));
        dataPoints.add(new DataPoint("2022-11-19 11:11:11", 7.6));

        //Initializes a metric
        Metric m = new Metric("sleep", dataPoints, 24, 0);

        //Creates a metricStorage and adds metric "m" to it
        MetricStorageInterface metricStorage = new MetricStorage();
        metricStorage.addMetric(m);

        //Initializes the use case interactor, metricDeleter
        MetricDelInputBoundary metricDeleter = new MetricDeleter(metricStorage, metricDelPresenter);

        //Creates a request model
        MetricDelRequestModel requestModel = new MetricDelRequestModel("work");

        //Verifies that presenter returns the correct fail message
        Throwable exception = assertThrows(MetricDeleterFailed.class, () -> metricDeleter.create(requestModel));
        assertEquals("[work] not found in metric storage!", exception.getMessage());

        //Verifies that the sleep metric is still in the metric storage
        assertEquals(1, metricStorage.getMetricList().size());
    }

}