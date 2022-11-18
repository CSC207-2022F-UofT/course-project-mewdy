import static org.junit.jupiter.api.Assertions.*;

import Screens.DataSummaryFailed;
import org.junit.jupiter.api.Test;

import Entities.DataPoint;
import Entities.Metric;
import Entities.MetricStorage;
import Entities.MetricStorageInterface;
import Models.MetricSumRequestModel;
import Presenters.MetricSumPresenter;
import Presenters.MetricSumViewModel;
import UseCases.MetricSumInputBoundary;
import UseCases.MetricSumOutputBoundary;
import UseCases.MetricSummarizer;
import java.util.ArrayList;

public class TestMetricSumUseCase {


    @Test
    public void testMetricSummarizerSuccess() throws Exception {

        //Initialize Presenter
        MetricSumOutputBoundary metricSumPresenter = new MetricSumPresenter();

        //Create data points
        ArrayList<DataPoint> dataPoints = new ArrayList<>();
        dataPoints.add(new DataPoint("2018-01-01 19:34:50", 1.0));
        dataPoints.add(new DataPoint("2018-01-02 19:34:50", 2.0));
        dataPoints.add(new DataPoint("2018-01-04 19:34:50", 3.0));
        dataPoints.add(new DataPoint("2018-01-04 19:34:50", 4.0));
        dataPoints.add(new DataPoint("2018-01-05 19:34:50", 5.0));
        dataPoints.add(new DataPoint("2018-01-06 19:34:50", 6.0));
        dataPoints.add(new DataPoint("2018-01-07 19:34:50", 7.0));

        //Create metrics
        Metric m1 = new Metric("sleep",dataPoints, 24, 0);
        Metric m2 = new Metric("empty", 10,0);

        //Create metric storage and populate with metrics and datapoints within the metrics.

        MetricStorageInterface metricStorage = new MetricStorage();
        metricStorage.addMetric(m1);
        metricStorage.addMetric(m2);

        //Initialize MetricSummarizer
        MetricSumInputBoundary metricSummarizer = new MetricSummarizer(metricStorage, metricSumPresenter);

        //Create request model
        MetricSumRequestModel requestModel = new MetricSumRequestModel("sleep");

        MetricSumViewModel viewModel = metricSummarizer.getMetricSummary(requestModel);

        assertEquals("Average: 4.0; Size: 7", viewModel.getMetricAverageAndSize());
    }




    @Test
    public void testMetricSummarizerFail() throws Exception {

        //Initialize Presenter
        MetricSumOutputBoundary metricSumPresenter = new MetricSumPresenter();

        //Create data points
        ArrayList<DataPoint> dataPoints = new ArrayList<DataPoint>();
        dataPoints.add(new DataPoint("2018-01-01 19:34:50", 1.0));
        dataPoints.add(new DataPoint("2018-01-02 19:34:50", 2.0));
        dataPoints.add(new DataPoint("2018-01-04 19:34:50", 3.0));
        dataPoints.add(new DataPoint("2018-01-04 19:34:50", 4.0));
        dataPoints.add(new DataPoint("2018-01-05 19:34:50", 5.0));
        dataPoints.add(new DataPoint("2018-01-06 19:34:50", 6.0));
        dataPoints.add(new DataPoint("2018-01-07 19:34:50", 7.0));

        //Create metrics
        Metric m1 = new Metric("sleep", dataPoints, 24, 0);
        Metric m2 = new Metric("empty", 10, 0);

        //Create metric storage and populate with metrics and datapoints within the metrics.

        MetricStorageInterface metricStorage = new MetricStorage();
        metricStorage.addMetric(m1);
        metricStorage.addMetric(m2);

        //Initialize MetricSummarizer
        MetricSumInputBoundary metricSummarizer = new MetricSummarizer(metricStorage, metricSumPresenter);

        //Create request model
        MetricSumRequestModel requestModel = new MetricSumRequestModel("empty");

        Throwable exception = assertThrows(DataSummaryFailed.class, () ->
                metricSummarizer.getMetricSummary(requestModel));
        assertEquals("Metric summary unavailable - empty contains fewer than 7 data points.",
                exception.getMessage());
    }
    @Test
    public void testMetricStorageGetMetric() throws Exception {

        //Create data points
        ArrayList<DataPoint> dataPoints = new ArrayList<>();
        dataPoints.add(new DataPoint("2018-01-01 19:34:50", 1.0));
        dataPoints.add(new DataPoint("2018-01-02 19:34:50", 2.0));
        dataPoints.add(new DataPoint("2018-01-04 19:34:50", 3.0));
        dataPoints.add(new DataPoint("2018-01-04 19:34:50", 4.0));
        dataPoints.add(new DataPoint("2018-01-05 19:34:50", 5.0));
        dataPoints.add(new DataPoint("2018-01-06 19:34:50", 6.0));
        dataPoints.add(new DataPoint("2018-01-07 19:34:50", 7.0));

        //Create metrics
        Metric m1 = new Metric("sleep",dataPoints, 24, 0);
        Metric m2 = new Metric("empty", 10,0);

        //Create metric storage and populate with metrics and datapoints within the metrics.

        MetricStorageInterface metricStorage = new MetricStorage();
        metricStorage.addMetric(m1);
        metricStorage.addMetric(m2);

        assertEquals("sleep", m1.getName());
        assertEquals(m1, metricStorage.getMetric("sleep"));
        assertFalse(metricStorage.getMetric("sleep").getDataPoints().size() < 7);
    }

}

