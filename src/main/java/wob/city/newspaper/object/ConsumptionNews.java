package wob.city.newspaper.object;

import wob.city.city.City;
import wob.city.database.dao.NewsPaperDao;
import wob.city.database.dto.ConsumptionNewsDto;
import wob.city.newspaper.abstraction.NewsPaper;
import wob.city.newspaper.enums.Folder;
import wob.city.timing.Timing;
import wob.city.util.Calculation;

import java.util.ArrayList;
import java.util.List;

public class ConsumptionNews extends NewsPaper {
    private List<ConsumptionNewsDto> consumptionData;
    private final NewsPaperDao newsPaperDao = new NewsPaperDao();

    public ConsumptionNews(City city){
        super(city, Folder.CONSUMPTION_NEWS.name(), true);
        this.consumptionData = new ArrayList<>();
    }

    @Override
    public void flushData() {
        consumptionData.removeAll(getData());
    }

    @Override
    public void fetchData(int limit, int fromId) {
        consumptionData = newsPaperDao.fetchConsumptionNews(location.getName(), false, limit, fromId);
    }

    @Override
    public void setToReported(int limit, int fromId) {
        newsPaperDao.setConsumptionNewsToReported(location.getName(), limit, fromId);
    }

    @Override
    public Integer getFetchedSize() {
        return consumptionData.size();
    }

    @Override
    public Integer getLastId() {
        return consumptionData.get(consumptionData.size() - 1) != null ? consumptionData.get(consumptionData.size() - 1).getId() : 0;
    }

    @Override
    public Timing getTiming() {
        return Timing.AVERAGE_REPORT;
    }

    private List<ConsumptionNewsDto> getData() {
        return consumptionData;
    }

    @Override
    public String toString() {
        return Calculation.sumConsumptionToString(consumptionData);
    }
}
