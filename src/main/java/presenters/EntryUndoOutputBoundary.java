package presenters;

import models.EntryUndoResponseModel;

/**
 * Interface for the EntryUndoPresenter
 */
public interface EntryUndoOutputBoundary {

    /**
     * prepareSuccessView returns the success view
     *
     * @param msg represents the success message
     * @return the success view for the EntryUndo usecase
     */
    EntryUndoResponseModel prepareSuccessView(EntryUndoResponseModel msg);

    /**
     * prepareFailView returns the fail view
     *
     * @param msg represents the fail message
     * @return the fail view for the EntryUndo usecase
     */
    EntryUndoResponseModel prepareFailView(String msg);
}
