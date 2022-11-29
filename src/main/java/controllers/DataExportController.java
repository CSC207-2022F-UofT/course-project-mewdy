package controllers;

import models.ExportRequestModel;
import models.ExportResponseModel;
import use_cases.DataExportInputBoundary;

public class DataExportController {
    /**
     * Controller for exporting data to a file
     */

    private final DataExportInputBoundary INPUTBOUNDARY;

    public DataExportController(DataExportInputBoundary inputBoundary) {
        this.INPUTBOUNDARY = inputBoundary;
    }

    public ExportResponseModel writeToNewFile(String path) {
        ExportRequestModel requestModel = new ExportRequestModel(path);
        return INPUTBOUNDARY.writeToNewFile(requestModel);
    }

    public boolean getSaveStatus() {
        return INPUTBOUNDARY.getSaveStatus();
    }
}
