package wob.city.newspaper.object;

import wob.city.city.City;
import wob.city.database.enums.PersonNewsCategory;
import wob.city.newspaper.abstraction.PersonNews;
import wob.city.newspaper.enums.Folder;

public class EscapedCriminalNews extends PersonNews {
    public EscapedCriminalNews(City city) {
        super(city, Folder.ESCAPED_CRIMINAL_NEWS.name(), true);
    }

    @Override
    public PersonNewsCategory getCategory() {
        return PersonNewsCategory.ESCAPED_CRIMINAL;
    }
}
