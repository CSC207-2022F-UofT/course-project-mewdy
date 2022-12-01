package models;

import entities.MetricStorage;

/**
 * Class that represents the RequestModel for Import
 */
public class ImportRequestModel {
    private final String path;

    /**
     * Constructor for the ImportRequestModel
     *
     * @param path represents the path to the file that is being imported
     */
    public ImportRequestModel(String path) {
        this.path = path;
    }

    /**
     * Getter method for the path
     *
     * @return the path to the file that is being imported
     */
    public String getPath() {
        return this.path;
    }
}
