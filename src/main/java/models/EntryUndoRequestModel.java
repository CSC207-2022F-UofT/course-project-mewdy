package models;

/**
 * Class that represents the Request Model for EntryUndo
 */
public class EntryUndoRequestModel {

    private final String metricName;

    /**
     * Constructor for EntryUndoRequestModel
     *
     * @param metricName represents the name of the metric
     */
    public EntryUndoRequestModel(String metricName){
        this.metricName = metricName;
    }

    /**
     * Getter for metricName
     *
     * @return the name of the metric
     */
    public String getMetricName(){
        return this.metricName;
    }
}
