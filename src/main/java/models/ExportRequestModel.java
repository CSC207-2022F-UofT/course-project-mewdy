package models;

import entities.MetricStorage;

public class ExportRequestModel {
    private final String path;

    public ExportRequestModel(String path) {
        this.path = path;
    }
    public String getPath() {
        return this.path;
    }
}
