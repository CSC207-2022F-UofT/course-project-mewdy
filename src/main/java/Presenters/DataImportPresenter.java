package Presenters;

import Models.ImportResponseModel;

public class DataImportPresenter implements DataImportPresenterGateway{
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
