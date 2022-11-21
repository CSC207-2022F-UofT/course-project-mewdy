package controllers;

<<<<<<< HEAD
public class DataImportController {

=======
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

     public ImportResponseModel readFromNewFile(String path) {
         ImportRequestModel req = new ImportRequestModel(path);

         return INPUTBOUNDARY.readFromNewFile(req);
     }
>>>>>>> 1a7e3b6895d936eee5e68cf04e9c29d52db1081f
}
