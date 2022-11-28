package models;

public class ImportResponseModel {
    /**
     * Response model for Importing data to a file
     */
    private final String errorMsg;

    public ImportResponseModel(String msg) {
        this.errorMsg = msg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
