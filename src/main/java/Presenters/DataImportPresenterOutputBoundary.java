package Presenters;

import Models.ImportResponseModel;

public interface DataImportPresenterOutputBoundary {
    ImportResponseModel prepareSuccessView();

    ImportResponseModel prepareFailView(String msg);
}
