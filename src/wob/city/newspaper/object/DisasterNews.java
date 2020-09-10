package wob.city.newspaper.object;

import wob.city.disaster.abstraction.Disaster;
import wob.city.newspaper.abstraction.NewsPaper;

import java.util.ArrayList;
import java.util.List;

public class DisasterNews extends NewsPaper {
    private final List<Disaster> disasterData;

    @SuppressWarnings("unchecked")
    public DisasterNews() {
        super("DisasterNews", new ArrayList<Disaster>(), false);
        this.disasterData = (List<Disaster>) this.getData();
    }

    @Override
    public void addData(Object data) {
        disasterData.add((Disaster) data);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void flushData() {
        disasterData.removeAll((List<Disaster>) this.getData());
    }

    @Override
    public String toString() {
        return disasterData.toString();
    }
}
