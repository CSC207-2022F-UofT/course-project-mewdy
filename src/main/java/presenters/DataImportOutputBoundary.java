package presenters;

import models.ImportResponseModel;

/**
 * Interface for DataImportPresenter
 */

public interface DataImportOutputBoundary {

    /**
     * prepareSuccessView returns the success view
     *
     * @return ImportResponseModel
     */
    ImportResponseModel prepareSuccessView();

    /**
     * prepareFailView returns the fail view
     *
     * @param msg represents the error message
     * @return ImportResponseModel
     */
    ImportResponseModel prepareFailView(String msg);
}
