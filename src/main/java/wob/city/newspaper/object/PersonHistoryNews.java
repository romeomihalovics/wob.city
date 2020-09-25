package wob.city.newspaper.object;

import wob.city.city.City;
import wob.city.database.dao.PersonHistoryDao;
import wob.city.database.dto.PersonHistoryDto;
import wob.city.newspaper.abstraction.NewsPaper;
import wob.city.newspaper.enums.Folder;
import wob.city.timing.Timing;

import java.util.ArrayList;
import java.util.List;

public class PersonHistoryNews extends NewsPaper {
    private List<PersonHistoryDto> personHistoryData;
    private final PersonHistoryDao personHistoryDao = new PersonHistoryDao();

    public PersonHistoryNews(City city) {
        super(city, Folder.PERSON_HISTORY_NEWS.name(), true);
        this.personHistoryData = new ArrayList<>();
    }

    @Override
    public void fetchData(int limit, int fromId) {
        personHistoryData = personHistoryDao.fetchPersonHistory(location.getName(), limit, fromId);
    }

    @Override
    public void flushData() {
        personHistoryData.removeAll(getData());
    }

    private List<PersonHistoryDto> getData() {
        return personHistoryData;
    }

    @Override
    public void setToReported(int limit, int fromId) {
        personHistoryDao.setPersonHistoryToReported(location.getName(), limit,fromId);
    }

    @Override
    public Integer getFetchedSize() {
        return personHistoryData.size();
    }

    @Override
    public Integer getLastId() {
        return personHistoryData.get(personHistoryData.size() - 1) != null ? personHistoryData.get(personHistoryData.size() - 1).getId() : 0;
    }

    @Override
    public Timing getTiming() {
        return Timing.BIG_REPORT;
    }

    @Override
    public String toString() {
        return personHistoryData.toString();
    }
}
