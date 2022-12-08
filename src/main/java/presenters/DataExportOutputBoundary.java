package presenters;

import models.ExportResponseModel;
/**
 * Interface for DataExportPresenter
 */
public interface DataExportOutputBoundary {

    /**
     * Prepares the success view.
     *
     * @return ExportResponseModel
     */
    ExportResponseModel prepareSuccessView();

    /**
     * Prepares the fail view.
     *
     * @param msg represents the error message
     * @return ExportResponseModel
     */
    ExportResponseModel prepareFailView(String msg);
}
