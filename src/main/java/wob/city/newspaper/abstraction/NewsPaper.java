package wob.city.newspaper.abstraction;

import wob.city.city.City;
import wob.city.newspaper.worker.ReportWorker;
import wob.city.timing.Timing;

import java.util.Timer;

public abstract class NewsPaper {
    protected final String folder;
    protected final ReportWorker reportWorker;
    protected final City location;

    public NewsPaper(City city, String folder, boolean scheduled) {
        this.folder = folder;
        this.location = city;
        this.reportWorker = new ReportWorker(this);

        if(scheduled){
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(reportWorker, getTiming().getValue(), getTiming().getValue());
        }
    }

    public String getFolder() {
        return folder;
    }

    public void manualPublish() {
        reportWorker.run();
    }

    public abstract void fetchData(int limit, int fromId);
    public abstract void flushData();
    public abstract void setToReported(int limit, int fromId);
    public abstract Integer getFetchedSize();
    public abstract Integer getLastId();
    public abstract Timing getTiming();

    @Override
    public abstract String toString();
}
