package Models;

public class ExportResponseModel {
    private final String errorMsg;

    public ExportResponseModel(String msg){this.errorMsg = msg;}

    public String getErrorMsg() {
        return errorMsg;
    }
}
