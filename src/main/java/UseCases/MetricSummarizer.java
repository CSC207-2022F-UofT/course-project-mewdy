package UseCases;

import Entities.DataPoint;
import Entities.Metric;
import Entities.MetricStorageInterface;
import Models.MetricSumRequestModel;
import Models.MetricSumResponseModel;
import Presenters.MetricSumViewModel;

import java.util.ArrayList;
import java.util.Date;

public class MetricSummarizer implements MetricSumInputBoundary {

    final MetricStorageInterface metricStorage;
    final MetricSumOutputBoundary presenter;

    public MetricSummarizer(MetricStorageInterface metricStorage, MetricSumOutputBoundary presenter){
        this.metricStorage = metricStorage;
        this.presenter = presenter;
    }

    @Override
    public MetricSumViewModel getMetricSummary(MetricSumRequestModel requestModel){
        String metricName = requestModel.getMetricName();
        try {
            Metric metricToSum = this.metricStorage.getMetric(metricName);
            if (metricToSum.getDataPoints().size() < 7){return presenter.prepareDataSumFail("Metric summary " +
                    "unavailable - " + metricName + " contains fewer than 7 data points.");}

            ArrayList<DataPoint> dataPoints = metricToSum.getDataPoints();
            ArrayList<Double> dataList = new ArrayList<Double>();
            ArrayList<Date> timePoints = new ArrayList<Date>();
            for (DataPoint d: dataPoints){
                timePoints.add(d.getDate());
                dataList.add(d.getValue());
            }
            double metricAverage = calculateMetricAverage(dataList);
            int metricSize = calculateMetricSize(dataList);

            MetricSumResponseModel responseModel = new MetricSumResponseModel(timePoints, dataList, metricAverage,
                    metricSize);
            return presenter.prepareSuccessView(responseModel);
        } catch (Exception e) {
            return presenter.prepareDataSumFail(metricName + " not found in metric storage!");
        }
    }

    // helper method to calculate average across datapoints in metric
    private double calculateMetricAverage(ArrayList<Double> dataList){
        double sum = 0;
        for (Double d: dataList){
            sum += d;
        }
        sum = sum / dataList.size();
        return sum;
    }

    //helper method to calculate simple trend across datapoints in metric
    private int calculateMetricSize(ArrayList<Double> dataList){
        return dataList.size();
    }
}
