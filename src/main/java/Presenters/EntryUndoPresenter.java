package Presenters;

import Models.EntryUndoResponseModel;
public class EntryUndoPresenter {

    public  EntryUndoResponseModel prepareSuccessView(String msg) {
        System.out.println(msg);
        return new EntryUndoResponseModel(msg);
    }

    public EntryUndoResponseModel prepareFailView(String msg) {
        System.out.println(msg);
        return new EntryUndoResponseModel(msg);
    }

}
