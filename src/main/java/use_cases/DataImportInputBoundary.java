package use_cases;

import models.ImportRequestModel;
import models.ImportResponseModel;

/**
 * Interface for the DataImport use case
 */
public interface DataImportInputBoundary {

    /**
     * readFromNewFile reads data from a file via the request model and writes it into the metricstorage
     *
     * @param req represents the request model for the DataImport use case
     * @return ImportResponseModel representing the response model for the DataImport use case
     */

    ImportResponseModel readFromNewFile(ImportRequestModel req);

}
