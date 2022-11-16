package Presenters;

import Models.EntryUndoResponseModel;
public class EntryUndoPresenter implements EntryUndoOutputBoundary{
    public EntryUndoPresenter(){}
    @Override
    public EntryUndoResponseModel prepareSuccessView(EntryUndoResponseModel responseModel) {
        String newInformation = "Value: "+ responseModel.getValue() + "; Date " + responseModel.getDate()
                + "; " + responseModel.getMetric();
        return new EntryUndoResponseModel(newInformation);
    }
    @Override
    public EntryUndoResponseModel prepareFailView(String msg) {
        System.out.println(msg);
        return new EntryUndoResponseModel(msg);
    }

}
