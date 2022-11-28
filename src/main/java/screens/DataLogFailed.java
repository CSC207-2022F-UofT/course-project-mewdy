package screens;

public class DataLogFailed extends RuntimeException{
    public DataLogFailed(String error){
        super(error);
    }
}
