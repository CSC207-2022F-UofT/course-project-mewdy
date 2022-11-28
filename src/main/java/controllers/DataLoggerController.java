package controllers;


import models.DataLoggerRequestModel;
import models.DataLoggerResponseModel;
import screens.DataLogFailed;
import use_cases.DataLoggerInputBoundary;

public class DataLoggerController {

    private final DataLoggerInputBoundary dataLogger;

    public DataLoggerController(DataLoggerInputBoundary dataLogger) {
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