package controllers;


import models.DataLoggerRequestModel;
import models.DataLoggerResponseModel;
import use_cases.DataLoggerInputBoundary;

public class DataLoggerController {

    private final DataLoggerInputBoundary dataLogger;

    public DataLoggerController(DataLoggerInputBoundary dataLogger) {
        this.dataLogger = dataLogger;
    }

    public DataLoggerResponseModel logDataPoint(double value, String metricName) {
        DataLoggerRequestModel requestModel = new DataLoggerRequestModel(metricName, value);
        return this.dataLogger.logDataPoint(requestModel);
    }

}