package wob.city.newspaper.object;

import wob.city.city.City;
import wob.city.database.dao.NewsPaperDao;
import wob.city.database.dto.PersonNewsDto;
import wob.city.newspaper.abstraction.NewsPaper;

import java.util.ArrayList;
import java.util.List;

public class DeathNews extends NewsPaper {
    private List<PersonNewsDto> deathData;
    private final NewsPaperDao newsPaperDao = new NewsPaperDao();

    public DeathNews(City city) {
        super(city, "DeathNews", true);
        this.deathData = new ArrayList<>();
    }

    @Override
    public void flushData() {
        deathData.removeAll(getData());
    }

    @Override
    public void fetchData() {
        deathData = newsPaperDao.fetchPersonNews("death", this.location.getName(), false);
    }

    @Override
    public void setToReported() {
        newsPaperDao.setPersonNewsToReported("death", this.location.getName());
    }

    private List<PersonNewsDto> getData() {
        return deathData;
    }

    @Override
    public String toString() {
        return deathData.toString();
    }
}
