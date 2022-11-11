package UseCases;

import Presenters.DataLoggerResponseModel;

public interface DataLoggerOutputBoundary {

    DataLoggerResponseModel prepareSuccessView(DataLoggerResponseModel responseModel);

}
