package wob.city.newspaper.object;

import wob.city.city.City;
import wob.city.database.enums.PersonNewsCategory;
import wob.city.newspaper.abstraction.PersonNews;
import wob.city.newspaper.enums.Folder;

public class NewBornNews extends PersonNews {

    public NewBornNews(City city){
        super(city, Folder.NEW_BORN_NEWS.name(), true);
    }

    @Override
    public PersonNewsCategory getCategory() {
        return PersonNewsCategory.NEW_BORN;
    }
}
