package presenters;

import models.ImportResponseModel;


/**
 * Subclass of DataImportPresenterOutputBoundary
 */
public class DataImportPresenter implements DataImportOutputBoundary {

    /**
     * prepareSuccessView returns the success view
     *
     * @return ImportResponseModel
     */
    @Override
    public ImportResponseModel prepareSuccessView() {
        return new ImportResponseModel("");
    }

    /**
     * prepareFailView returns the fail view
     *
     * @param msg represents the error message
     * @return ImportResponseModel
     */
    @Override
    public ImportResponseModel prepareFailView(String msg) {
        System.out.println(msg);//DEBUG
        return new ImportResponseModel(msg);
    }

}
