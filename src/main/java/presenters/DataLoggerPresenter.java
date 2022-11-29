package presenters;

import models.DataLoggerResponseModel;

/**
 * Subclass of DataLoggerOutputBoundary
 */

public class DataLoggerPresenter implements DataLoggerOutputBoundary{

    /**
     * prepareSuccesView for the succesview
     *
     * @param responseModel represents the response model
     * @return DataLoggerResponseModel
     */
    @Override
    public DataLoggerResponseModel prepareSuccessView(DataLoggerResponseModel responseModel) {
        Double value = responseModel.getValue();
        String message = responseModel.getMessage() + " with value "  + value.toString() + " to metric " +
                responseModel.getMetricName();
        return new DataLoggerResponseModel(message);
    }

    /**
     * prepareFailView returns the failure view
     *
     * @param error represents the error messagae
     * @return DataLoggerResponseModel
     */
    @Override
    public DataLoggerResponseModel prepareFailView(String error) {
        return new DataLoggerResponseModel(error);
    }
}
