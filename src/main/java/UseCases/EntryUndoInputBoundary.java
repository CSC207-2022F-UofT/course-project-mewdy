package UseCases;

import Models.EntryUndoRequestModel;
import Models.EntryUndoResponseModel;
public interface EntryUndoInputBoundary {
    EntryUndoResponseModel deleteDatapoint(EntryUndoRequestModel requestModel);
}
