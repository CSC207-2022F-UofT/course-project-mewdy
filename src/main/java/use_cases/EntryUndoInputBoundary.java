package use_cases;

import models.EntryUndoRequestModel;
import models.EntryUndoResponseModel;

public interface EntryUndoInputBoundary {
    EntryUndoResponseModel deleteDatapoint(EntryUndoRequestModel requestModel);
}
