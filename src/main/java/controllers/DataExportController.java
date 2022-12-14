package controllers;

import models.ExportRequestModel;
import models.ExportResponseModel;
import use_cases.DataExportInputBoundary;

/**
 * Controller class for DataExporter
 */
public class DataExportController {
    private final DataExportInputBoundary inputBoundary;

    /**
     * Constructor for DataExportController
     *
     * @param inputBoundary represents the input boundary for exporting data
     */
    public DataExportController(DataExportInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * writeToNewFile calls the use case interactor, DataExporter to export data
     *
     * @param path represents the path to the file to be exported to
     * @return the response model for exporting data
     */
    public ExportResponseModel writeToNewFile(String path){
        ExportRequestModel requestModel = new ExportRequestModel(path);
        return inputBoundary.writeToNewFile(requestModel);
    }

    public boolean getSaveStatus() {
        return inputBoundary.getSaveStatus();
    }
}
