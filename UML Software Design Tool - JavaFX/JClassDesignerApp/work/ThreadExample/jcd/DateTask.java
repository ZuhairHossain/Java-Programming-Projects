package ThreadExample.jcd;
import java.util.Date;
import javafx.application.Platform;
import javafx.concurrent.Task;
import ThreadExample.ThreadExample;
public class DateTask extends Task {
    private  ThreadExample app;
    private  Date now;
    public   DateTask(ThreadExample initApp){
    }
    protected  Void call(){
    return null;
    }
}