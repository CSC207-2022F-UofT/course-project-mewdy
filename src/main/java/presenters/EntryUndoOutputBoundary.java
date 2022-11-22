package presenters;

import models.EntryUndoResponseModel;
public interface EntryUndoOutputBoundary {
    EntryUndoResponseModel prepareSuccessView(EntryUndoResponseModel msg);

    EntryUndoResponseModel prepareFailView(String msg);
}
