package wob.city.newspaper.object;

import wob.city.city.City;
import wob.city.database.enums.PersonNewsCategory;
import wob.city.newspaper.abstraction.PersonNews;
import wob.city.newspaper.enums.Folder;

public class SavedByParamedicNews extends PersonNews {

    public SavedByParamedicNews(City city){
        super(city, Folder.SAVED_BY_PARAMEDIC_NEWS.name(), true);
    }

    @Override
    public PersonNewsCategory getCategory() {
        return PersonNewsCategory.SAVED_BY_PARAMEDIC;
    }
}
