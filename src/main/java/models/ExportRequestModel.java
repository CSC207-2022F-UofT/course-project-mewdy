package models;

public class ExportRequestModel {
    /**
     * Request model for exporting data to a file
     */
    private final String path;

    public ExportRequestModel(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
