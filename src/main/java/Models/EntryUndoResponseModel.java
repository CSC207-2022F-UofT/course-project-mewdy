package Models;

import java.time.LocalDateTime;
import java.util.Date;

public class EntryUndoResponseModel {
    private String information;
    private double value;
    private LocalDateTime date;
    private String metric;

    public EntryUndoResponseModel(double value, LocalDateTime date, String metric){
        this.value = value;
        this.date = date;
        this.metric = metric;
    }

    public EntryUndoResponseModel(String information){
        this.information = information;
    }
    public String getInformation(){
        return this.information;
    }

    public double getValue() {
        return this.value;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public String getMetric() {
        return metric;
    }
}
