package models;

/**
 * Class that represents the RequestModel for Export
 */
public class ExportRequestModel {
    private final String path;

    /**
     * Constructor for ExportRequestModel
     *
     * @param path represents the path to the file
     */
    public ExportRequestModel(String path) {
        this.path = path;
    }

    /**
     * Getter for path
     *
     * @return the location of where MetricStorage is to be exported
     */
    public String getPath() {
        return this.path;
    }
}
