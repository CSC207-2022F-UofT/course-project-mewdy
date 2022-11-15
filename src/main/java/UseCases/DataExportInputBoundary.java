package UseCases;

import Models.ExportRequestModel;
import Models.ExportResponseModel;

public interface DataExportInputBoundary {
    ExportResponseModel writeToNewFile(ExportRequestModel req);

    ExportResponseModel write();
    boolean filesExist();
}
