package models;

public class ImportRequestModel {
    /**
     * Request model for importing data from a file
     */

    private final String path;


    public ImportRequestModel(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
