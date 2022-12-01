package presenters;

import models.ExportResponseModel;

/**
 * Subclass of DataExportPresenterOutputBoundary
 */
public class DataExportPresenter implements DataExportPresenterOutputBoundary{
    /**
     * prepareSuccessView returns a new ExportResponseModel with an empty string
     *
     * @return the export response model
     */
    @Override
    public ExportResponseModel prepareSuccessView() {
        return new ExportResponseModel("");
    }

    /**
     * prepareFailView returns a new ExportResponseModel with the message
     *
     * @param msg represents the message to be displayed
     * @return the export response model
     */
    @Override
    public ExportResponseModel prepareFailView(String msg) {
        System.out.println(msg);//DEBUG
        return new ExportResponseModel(msg);
    }
}
