package Controllers;

import Entities.MetricStorage;
import Models.ImportRequestModel;
import Models.ImportResponseModel;
import UseCases.DataImportGateway;

public class DataImportController {
    private DataImportGateway gateway;

    public DataImportController(DataImportGateway gateway) {
        this.gateway = gateway;
    }

     public ImportResponseModel readFromNewFile(String path, MetricStorage store) {
         ImportRequestModel req = new ImportRequestModel(path, store);

         return gateway.readFromNewFile(req);
     }
}
