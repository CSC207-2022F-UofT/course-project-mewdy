package Controllers;

import Models.EntryUndoRequestModel;
import Models.EntryUndoResponseModel;
import UseCases.EntryUndoInputBoundary;

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
