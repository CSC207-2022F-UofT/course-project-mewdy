package Presenters;

import Models.ImportResponseModel;

public interface DataImportPresenterGateway {
    ImportResponseModel prepareSuccessView();

    ImportResponseModel prepareFailView(String msg);
}
