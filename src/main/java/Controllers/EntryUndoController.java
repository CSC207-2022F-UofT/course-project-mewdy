package Controllers;

import Models.EntryUndoRequestModel;
import Models.EntryUndoResponseModel;
import UseCases.EntryUndo;

public class EntryUndoController {

    private EntryUndo entryUndo;

    public EntryUndoController(EntryUndo entryUndo){

        this.entryUndo = entryUndo;
    }

    EntryUndoResponseModel deleteLastEntry(String metricName){
        EntryUndoRequestModel requestmodel = new EntryUndoRequestModel(metricName);

        return this.entryUndo.deleteDatapoint(requestmodel);
    }




}
