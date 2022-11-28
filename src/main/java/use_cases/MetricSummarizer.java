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

public class MetricSummarizer implements MetricSumInputBoundary {

    private MetricStorageInterface metricStorage;
    private MetricSumOutputBoundary presenter;

    public MetricSummarizer(MetricStorageInterface metricStorage, MetricSumOutputBoundary presenter) {
        this.metricStorage = metricStorage;
        this.presenter = presenter;
    }

    @Override
    public MetricSumViewModel getMetricSummary(MetricSumRequestModel requestModel) {
        String metricName = requestModel.getMetricName();
        try {
            Metric metricToSum = this.metricStorage.getMetric(metricName);

            if (metricToSum.getDataPoints().size() < 7) {
                throw new DataSummaryFailed("Metric summary " +
                        "unavailable - " + metricName + " contains fewer than 7 data points.");
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
        } catch (Exception e) {
            return presenter.prepareDataSumFail(metricName + " not found in metric storage!");
        }
    }

    // helper method to calculate average across datapoints in metric
    private Double calculateMetricAverage(ArrayList<Double> dataList) {
        Double sum = 0.0;
        for (Double d : dataList) {
            sum += d;
        }
        sum = sum / dataList.size();

        return sum;
    }

    //helper method to calculate number of datapoints stored in metric
    private int calculateMetricSize(ArrayList<DataPoint> dataPoints) {
        return dataPoints.size();
    }
}
