package wob.city.newspaper.object;

import wob.city.city.City;
import wob.city.database.dao.DisasterHistoryDao;
import wob.city.database.dto.DisasterHistoryDto;
import wob.city.newspaper.abstraction.NewsPaper;
import wob.city.newspaper.enums.Folder;
import wob.city.timing.Timing;

import java.util.ArrayList;
import java.util.List;

public class DisasterNews extends NewsPaper {
    private List<DisasterHistoryDto> disasterData;
    private final DisasterHistoryDao disasterHistoryDao = new DisasterHistoryDao();

    public DisasterNews(City city) {
        super(city, Folder.DISASTER_NEWS.name(), false);
        this.disasterData = new ArrayList<>();
    }

    @Override
    public void flushData() {
        disasterData.removeAll(getData());
    }

    @Override
    public void fetchData(int limit, int fromId) {
        disasterData = disasterHistoryDao.fetchDisasterHistory(location.getName(), false);
    }

    @Override
    public void setToReported(int limit, int fromId) {
        disasterHistoryDao.setDisasterHistoryToReported(location.getName());
    }

    private List<DisasterHistoryDto> getData() {
        return disasterData;
    }

    public Integer getFetchedSize() {
        return disasterData.size();
    }

    public Integer getLastId() {
        return disasterData.get(disasterData.size() - 1) != null ? disasterData.get(disasterData.size() - 1).getId() : 0;
    }

    @Override
    public Timing getTiming() {
        return Timing.AVERAGE_REPORT;
    }

    @Override
    public String toString() {
        return disasterData.toString();
    }
}
