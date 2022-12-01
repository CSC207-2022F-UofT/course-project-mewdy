package models;

/**
 * Class that represents the ResponseModel for Import
 */
public class ImportResponseModel {
    private final String errorMsg;

    /**
     * Constructor for ImportResponseModel
     *
     * @param msg represents the error message
     */
    public ImportResponseModel(String msg){this.errorMsg = msg;}

    /**
     * Getter for the error message
     *
     * @return the error message
     */
    public String getErrorMsg() {
        return errorMsg;
    }
}
