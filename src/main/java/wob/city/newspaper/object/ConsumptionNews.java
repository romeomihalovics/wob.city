package wob.city.newspaper.object;

import wob.city.city.City;
import wob.city.database.dao.NewsPaperDao;
import wob.city.database.dto.ConsumptionNewsDto;
import wob.city.newspaper.abstraction.NewsPaper;
import wob.city.util.Calculations;

import java.util.ArrayList;
import java.util.List;

public class ConsumptionNews extends NewsPaper {
    private List<ConsumptionNewsDto> consumptionData;
    private final NewsPaperDao newsPaperDao = new NewsPaperDao();

    public ConsumptionNews(City city){
        super(city,"ConsumptionNews", true);
        this.consumptionData = new ArrayList<>();
    }

    @Override
    public void flushData() {
        consumptionData.removeAll(getData());
    }

    @Override
    public void fetchData() {
        consumptionData = newsPaperDao.fetchConsumptionNews(this.location.getName(), false);
    }

    @Override
    public void setToReported() {
        newsPaperDao.setConsumptionNewsToReported(this.location.getName());
    }

    private List<ConsumptionNewsDto> getData() {
        return consumptionData;
    }

    @Override
    public String toString() {
        return Calculations.sumConsumptionToString(consumptionData);
    }
}
