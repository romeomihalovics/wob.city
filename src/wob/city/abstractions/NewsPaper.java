package wob.city.abstractions;

import wob.city.worker.ReportWorker;

import java.util.Timer;

public abstract class NewsPaper {
    private final Timer timer;
    private final ReportWorker reportWorker;
    private final String folder;
    private final Object data;

    public NewsPaper(String folder, Object data) {
        this.folder = folder;
        this.data = data;
        this.timer = new Timer();
        this.reportWorker = new ReportWorker(this);

        this.timer.scheduleAtFixedRate(reportWorker, (60*1000*10), (60*1000*10));
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
