package wob.city.newspaper.object;

import wob.city.city.City;
import wob.city.database.dao.NewsPaperDao;
import wob.city.database.dto.PersonNewsDto;
import wob.city.newspaper.abstraction.NewsPaper;

import java.util.ArrayList;
import java.util.List;

public class NewBornNews extends NewsPaper {
    private List<PersonNewsDto> newBornData;
    private final NewsPaperDao newsPaperDao = new NewsPaperDao();

    public NewBornNews(City city){
        super(city, "NewBornNews", true);
        this.newBornData = new ArrayList<>();
    }

    @Override
    public void flushData() {
        newBornData.removeAll(getData());
    }

    @Override
    public void fetchData() {
        newBornData = newsPaperDao.fetchPersonNews("new_born", this.location.getName(), false);
    }

    @Override
    public void setToReported() {
        newsPaperDao.setPersonNewsToReported("new_born", this.location.getName());
    }

    private List<PersonNewsDto> getData() {
        return newBornData;
    }

    @Override
    public String toString() {
        return newBornData.toString();
    }
}
