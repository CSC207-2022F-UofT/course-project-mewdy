import static org.junit.jupiter.api.Assertions.*;

import controllers.MetricSumController;
import screens.DataSummaryFailed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.DataPoint;
import entities.Metric;
import entities.MetricStorage;
import entities.MetricStorageInterface;
import models.MetricSumRequestModel;
import presenters.MetricSumPresenter;
import presenters.MetricSumViewModel;
import use_cases.MetricSumInputBoundary;
import presenters.MetricSumOutputBoundary;
import use_cases.MetricSummarizer;

import java.text.ParseException;
import java.util.ArrayList;

//Tests for the MetricSumUseCase
public class TestMetricSumUseCase {

    MetricSumInputBoundary metricSummarizer;
    MetricStorageInterface metricStorage;
    MetricSumOutputBoundary metricSumPresenter;
    MetricSumController metricSumController;

    @BeforeEach
    public void setUp() throws ParseException {
        //Initialize Presenter
        metricSumPresenter = new MetricSumPresenter();

        //Create data points
        ArrayList<DataPoint> dataPoints = new ArrayList<>();
        dataPoints.add(new DataPoint("2018-01-01 19:34:50", 1.253));
        dataPoints.add(new DataPoint("2018-01-02 19:34:50", 1.253));
        dataPoints.add(new DataPoint("2018-01-04 19:34:50", 1.253));
        dataPoints.add(new DataPoint("2018-01-04 19:34:50", 1.253));
        dataPoints.add(new DataPoint("2018-01-05 19:34:50", 1.253));
        dataPoints.add(new DataPoint("2018-01-06 19:34:50", 1.253));
        dataPoints.add(new DataPoint("2018-01-07 19:34:50", 1.253));

        //Create metrics
        Metric m1 = new Metric("sleep",dataPoints, 24, 0);
        Metric m2 = new Metric("empty", 10,0);

        //Create metric storage and populate with metrics and datapoints within the metrics.

        metricStorage = new MetricStorage();
        metricStorage.addMetric(m1);
        metricStorage.addMetric(m2);

        //Initialize MetricSummarizer
        metricSummarizer = new MetricSummarizer(metricStorage, metricSumPresenter);

    }
    // Test that Metric Summarizer returns view model with properly formatted String displaying avg and size of metric
    @Test
    public void testMetricSummarizerSuccess(){

        //Create request model
        MetricSumRequestModel requestModel = new MetricSumRequestModel("sleep");

        MetricSumViewModel viewModel = metricSummarizer.getMetricSummary(requestModel);


        assertEquals("Average: 1.26; Size: 7", viewModel.getMetricAverageAndSize());
    }

    // Test that Metric Summarizer throws DataSummaryFailed exception when Metric contains fewer than 7 dataPoints.
    // Metric needs to contain >= 7 datapoints for summary function to work.
    @Test
    public void testMetricSummarizerFail() {
        //Create request model
        MetricSumRequestModel requestModel = new MetricSumRequestModel("empty");

        Throwable exception = assertThrows(DataSummaryFailed.class, () ->
                metricSummarizer.getMetricSummary(requestModel));
        assertEquals("Metric summary unavailable - empty contains fewer than 7 data points.",
                exception.getMessage());
    }


}

