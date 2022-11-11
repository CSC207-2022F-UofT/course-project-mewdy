package Models;

public class ImportResponseModel {
    private String errorMsg;

    public ImportResponseModel(String msg){this.errorMsg = msg;}

    public String getErrorMsg() {
        return errorMsg;
    }
}
