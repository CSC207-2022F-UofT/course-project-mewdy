package presenters;

import models.DataLoggerResponseModel;

public interface DataLoggerOutputBoundary {

    DataLoggerResponseModel prepareSuccessView(DataLoggerResponseModel responseModel);

    DataLoggerResponseModel prepareFailView(String error);

}
