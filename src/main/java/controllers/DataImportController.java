package controllers;

import entities.MetricStorage;
import entities.MetricStorageInterface;
import models.ImportRequestModel;
import models.ImportResponseModel;
import use_cases.DataImportInputBoundary;

public class DataImportController {
    private final DataImportInputBoundary INPUTBOUNDARY;

    public DataImportController(DataImportInputBoundary inputBoundary) {
        this.INPUTBOUNDARY = inputBoundary;
    }

     public ImportResponseModel readFromNewFile(String path, MetricStorageInterface store) {
         ImportRequestModel req = new ImportRequestModel(path);

         return INPUTBOUNDARY.readFromNewFile(req);
     }
}
