package controllers;

import entities.MetricStorage;
import models.ImportRequestModel;
import models.ImportResponseModel;
import use_cases.DataImportInputBoundary;

public class DataImportController {
    private final DataImportInputBoundary gateway;

    public DataImportController(DataImportInputBoundary gateway) {
        this.gateway = gateway;
    }

     public ImportResponseModel readFromNewFile(String path, MetricStorage store) {
         ImportRequestModel req = new ImportRequestModel(path, store);

         return gateway.readFromNewFile(req);
     }
}
