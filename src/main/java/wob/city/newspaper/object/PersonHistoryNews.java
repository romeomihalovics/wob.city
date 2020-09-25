package wob.city.newspaper.object;

import wob.city.city.City;
import wob.city.database.dao.NewsPaperDao;
import wob.city.database.dao.PersonHistoryDao;
import wob.city.database.dto.ConsumptionNewsDto;
import wob.city.database.dto.PersonHistoryDto;
import wob.city.newspaper.abstraction.NewsPaper;
import wob.city.newspaper.enums.Folder;

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
    public void fetchData() {

    }

    @Override
    public void flushData() {
        personHistoryData.removeAll(getData());
    }

    private List<PersonHistoryDto> getData() {
        return personHistoryData;
    }

    @Override
    public void setToReported() {
    
    }

    @Override
    public String toString() {
        return null;
    }
}
