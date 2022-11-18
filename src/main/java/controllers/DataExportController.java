package controllers;

import use_cases.DataExportInputBoundary;

public class DataExportController {
    private final DataExportInputBoundary gateway;

    public DataExportController(DataExportInputBoundary gateway) {
        this.gateway = gateway;
    }
}
