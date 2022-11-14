package UseCases;

import Presenters.DataLoggerResponseModel;

public interface DataLoggerInputBoundary {

    DataLoggerResponseModel logDataPoint(DataLoggerRequestModel requestModel) throws Exception;

}