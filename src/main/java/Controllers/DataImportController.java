package Controllers;

import Entities.MetricStorage;
import Models.ImportRequestModel;
import Models.ImportResponseModel;
import UseCases.DataImportInputBoundary;

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
