package UseCases;

import Models.ImportRequestModel;
import Models.ImportResponseModel;

public interface DataImportInputBoundary {

    ImportResponseModel read();
    ImportResponseModel readFromNewFile(ImportRequestModel req);
}
