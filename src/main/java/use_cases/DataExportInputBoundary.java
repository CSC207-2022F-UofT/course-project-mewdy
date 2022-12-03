package use_cases;

import models.ExportRequestModel;
import models.ExportResponseModel;

/**
 * Interface for the DataExport use case
 */
public interface DataExportInputBoundary {

    /**
     * writeToNewFile writes the data in the database to a new file
     *
     * @param req represents the request model for the DataExport use case
     * @return ExportResponseModel representing the response model for the DataExport use case
     */
    ExportResponseModel writeToNewFile(ExportRequestModel req);

    /**
     * getSaveStatus a boolean representing whether the data has been saved or not
     *
     * @return boolean
     */
    boolean getSaveStatus();
}