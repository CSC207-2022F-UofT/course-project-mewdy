package use_cases;

import org.junit.jupiter.api.BeforeEach;
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

class MetricDeleterTest {

    MetricDelOutputBoundary metricDelPresenter;
    MetricDelInputBoundary metricDeleter;
    MetricStorageInterface metricStorage;

    @BeforeEach
    public void setUp() {
        //Initializes a presenter
        this.metricDelPresenter = new MetricDelPresenter();

        //Creates a list of DataPoints
        ArrayList<DataPoint> dataPoints = new ArrayList<>();
        dataPoints.add(new DataPoint("2022-11-17 11:11:11", 6.4));
        dataPoints.add(new DataPoint("2022-11-18 11:11:11", 4.1));
        dataPoints.add(new DataPoint("2022-11-19 11:11:11", 7.6));

        //Initializes a metric
        Metric m = new Metric("sleep", dataPoints, 24, 0);

        //Creates a metricStorage and adds metric "m" to it
        this.metricStorage = new MetricStorage();
        this.metricStorage.addMetric(m);

        //Initializes the use case interactor, metricDeleter
        this.metricDeleter = new MetricDeleter(metricStorage, metricDelPresenter);
    }

    @Test
    public void testMetricDeleterSuccess() {

        //Creates a request model
        MetricDelRequestModel requestModel = new MetricDelRequestModel("sleep");

        //This is what is expected to be return after the metricDeleter runs
        MetricDelResponseModel expected = new MetricDelResponseModel("sleep", 3);

        //This is what actually returns after the metricDeleter runs
        MetricDelResponseModel actual = this.metricDeleter.metricDelete(requestModel);

        //Verifies that the metric has actually been removed
        assertEquals(0, this.metricStorage.getMetricList().size());

        //Verifies that the expected values are returned
        assertEquals(expected.getMetricName(), actual.getMetricName());
        assertEquals(expected.getNumDataPoints(), actual.getNumDataPoints());
    }

    @Test
    public void testMetricDeleterFailure() {

        //Creates a request model
        MetricDelRequestModel requestModel = new MetricDelRequestModel("work");

        //Verifies that presenter returns the correct fail message
        Throwable exception = assertThrows(MetricDeleterFailed.class,
                () -> this.metricDeleter.metricDelete(requestModel));
        assertEquals("[work] not found in metric storage!", exception.getMessage());

        //Verifies that the sleep metric is still in the metric storage
        assertEquals(1, this.metricStorage.getMetricList().size());
    }

}