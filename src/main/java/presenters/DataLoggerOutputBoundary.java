package presenters;

import models.DataLoggerResponseModel;

/**
 * Interface for the DataLoggerPresenter
 */
public interface DataLoggerOutputBoundary {

    /**
     * prepareSuccessView returns the success message
     *
     * @param responseModel represents the response model
     * @return DataLoggerResponseModel
     */
    DataLoggerResponseModel prepareSuccessView(DataLoggerResponseModel responseModel);

    /**
     * prepareFailView returns the error message
     *
     * @param error represents the error message
     * @return DataLoggerResponseModel
     */
    DataLoggerResponseModel prepareFailView(String error);

}
