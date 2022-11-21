package controllers;

import models.EntryUndoRequestModel;
import models.EntryUndoResponseModel;
import use_cases.EntryUndoInputBoundary;

public class EntryUndoController {

    private EntryUndoInputBoundary entryUndo;

    public EntryUndoController(EntryUndoInputBoundary entryUndo){

        this.entryUndo = entryUndo;
    }

    public EntryUndoResponseModel deleteLastEntry(String metricName){
        EntryUndoRequestModel requestModel = new EntryUndoRequestModel(metricName);

        return this.entryUndo.deleteDatapoint(requestModel);
    }




}
