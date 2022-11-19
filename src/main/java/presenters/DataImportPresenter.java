package presenters;

import models.ImportResponseModel;

public class DataImportPresenter implements DataImportPresenterOutputBoundary {

    @Override
    public ImportResponseModel prepareSuccessView() {
        return new ImportResponseModel("");
    }

    @Override
    public ImportResponseModel prepareFailView(String msg) {
        System.out.println(msg);//DEBUG
        return new ImportResponseModel(msg);
    }
}
