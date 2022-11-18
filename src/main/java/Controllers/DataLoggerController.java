package Controllers;


import Entities.Metric;
import Entities.MetricStorageInterface;
import Presenters.DataLogFailed;
import Presenters.DataLoggerPresenter;
import Presenters.DataLoggerResponseModel;
import UseCases.DataLogger;
import UseCases.DataLoggerInputBoundary;
import UseCases.DataLoggerOutputBoundary;
import UseCases.DataLoggerRequestModel;

import javax.xml.crypto.Data;

public class DataLoggerController{

    private final DataLoggerInputBoundary dataLogger;

    public DataLoggerController(DataLoggerInputBoundary dataLogger){
        this.dataLogger = dataLogger;
    }
    public DataLoggerResponseModel logDataPoint(double value, String metricName) {
        try {
            DataLoggerRequestModel requestModel = new DataLoggerRequestModel(metricName, value);
            return this.dataLogger.logDataPoint(requestModel);

        } catch (Exception e) {
            throw new DataLogFailed("Failed to add datapoint");
        }

    }

}