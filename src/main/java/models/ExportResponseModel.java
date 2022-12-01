package models;

/**
 * Class that represents the ResponseModel for Export
 */
public class ExportResponseModel {
    private final String errorMsg;

    /**
     * Constructor for ExportResponseModel
     *
     * @param msg represents the error message
     */
    public ExportResponseModel(String msg){this.errorMsg = msg;}

    /**
     * Getter for errorMsg
     * @return the error message
     */
    public String getErrorMsg() {
        return errorMsg;
    }
}
