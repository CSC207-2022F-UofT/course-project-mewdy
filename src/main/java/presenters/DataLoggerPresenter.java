package presenters;

import models.DataLoggerResponseModel;

public class DataLoggerPresenter implements DataLoggerOutputBoundary{

    @Override
    public DataLoggerResponseModel prepareSuccessView(DataLoggerResponseModel responseModel) {
        Double value = responseModel.getValue();
        String message = responseModel.getMessage() + " with value "  + value.toString() + " to metric " +
                responseModel.getMetricName();
        return new DataLoggerResponseModel(message);
    }

    @Override
    public DataLoggerResponseModel prepareFailView(String error) {
        return new DataLoggerResponseModel(error);
    }
}
