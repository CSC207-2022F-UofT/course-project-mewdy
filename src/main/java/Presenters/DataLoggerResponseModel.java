package Presenters;

import UseCases.DataLoggerOutputBoundary;

public class DataLoggerResponseModel{

    private boolean success;
    private String message;

    public DataLoggerResponseModel(boolean success, String message) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean getSuccess(){
        return this.success;
    }
}
