package Presenters;

import UseCases.DataLoggerOutputBoundary;

public class DataLoggerResponseModel{

    private final String metricName;
    private final double value;
    private boolean success;
    private String message;

    public DataLoggerResponseModel(String message, String metricName, double value) {
        // depending on whether the DataLog fails or succeeds, these values will be relevant or not
        this.message = message;
        this.metricName = metricName;
        this.value = value;
    }

    public DataLoggerResponseModel(String message){
        this.message = message;
        this.metricName = "";
        this.value = 0;
    }

    public String getMessage() {
        return this.message;
    }

    public String getMetricName(){
        return this.metricName;
    }

    public double getValue(){
        return this.value;
    }
}
