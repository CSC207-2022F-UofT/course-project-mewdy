package MetricSummaryUseCase.Presenters;

import MetricSummaryUseCase.Models.MetricSumResponseModel;
import MetricSummaryUseCase.Screens.DataSummaryFailed;
import MetricSummaryUseCase.UseCases.MetricSumOutputBoundary;

public class MetricSumPresenter implements MetricSumOutputBoundary {

    @Override
    public MetricSumViewModel prepareSuccessView(MetricSumResponseModel responseModel){

        String metricAverageAndSize = "Average: " + responseModel.toString() + "; Size: " +
                responseModel.getMetricSize();

        return new MetricSumViewModel(responseModel.getTimePoints(),
                responseModel.getDatalist(), metricAverageAndSize);
    }

    @Override
    public MetricSumViewModel prepareDataSumFail(String error){
        throw new DataSummaryFailed(error);
    }
}
