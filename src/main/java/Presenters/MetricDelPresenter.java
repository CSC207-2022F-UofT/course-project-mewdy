package Presenters;

import Models.MetricDelResponseModel;
import UseCases.MetricDelOutputBoundary;

public class MetricDelPresenter implements MetricDelOutputBoundary{

    @Override
    public MetricDelResponseModel prepareSuccessView(MetricDelResponseModel responseModel) {
        String metricDel = "The metric, " + responseModel.getMetricName() + ", with " +
                responseModel.getNumDataPoints() + " data points, has been successfully deleted!";
    }

    @Override
    public MetricDelResponseModel prepareMetricDelFail(String error) {
        throw new Exception(error);
    }
}
