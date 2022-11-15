package Presenters;

import Models.ExportResponseModel;

public interface DataExportPresenterOutputBoundary {
    ExportResponseModel prepareSuccessView();

    ExportResponseModel prepareFailView(String msg);
}
