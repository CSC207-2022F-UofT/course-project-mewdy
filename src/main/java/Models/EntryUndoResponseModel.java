package Models;

import java.util.Date;

public class EntryUndoResponseModel {
    private String error;
    private double value;
    private Date date;
    private String metric;

    public EntryUndoResponseModel(double value, Date date, String metric){
        this.value = value;
        this.date = date;
        this.metric = metric;
    }

    public EntryUndoResponseModel(String error){
        this.error = error;
    }
    public String getError(){
        return error;
    }

}
