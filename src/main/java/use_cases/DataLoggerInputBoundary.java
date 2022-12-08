package use_cases;

import models.DataLoggerRequestModel;
import models.DataLoggerResponseModel;

/**
 * Interface for the DataLogger use case
 */
public interface DataLoggerInputBoundary {

    /**
     * logData logs datapoints into a metric
     *
     * @param requestModel contains the information of the datapoint to be logged
     * @return DataLoggerResponseModel contains the information of the datapoint that was logged
     */
    DataLoggerResponseModel logDataPoint(DataLoggerRequestModel requestModel);

}