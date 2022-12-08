package presenters;

import models.EntryUndoResponseModel;

/**
 * Presenter class that implements the EntryUndoOutputBoundary interface
 */
public class EntryUndoPresenter implements EntryUndoOutputBoundary{

    /**
     * Constructor for the EntryUndoPresenter???
     */
    public EntryUndoPresenter(){}

    /**
    * prepareSuccessView prepares the success view
     *
    * @param responseModel represents the response model*
    * @return returns the EntryUndo response model
     */
    @Override
    public EntryUndoResponseModel prepareSuccessView(EntryUndoResponseModel responseModel) {
        String newInformation = "Value: "+ responseModel.getValue() + "; Date: " + responseModel.getDate()
                + "; Metric: " + responseModel.getMetric();
        System.out.println(newInformation);
        return new EntryUndoResponseModel(newInformation);
    }

    /**
     * prepareErrorView prepares the error view for the EntryUndo usecase
     *
     * @param msg represents the fail message
     * @return the fail view
     */
    @Override
    public EntryUndoResponseModel prepareFailView(String msg) {
        System.out.println(msg);
        return new EntryUndoResponseModel(msg);
    }

}
