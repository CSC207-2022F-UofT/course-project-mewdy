package controllers;

import use_cases.DataExportInputBoundary;

public class DataExportController {
    private final DataExportInputBoundary INPUTBOUNDARY;

    public DataExportController(DataExportInputBoundary inputBoundary) {
        this.INPUTBOUNDARY = inputBoundary;
    }
}
