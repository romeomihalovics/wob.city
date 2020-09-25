package wob.city.database.dao;

import wob.city.database.dao.abstraction.Dao;
import wob.city.database.dto.ConsumptionNewsDto;
import wob.city.database.dto.PersonNewsDto;
import wob.city.database.enums.PersonNewsCategory;
import wob.city.util.DtoGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewsPaperDao implements Dao {
    public List<ConsumptionNewsDto> fetchConsumptionNews(String city) {
        String query = "SELECT `id`, `city`, `type`, `amount`, `reported` FROM `consumption_news` WHERE `city` = ?";

        List<Object> params = Collections.singletonList(city);

        return DtoGenerator.generateConsumptionNewsDto(runQuery(query, params));
    }

    public List<ConsumptionNewsDto> fetchConsumptionNews(String city, Boolean reported) {
        String query = "SELECT `id`, `city`, `type`, `amount`, `reported` FROM `consumption_news` WHERE `city` = ? AND `reported` = ?";

        List<Object> params = new ArrayList<>();
        params.add(city);
        params.add(reported);

        return DtoGenerator.generateConsumptionNewsDto(runQuery(query, params));
    }

    public void uploadConsumptionNews(ConsumptionNewsDto consumptionNews) {
        String query = "INSERT INTO `consumption_news` (`city`, `type`, `amount`) VALUES (?, ?, ?)";

        List<Object> params = new ArrayList<>();
        params.add(consumptionNews.getCity());
        params.add(consumptionNews.getType());
        params.add(consumptionNews.getAmount());

        runQuery(query, params);
    }

    public void setConsumptionNewsToReported(String city) {
        String query = "UPDATE `consumption_news` SET `reported` = 1 WHERE `city` = ? AND `reported` = 0";

        List<Object> params = Collections.singletonList(city);

        runQuery(query, params);
    }

    public List<PersonNewsDto> fetchPersonNews(PersonNewsCategory category, String city) {
        String query = "SELECT `id`, `type`, `fullname`, `age`, `weight`, `height`, `city`, `energy`, `lastfood`, `reported`, `involved_person` FROM `person_news` WHERE `city` = ? AND `category` = '" + category.getValue() + "'";

        List<Object> params = Collections.singletonList(city);

        return DtoGenerator.generatePersonNewsDto(runQuery(query,params));
    }

    public List<PersonNewsDto> fetchPersonNews(PersonNewsCategory category, String city, Boolean reported) {
        String query = "SELECT `id`, `type`, `fullname`, `age`, `weight`, `height`, `city`, `energy`, `lastfood`, `reported`, `involved_person` FROM `person_news` WHERE `city` = ? AND `reported` = ?  AND `category` = '" + category.getValue() + "'";

        List<Object> params = new ArrayList<>();
        params.add(city);
        params.add(reported);

        return DtoGenerator.generatePersonNewsDto(runQuery(query,params));
    }

    public void uploadPersonNews(PersonNewsDto personNews) {
        String query = "INSERT INTO `person_news` (`category`, `type`, `fullname`, `age`, `weight`, `height`, `city`, `energy`, `lastfood`, `involved_person`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        List<Object> params = new ArrayList<>();
        params.add(personNews.getCategory().getValue());
        params.add(personNews.getType());
        params.add(personNews.getFullname());
        params.add(personNews.getAge());
        params.add(personNews.getWeight());
        params.add(personNews.getHeight());
        params.add(personNews.getCity());
        params.add(personNews.getEnergy());
        params.add((personNews.getLastFood() != null ? personNews.getLastFood() : "nothing"));
        params.add(personNews.getInvolvedPerson());

        runQuery(query, params);
    }

    public void setPersonNewsToReported(PersonNewsCategory category, String city) {
        String query = "UPDATE `person_news` SET `reported` = 1 WHERE `city` = ? AND `reported` = 0  AND `category` = '" + category.getValue() + "'";

        List<Object> params = Collections.singletonList(city);

        runQuery(query, params);
    }
}
