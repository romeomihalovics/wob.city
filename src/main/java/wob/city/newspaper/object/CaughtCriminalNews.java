package wob.city.newspaper.object;

import wob.city.city.City;
import wob.city.database.enums.PersonNewsCategory;
import wob.city.newspaper.abstraction.PersonNews;
import wob.city.newspaper.enums.Folder;

public class CaughtCriminalNews extends PersonNews {

    public CaughtCriminalNews(City city){
        super(city, Folder.CAUGHT_CRIMINAL_NEWS.name(), true);
    }

    @Override
    public PersonNewsCategory getCategory() {
        return PersonNewsCategory.CAUGHT_CRIMINAL;
    }
}
