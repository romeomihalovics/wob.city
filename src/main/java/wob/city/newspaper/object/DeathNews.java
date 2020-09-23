package wob.city.newspaper.object;

import wob.city.city.City;
import wob.city.database.enums.PersonNewsCategory;
import wob.city.newspaper.abstraction.PersonNews;
import wob.city.newspaper.enums.Folder;


public class DeathNews extends PersonNews {

    public DeathNews(City city) {
        super(city, Folder.DEATH_NEWS.name(), true);
    }

    @Override
    public PersonNewsCategory getCategory() {
        return PersonNewsCategory.DEATH;
    }
}
