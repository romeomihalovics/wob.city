package wob.city.database.dao;

import wob.city.database.dao.abstraction.Dao;
import wob.city.database.dto.ConsumptionNewsDto;
import wob.city.database.dto.PersonNewsDto;
import wob.city.database.dto.QueryDto;
import wob.city.database.enums.PersonNewsCategory;
import wob.city.util.DtoGenerator;

import java.util.List;

public class NewsPaperDao implements Dao {
    public List<ConsumptionNewsDto> fetchConsumptionNews(String city, int limit, int fromId) {
        QueryDto queryDto = new QueryDto();

        queryDto.setQuery("SELECT `id`, `city`, `type`, `amount`, `reported`, `date` FROM `consumption_news` WHERE `city` = ? AND `id` > ? ORDER BY `id` LIMIT ?");

        queryDto.addParam(city);
        queryDto.addParam(fromId);
        queryDto.addParam(limit);

        return DtoGenerator.generateConsumptionNewsDto(runQuery(queryDto));
    }

    public List<ConsumptionNewsDto> fetchConsumptionNews(String city, Boolean reported, int limit, int fromId) {
        QueryDto queryDto = new QueryDto();

        queryDto.setQuery("SELECT `id`, `city`, `type`, `amount`, `reported`, `date` FROM `consumption_news` WHERE `city` = ? AND `reported` = ? AND `id` > ? ORDER BY `id` LIMIT ?");

        queryDto.addParam(city);
        queryDto.addParam(reported);
        queryDto.addParam(fromId);
        queryDto.addParam(limit);

        return DtoGenerator.generateConsumptionNewsDto(runQuery(queryDto));
    }

    public void uploadConsumptionNews(ConsumptionNewsDto consumptionNews) {
        QueryDto queryDto = new QueryDto();

        queryDto.setQuery("INSERT INTO `consumption_news` (`city`, `type`, `amount`, `date`) VALUES (?, ?, ?, NOW())");

        queryDto.addParam(consumptionNews.getCity());
        queryDto.addParam(consumptionNews.getType());
        queryDto.addParam(consumptionNews.getAmount());

        runQuery(queryDto);
    }

    public void setConsumptionNewsToReported(String city, int limit, int fromId) {
        QueryDto queryDto = new QueryDto();

        queryDto.setQuery("UPDATE `consumption_news` SET `reported` = 1 WHERE `city` = ? AND `reported` = 0 AND `id` > ? AND `id` < ?");

        queryDto.addParam(city);
        queryDto.addParam(fromId);
        queryDto.addParam(limit+fromId);

        runQuery(queryDto);
    }

    public List<PersonNewsDto> fetchPersonNews(PersonNewsCategory category, String city, int limit, int fromId) {
        QueryDto queryDto = new QueryDto();

        queryDto.setQuery("SELECT `id`, `type`, `full_name`, `age`, `weight`, `height`, `city`, `energy`, `last_food`, `reported`, `involved_person`, `date` FROM `person_news` WHERE `city` = ? AND `category` = ? AND `id` > ? ORDER BY `id` LIMIT ?");

        queryDto.addParam(city);
        queryDto.addParam(category.getValue());
        queryDto.addParam(fromId);
        queryDto.addParam(limit);

        return DtoGenerator.generatePersonNewsDto(runQuery(queryDto));
    }

    public List<PersonNewsDto> fetchPersonNews(PersonNewsCategory category, String city, Boolean reported, int limit, int fromId) {
        QueryDto queryDto = new QueryDto();

        queryDto.setQuery("SELECT `id`, `type`, `full_name`, `age`, `weight`, `height`, `city`, `energy`, `last_food`, `reported`, `involved_person`, `date` FROM `person_news` WHERE `city` = ? AND `reported` = ?  AND `category` = ? AND `id` > ? ORDER BY `id` LIMIT ?");

        queryDto.addParam(city);
        queryDto.addParam(reported);
        queryDto.addParam(category.getValue());
        queryDto.addParam(fromId);
        queryDto.addParam(limit);

        return DtoGenerator.generatePersonNewsDto(runQuery(queryDto));
    }

    public void uploadPersonNews(PersonNewsDto personNews) {
        QueryDto queryDto = new QueryDto();

        queryDto.setQuery("INSERT INTO `person_news` (`category`, `type`, `full_name`, `age`, `weight`, `height`, `city`, `energy`, `last_food`, `involved_person`, `date`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())");

        queryDto.addParam(personNews.getCategory().getValue());
        queryDto.addParam(personNews.getType());
        queryDto.addParam(personNews.getFullName());
        queryDto.addParam(personNews.getAge());
        queryDto.addParam(personNews.getWeight());
        queryDto.addParam(personNews.getHeight());
        queryDto.addParam(personNews.getCity());
        queryDto.addParam(personNews.getEnergy());
        queryDto.addParam((personNews.getLastFood() != null ? personNews.getLastFood() : "nothing"));
        queryDto.addParam(personNews.getInvolvedPerson());

        runQuery(queryDto);
    }

    public void setPersonNewsToReported(PersonNewsCategory category, String city, int limit, int fromId) {
        QueryDto queryDto = new QueryDto();

        queryDto.setQuery("UPDATE `person_news` SET `reported` = 1 WHERE `city` = ? AND `reported` = 0  AND `category` = ? AND `id` > ? AND `id` < ?");

        queryDto.addParam(city);
        queryDto.addParam(category.getValue());
        queryDto.addParam(fromId);
        queryDto.addParam(limit+fromId);

        runQuery(queryDto);
    }
}
