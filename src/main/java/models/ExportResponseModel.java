package models;

public class ExportResponseModel {
    /**
     * Request model for exporting data to a file
     */
    private final String errorMsg;

    public ExportResponseModel(String msg) {
        this.errorMsg = msg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
