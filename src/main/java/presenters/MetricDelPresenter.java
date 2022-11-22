package presenters;

import models.MetricDelResponseModel;
import screens.MetricDeleterFailed;

public class MetricDelPresenter implements MetricDelOutputBoundary{

    //Returns the response model for the metric that was deleted
    @Override
    public MetricDelResponseModel prepareSuccessView(MetricDelResponseModel responseModel) {
        return responseModel;
    }

    //Throws a MetricDeleterFailed exception in the event that the selected metric can't be deleted
    @Override
    public MetricDelResponseModel prepareMetricDelFail(String error) {
        throw new MetricDeleterFailed(error);
    }
}
