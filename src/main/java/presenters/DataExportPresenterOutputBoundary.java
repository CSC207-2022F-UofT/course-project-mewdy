package presenters;

import models.ExportResponseModel;

public interface DataExportPresenterOutputBoundary {
    ExportResponseModel prepareSuccessView();

    ExportResponseModel prepareFailView(String msg);
}
