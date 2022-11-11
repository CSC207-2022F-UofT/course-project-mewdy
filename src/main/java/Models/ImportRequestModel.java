package Models;

import Entities.MetricStorage;

public class ImportRequestModel {
    private final String path;
    private final MetricStorage store;

    public ImportRequestModel(String path, MetricStorage store) {
        this.path = path;
        this.store = store;
    }

    public  MetricStorage getStore() {
        return this.store;
    }

    public String getPath() {
        return this.path;
    }
}
