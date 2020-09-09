package wob.city.newspaper.abstraction;

import wob.city.newspaper.worker.ReportWorker;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;

public abstract class NewsPaper {
    protected final String folder;
    protected final Object data;

    public NewsPaper(String folder, Object data) {
        this.folder = folder;
        this.data = data;
        Timer timer = new Timer();
        ReportWorker reportWorker = new ReportWorker(this);

        timer.scheduleAtFixedRate(reportWorker, (60*1000*5), (60*1000*5));
    }

    public Object getData() {
        return data;
    }

    public String getFolder() {
        return folder;
    }

    public abstract void addData(Object data);
    public abstract void flushData();
    @Override
    public abstract String toString();
}
