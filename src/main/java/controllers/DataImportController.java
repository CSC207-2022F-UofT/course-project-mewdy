package controllers;

import models.ImportRequestModel;
import models.ImportResponseModel;
import use_cases.DataImportInputBoundary;

public class DataImportController {
    /**
     * Controller for importing data from a file
     */
    private final DataImportInputBoundary INPUTBOUNDARY;

    public DataImportController(DataImportInputBoundary inputBoundary) {
        this.INPUTBOUNDARY = inputBoundary;
    }

    public ImportResponseModel readFromNewFile(String path) {
        ImportRequestModel req = new ImportRequestModel(path);

        return INPUTBOUNDARY.readFromNewFile(req);
    }
}
