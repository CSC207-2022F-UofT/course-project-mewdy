package models;

public class ImportResponseModel {
    private final String errorMsg;

    public ImportResponseModel(String msg){this.errorMsg = msg;}

    public String getErrorMsg() {
        return errorMsg;
    }
}
