package Presenters;

import Models.EntryUndoResponseModel;
public interface EntryUndoOutputBoundary {
    EntryUndoResponseModel prepareSuccessView(EntryUndoResponseModel msg);

    EntryUndoResponseModel prepareFailView(String msg);
}
