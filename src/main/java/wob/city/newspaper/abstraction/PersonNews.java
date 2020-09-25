package wob.city.newspaper.abstraction;

import wob.city.city.City;
import wob.city.database.dao.NewsPaperDao;
import wob.city.database.dto.PersonNewsDto;
import wob.city.database.enums.PersonNewsCategory;
import wob.city.timing.Timing;

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
    public void fetchData(int limit, int fromId) {
        data = newsPaperDao.fetchPersonNews(getCategory(), location.getName(), false);
    }

    @Override
    public void setToReported(int limit, int fromId) {
        newsPaperDao.setPersonNewsToReported(getCategory(), location.getName());
    }

    private List<PersonNewsDto> getData() {
        return data;
    }

    @Override
    public Integer getFetchedSize() {
        return data.size();
    }

    @Override
    public Integer getLastId() {
        return data.get(data.size() - 1) != null ? data.get(data.size() - 1).getId() : 0;
    }

    @Override
    public Timing getTiming() {
        return Timing.AVERAGE_REPORT;
    }

    @Override
    public String toString() {
        return data.toString();
    }

    public abstract PersonNewsCategory getCategory();
}
