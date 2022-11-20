package use_cases;

import models.DataLoggerRequestModel;
import models.DataLoggerResponseModel;

public interface DataLoggerInputBoundary {

    DataLoggerResponseModel logDataPoint(DataLoggerRequestModel requestModel) throws Exception;

}