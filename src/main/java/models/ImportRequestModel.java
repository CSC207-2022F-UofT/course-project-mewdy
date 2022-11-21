package models;

import entities.MetricStorage;

public class ImportRequestModel {
    private final String path;


    public ImportRequestModel(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
