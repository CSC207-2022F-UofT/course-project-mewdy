package controllers;

import models.EntryUndoRequestModel;
import models.EntryUndoResponseModel;
import use_cases.EntryUndoInputBoundary;

/**
 * Controller for the EntryUndo use case
 */
public class EntryUndoController {

    private EntryUndoInputBoundary entryUndo;

    /**
     * Constructor for the EntryUndoController
     *
     * @param entryUndo represents the input boundary for the EntryUndo use case
     */
    public EntryUndoController(EntryUndoInputBoundary entryUndo){

        this.entryUndo = entryUndo;
    }

    /**
     * deleteLastEntry calls the use case interactor, EntryUndo to undo the last entry
     *
     * @param metricName represents the name of the metric to undo the last entry for
     * @return the response model for undoing the last entry
     */
    public EntryUndoResponseModel deleteLastEntry(String metricName){
        EntryUndoRequestModel requestModel = new EntryUndoRequestModel(metricName);

        return this.entryUndo.deleteDataPoint(requestModel);
    }




}
