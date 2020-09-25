package wob.city.newspaper.object;

import wob.city.city.City;
import wob.city.database.enums.PersonNewsCategory;
import wob.city.newspaper.abstraction.PersonNews;
import wob.city.newspaper.enums.Folder;

public class KilledByCriminalNews extends PersonNews {

    public KilledByCriminalNews(City city) {
        super(city, Folder.KILLED_BY_CRIMINAL_NEWS.name(), true);
    }

    @Override
    public PersonNewsCategory getCategory() {
        return PersonNewsCategory.KILLED_BY_CRIMINAL;
    }
}
