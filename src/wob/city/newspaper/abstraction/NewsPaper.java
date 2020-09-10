package wob.city.newspaper.abstraction;

import wob.city.newspaper.worker.ReportWorker;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;

public abstract class NewsPaper {
    protected final String folder;
    protected final Object data;
    protected final ReportWorker reportWorker;

    public NewsPaper(String folder, Object data, boolean scheduled) {
        this.folder = folder;
        this.data = data;

        this.reportWorker = new ReportWorker(this);

        if(scheduled){
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(reportWorker, (60*1000*10), (60*1000*10));
        }
    }

    public Object getData() {
        return data;
    }

    public String getFolder() {
        return folder;
    }

    public void manualPublish() {
        reportWorker.run();
    }

    public abstract void addData(Object data);
    public abstract void flushData();
    @Override
    public abstract String toString();
}
