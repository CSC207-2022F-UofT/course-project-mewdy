package Presenters;

import Models.EntryUndoResponseModel;
public class EntryUndoPresenter implements EntryUndoOutputBoundary{
    @Override
    public  EntryUndoResponseModel prepareSuccessView(String msg) {
        System.out.println(msg);
        return new EntryUndoResponseModel(msg);
    }
    @Override
    public EntryUndoResponseModel prepareFailView(String msg) {
        System.out.println(msg);
        return new EntryUndoResponseModel(msg);
    }

}
