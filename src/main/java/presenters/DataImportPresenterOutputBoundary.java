package presenters;

import models.ImportResponseModel;

public interface DataImportPresenterOutputBoundary {
    ImportResponseModel prepareSuccessView();

    ImportResponseModel prepareFailView(String msg);
}
