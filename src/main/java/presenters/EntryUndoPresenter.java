package presenters;

import models.EntryUndoResponseModel;

public class EntryUndoPresenter implements EntryUndoOutputBoundary {
    public EntryUndoPresenter() {
    }

    @Override
    public EntryUndoResponseModel prepareSuccessView(EntryUndoResponseModel responseModel) {
        String newInformation = "Value: " + responseModel.getValue() + "; Date: " + responseModel.getDate()
                + "; Metric: " + responseModel.getMetric();
        System.out.println(newInformation);
        return new EntryUndoResponseModel(newInformation);
    }

    @Override
    public EntryUndoResponseModel prepareFailView(String msg) {
        System.out.println(msg);
        return new EntryUndoResponseModel(msg);
    }

}
