package use_cases;

import models.ExportRequestModel;
import models.ExportResponseModel;

public interface DataExportInputBoundary {
    ExportResponseModel writeToNewFile(ExportRequestModel req);

    ExportResponseModel write();
    boolean filesExist();
}