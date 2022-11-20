package presenters;

<<<<<<< HEAD
public class DataImportPresenter {

=======
import models.ImportResponseModel;

public class DataImportPresenter implements DataImportPresenterOutputBoundary {

    @Override
    public ImportResponseModel prepareSuccessView() {
        return new ImportResponseModel("");
    }

    @Override
    public ImportResponseModel prepareFailView(String msg) {
        System.out.println(msg);//DEBUG
        return new ImportResponseModel(msg);
    }
>>>>>>> 1a7e3b6895d936eee5e68cf04e9c29d52db1081f
}
