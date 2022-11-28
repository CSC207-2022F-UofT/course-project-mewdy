package use_cases;

import models.ImportRequestModel;
import models.ImportResponseModel;

public interface DataImportInputBoundary {

    ImportResponseModel read();

    ImportResponseModel readFromNewFile(ImportRequestModel req);

}
