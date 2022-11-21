package models;

public class EntryUndoRequestModel {

    private String metricName;

    public EntryUndoRequestModel(String metricName){
        this.metricName = metricName;
    }

    public String getMetricName(){
        return this.metricName;
    }
}
