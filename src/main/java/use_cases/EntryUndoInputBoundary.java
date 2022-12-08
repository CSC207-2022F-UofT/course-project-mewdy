package use_cases;

import models.EntryUndoRequestModel;
import models.EntryUndoResponseModel;

/**
 * Interface for the EntryUndo use case
 */
public interface EntryUndoInputBoundary {

    /**
     * deleteDataPoint
     * @param requestModel represents the request model for the EntryUndo use case
     * @return EntryUndoResponseModel representing the response model for the EntryUndo use case
     */
    EntryUndoResponseModel deleteDataPoint(EntryUndoRequestModel requestModel);
}
