package presenters;

import models.ExportResponseModel;

/**
 * Class that represents the Presenter class for DataExport use case and implements DataExportPresenterOutputBoundary
 */
public class DataExportPresenter implements DataExportOutputBoundary {
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
        return new ExportResponseModel(msg);
    }
}
