package wob.city.worker;

import wob.city.abstractions.NewsPaper;

import java.util.TimerTask;

public class ReportWorker extends TimerTask {
    private final NewsPaper newsPaper;

    public ReportWorker(NewsPaper newsPaper) {
        this.newsPaper = newsPaper;
    }

    @Override
    public void run() {

    }
}
