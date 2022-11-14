package Presenters;

import Models.EntryUndoResponseModel;
public interface EntryUndoOutputBoundary {
    EntryUndoResponseModel prepareSuccessView(String msg);

    EntryUndoResponseModel prepareFailView(String msg);
}
