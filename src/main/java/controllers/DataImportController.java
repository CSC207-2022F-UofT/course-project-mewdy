package controllers;

import models.ImportRequestModel;
import models.ImportResponseModel;
import use_cases.DataImportInputBoundary;

/**
 * Controller for the DataImport use case
 */
public class DataImportController {
    private final DataImportInputBoundary inputBoundary;

    /**
     * Constructor for the DataImportController
     *
     * @param inputBoundary The input boundary for the DataImport use case
     */
    public DataImportController(DataImportInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * readFromNewFile calls the use case interactor, DataImport to import data from a file path
     *
     * @param path represents the path to the file to be imported
     * @return the response model for the DataImport use case
     */
     public ImportResponseModel readFromNewFile(String path) {
         ImportRequestModel req = new ImportRequestModel(path);

         return inputBoundary.readFromNewFile(req);
     }
}
