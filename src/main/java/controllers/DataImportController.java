package controllers;

import entities.MetricStorage;
import models.ImportRequestModel;
import models.ImportResponseModel;
import use_cases.DataImportInputBoundary;

public class DataImportController {
    private final DataImportInputBoundary INPUTBOUNDARY;

    public DataImportController(DataImportInputBoundary inputBoundary) {
        this.INPUTBOUNDARY = inputBoundary;
    }

     public ImportResponseModel readFromNewFile(String path, MetricStorage store) {
         ImportRequestModel req = new ImportRequestModel(path, store);

         return INPUTBOUNDARY.readFromNewFile(req);
     }
}
