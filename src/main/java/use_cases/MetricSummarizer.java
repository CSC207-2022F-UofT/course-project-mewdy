package use_cases;

import entities.DataPoint;
import entities.Metric;
import entities.MetricStorageInterface;
import models.MetricSumRequestModel;
import models.MetricSumResponseModel;
import presenters.MetricSumOutputBoundary;
import presenters.MetricSumViewModel;
import screens.DataSummaryFailed;

import java.util.ArrayList;

/**
 * Class that represents the MetricSummarizer use case that is responsible for summarizing the datapoints in a metric
 */

public class MetricSummarizer implements MetricSumInputBoundary {

    private final MetricStorageInterface metricStorage;
    private final MetricSumOutputBoundary presenter;

    /**
     * Constructor for MetricSummarizer
     *
     * @param metricStorage represents the metric storage that the use case will interact with
     * @param presenter represents the presenter that will present the output
     */
    public MetricSummarizer(MetricStorageInterface metricStorage, MetricSumOutputBoundary presenter) {
        this.metricStorage = metricStorage;
        this.presenter = presenter;
    }

    /**
     * getMetricSummary returns a view model of a given metric
     *
     * @param requestModel represents the request model for the MetricSum use case
     * @return MetricSumViewModel represents the view model for the MetricSum use case
     * @throws DataSummaryFailed if chosen metric to summarize contains 0 data points
     * @throws NullPointerException if the metric is not found
     */
    @Override
    public MetricSumViewModel getMetricSummary(MetricSumRequestModel requestModel) {
        String metricName = requestModel.getMetricName();
        try {
            Metric metricToSum = this.metricStorage.getMetric(metricName);

            if (metricToSum.getDataPoints().size() == 0) {
                throw new DataSummaryFailed("Metric summary " +
                        "unavailable - " + metricName + " contains 0 data points");
            }

            ArrayList<DataPoint> dataPoints = metricToSum.getDataPoints();
            ArrayList<Double> dataList = new ArrayList<>();
            ArrayList<String> timePoints = new ArrayList<>();
            for (DataPoint d : dataPoints) {
                timePoints.add(d.getDate());
                dataList.add(d.getValue());
            }
            double metricAverage = calculateMetricAverage(dataList);
            int metricSize = calculateMetricSize(dataPoints);

            MetricSumResponseModel responseModel = new MetricSumResponseModel(timePoints, dataList, metricAverage,
                    metricSize, metricName);

            return presenter.prepareSuccessView(responseModel);
        } catch (DataSummaryFailed dataSummaryFailed) {
            return presenter.prepareDataSumFail(dataSummaryFailed.getMessage());
        } catch (NullPointerException e) {
            return presenter.prepareDataSumFail(metricName + " not found in metric storage!");
        }
    }

    /**
     * calculateMetricAverage is a helper method that calculate the average across datapoints in a given metric
     *
     * @param dataList represents the list of data points
     * @return the average value of the data points in a metric
     */
    private Double calculateMetricAverage(ArrayList<Double> dataList) {
        Double sum = 0.0;
        for (Double d : dataList) {
            sum += d;
        }
        sum = sum / dataList.size();

        return sum;
    }

    /**
     * calculateMetricSize is a helper method that calculates the number of datapoints stored in a given metric
     *
     * @param dataPoints represents the list of data points
     * @return the number of datapoints stored in a metric
     */
    private int calculateMetricSize(ArrayList<DataPoint> dataPoints) {
        return dataPoints.size();
    }

}
