package wob.city.database.dao;

import wob.city.database.dao.abstraction.Dao;
import wob.city.database.dto.ConsumptionNewsDto;
import wob.city.database.dto.PersonNewsDto;
import wob.city.util.DtoGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewsPaperDao implements Dao {
    public List<ConsumptionNewsDto> fetchConsumptionNews(String city) {
        String query = "SELECT * FROM `consumption_news` WHERE `city` = ?";

        List<Object> params = Collections.singletonList(city);

        return DtoGenerator.generateConsumptionNewsDto(runQuery(query, params));
    }

    public List<ConsumptionNewsDto> fetchConsumptionNews(String city, Boolean reported) {
        String query = "SELECT * FROM `consumption_news` WHERE `city` = ? AND `reported` = ?";

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
        String query = "UPDATE `consumption_news` SET `reported` = 1 WHERE `city` = ?";

        List<Object> params = Collections.singletonList(city);

        runQuery(query, params);
    }

    public List<PersonNewsDto> fetchPersonNews(String type, String city) {
        String query = (type.equals("death") ? "SELECT * FROM `death_news` WHERE `city` = ?" : "SELECT * FROM `new_born_news` WHERE `city` = ?");

        List<Object> params = Collections.singletonList(city);

        return DtoGenerator.generatePersonNewsDto(runQuery(query,params));
    }

    public List<PersonNewsDto> fetchPersonNews(String type, String city, Boolean reported) {
        String query = (type.equals("death") ? "SELECT * FROM `death_news` WHERE `city` = ? AND `reported` = ?" : "SELECT * FROM `new_born_news` WHERE `city` = ? AND `reported` = ?");

        List<Object> params = new ArrayList<>();
        params.add(city);
        params.add(reported);

        return DtoGenerator.generatePersonNewsDto(runQuery(query,params));
    }

    public void uploadPersonNews(String type, PersonNewsDto personNews) {
        String query = (type.equals("death") ? "INSERT INTO `death_news` (`type`, `fullname`, `age`, `weight`, `height`, `city`, `energy`, `lastfood`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)" : "INSERT INTO `new_born_news` (`type`, `fullname`, `age`, `weight`, `height`, `city`, `energy`, `lastfood`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

        List<Object> params = new ArrayList<>();
        params.add(personNews.getType());
        params.add(personNews.getFullname());
        params.add(personNews.getAge());
        params.add(personNews.getWeight());
        params.add(personNews.getHeight());
        params.add(personNews.getCity());
        params.add(personNews.getEnergy());
        params.add((personNews.getLastFood() != null ? personNews.getLastFood() : "nothing"));

        runQuery(query, params);
    }

    public void setPersonNewsToReported(String type, String city) {
        String query = (type.equals("death") ? "UPDATE `death_news` SET `reported` = 1 WHERE `city` = ?" : "UPDATE `new_born_news` SET `reported` = 1 WHERE `city` = ?");

        List<Object> params = Collections.singletonList(city);

        runQuery(query, params);
    }
}
