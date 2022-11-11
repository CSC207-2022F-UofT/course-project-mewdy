package UseCases;

import Models.ImportRequestModel;
import Models.ImportResponseModel;

public interface DataImportGateway {

    ImportResponseModel read();
    ImportResponseModel readFromNewFile(ImportRequestModel req);
}
