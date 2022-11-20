package use_cases;

<<<<<<< HEAD
public class DataImportInputBoundary {

=======
import models.ImportRequestModel;
import models.ImportResponseModel;

public interface DataImportInputBoundary {

    ImportResponseModel read();
    ImportResponseModel readFromNewFile(ImportRequestModel req);
>>>>>>> 1a7e3b6895d936eee5e68cf04e9c29d52db1081f
}
