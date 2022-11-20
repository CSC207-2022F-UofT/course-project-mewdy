package controllers;

<<<<<<< HEAD
public class DataImportController {

=======
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
>>>>>>> 1a7e3b6895d936eee5e68cf04e9c29d52db1081f
}
