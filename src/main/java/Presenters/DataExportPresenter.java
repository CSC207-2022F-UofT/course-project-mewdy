package Presenters;

import Models.ExportResponseModel;

public class DataExportPresenter implements DataExportPresenterOutputBoundary{

    @Override
    public ExportResponseModel prepareSuccessView() {
        return new ExportResponseModel("");
    }

    @Override
    public ExportResponseModel prepareFailView(String msg) {
        System.out.println(msg);//DEBUG
        return new ExportResponseModel(msg);
    }
}
