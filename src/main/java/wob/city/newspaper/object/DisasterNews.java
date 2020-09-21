package wob.city.newspaper.object;

import wob.city.city.City;
import wob.city.database.dao.DisasterHistoryDao;
import wob.city.database.dto.DisasterHistoryDto;
import wob.city.newspaper.abstraction.NewsPaper;

import java.util.ArrayList;
import java.util.List;

public class DisasterNews extends NewsPaper {
    private List<DisasterHistoryDto> disasterData;
    private final DisasterHistoryDao disasterHistoryDao = new DisasterHistoryDao();

    public DisasterNews(City city) {
        super(city, "DisasterNews", false);
        this.disasterData = new ArrayList<>();
    }

    @Override
    public void flushData() {
        disasterData.removeAll(getData());
    }

    @Override
    public void fetchData() {
        disasterData = disasterHistoryDao.fetchDisasterHistory(this.location.getName(), false);
    }

    @Override
    public void setToReported() {
        disasterHistoryDao.setDisasterHistoryToReported(this.location.getName());
    }

    private List<DisasterHistoryDto> getData() {
        return disasterData;
    }

    @Override
    public String toString() {
        return disasterData.toString();
    }
}
