package presenters;

import models.ExportResponseModel;

public class DataExportPresenter implements DataExportPresenterOutputBoundary {
    /**
     * Prepares a response model for a successful or failed export
     */

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
