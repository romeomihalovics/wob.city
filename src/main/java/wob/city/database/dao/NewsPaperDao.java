package wob.city.database.dao;

import wob.city.database.dao.abstraction.Dao;
import wob.city.database.dto.ConsumptionNewsDto;
import wob.city.database.dto.PersonNewsDto;
import wob.city.database.enums.PersonNewsCategory;
import wob.city.util.DtoGenerator;

import java.util.ArrayList;
import java.util.List;

public class NewsPaperDao implements Dao {
    public List<ConsumptionNewsDto> fetchConsumptionNews(String city, int limit, int fromId) {
        String query = "SELECT `id`, `city`, `type`, `amount`, `reported`, `date` FROM `consumption_news` WHERE `city` = ? AND `id` > ? ORDER BY `id` LIMIT ?";

        List<Object> params = new ArrayList<>();
        params.add(city);
        params.add(fromId);
        params.add(limit);

        return DtoGenerator.generateConsumptionNewsDto(runQuery(query, params));
    }

    public List<ConsumptionNewsDto> fetchConsumptionNews(String city, Boolean reported, int limit, int fromId) {
        String query = "SELECT `id`, `city`, `type`, `amount`, `reported`, `date` FROM `consumption_news` WHERE `city` = ? AND `reported` = ? AND `id` > ? ORDER BY `id` LIMIT ?";

        List<Object> params = new ArrayList<>();
        params.add(city);
        params.add(reported);
        params.add(fromId);
        params.add(limit);

        return DtoGenerator.generateConsumptionNewsDto(runQuery(query, params));
    }

    public void uploadConsumptionNews(ConsumptionNewsDto consumptionNews) {
        String query = "INSERT INTO `consumption_news` (`city`, `type`, `amount`, `date`) VALUES (?, ?, ?, NOW())";

        List<Object> params = new ArrayList<>();
        params.add(consumptionNews.getCity());
        params.add(consumptionNews.getType());
        params.add(consumptionNews.getAmount());

        runQuery(query, params);
    }

    public void setConsumptionNewsToReported(String city, int limit, int fromId) {
        String query = "UPDATE `consumption_news` SET `reported` = 1 WHERE `city` = ? AND `reported` = 0 AND `id` > ? AND `id` < ?";

        List<Object> params = new ArrayList<>();
        params.add(city);
        params.add(fromId);
        params.add(limit+fromId);

        runQuery(query, params);
    }

    public List<PersonNewsDto> fetchPersonNews(PersonNewsCategory category, String city, int limit, int fromId) {
        String query = "SELECT `id`, `type`, `full_name`, `age`, `weight`, `height`, `city`, `energy`, `last_food`, `reported`, `involved_person`, `date` FROM `person_news` WHERE `city` = ? AND `category` = ? AND `id` > ? ORDER BY `id` LIMIT ?";

        List<Object> params = new ArrayList<>();
        params.add(city);
        params.add(category.getValue());
        params.add(fromId);
        params.add(limit);

        return DtoGenerator.generatePersonNewsDto(runQuery(query,params));
    }

    public List<PersonNewsDto> fetchPersonNews(PersonNewsCategory category, String city, Boolean reported, int limit, int fromId) {
        String query = "SELECT `id`, `type`, `full_name`, `age`, `weight`, `height`, `city`, `energy`, `last_food`, `reported`, `involved_person`, `date` FROM `person_news` WHERE `city` = ? AND `reported` = ?  AND `category` = ? AND `id` > ? ORDER BY `id` LIMIT ?";

        List<Object> params = new ArrayList<>();
        params.add(city);
        params.add(reported);
        params.add(category.getValue());
        params.add(fromId);
        params.add(limit);

        return DtoGenerator.generatePersonNewsDto(runQuery(query,params));
    }

    public void uploadPersonNews(PersonNewsDto personNews) {
        String query = "INSERT INTO `person_news` (`category`, `type`, `full_name`, `age`, `weight`, `height`, `city`, `energy`, `last_food`, `involved_person`, `date`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";

        List<Object> params = new ArrayList<>();
        params.add(personNews.getCategory().getValue());
        params.add(personNews.getType());
        params.add(personNews.getFullName());
        params.add(personNews.getAge());
        params.add(personNews.getWeight());
        params.add(personNews.getHeight());
        params.add(personNews.getCity());
        params.add(personNews.getEnergy());
        params.add((personNews.getLastFood() != null ? personNews.getLastFood() : "nothing"));
        params.add(personNews.getInvolvedPerson());

        runQuery(query, params);
    }

    public void setPersonNewsToReported(PersonNewsCategory category, String city, int limit, int fromId) {
        String query = "UPDATE `person_news` SET `reported` = 1 WHERE `city` = ? AND `reported` = 0  AND `category` = ? AND `id` > ? AND `id` < ?";

        List<Object> params = new ArrayList<>();
        params.add(city);
        params.add(category.getValue());
        params.add(fromId);
        params.add(limit+fromId);

        runQuery(query, params);
    }
}
