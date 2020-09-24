package wob.city.newspaper.abstraction;

import wob.city.city.City;
import wob.city.database.dao.NewsPaperDao;
import wob.city.database.dto.PersonNewsDto;
import wob.city.database.enums.PersonNewsCategory;

import java.util.ArrayList;
import java.util.List;

public abstract class PersonNews extends NewsPaper {
    protected List<PersonNewsDto> data;
    protected final NewsPaperDao newsPaperDao = new NewsPaperDao();

    public PersonNews(City city, String folder, boolean scheduled) {
        super(city, folder, scheduled);
        this.data = new ArrayList<>();
    }

    @Override
    public void flushData() {
        data.removeAll(getData());
    }

    @Override
    public void fetchData() {
        data = newsPaperDao.fetchPersonNews(getCategory(), location.getName(), false);
    }

    @Override
    public void setToReported() {
        newsPaperDao.setPersonNewsToReported(getCategory(), location.getName());
    }

    private List<PersonNewsDto> getData() {
        return data;
    }

    @Override
    public String toString() {
        return data.toString();
    }

    public abstract PersonNewsCategory getCategory();
}
