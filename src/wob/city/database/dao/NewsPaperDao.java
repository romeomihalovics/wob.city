package wob.city.database.dao;

import wob.city.database.dao.abstraction.Dao;
import wob.city.database.dto.ConsumptionNewsDto;
import wob.city.database.dto.PersonNewsDto;
import wob.city.database.dto.ParameterDto;
import wob.city.util.DtoGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewsPaperDao implements Dao {
    public List<ConsumptionNewsDto> fetchConsumptionNews(String city) {
        String query = "SELECT * FROM `consumption_news` WHERE `city` = ?";

        List<ParameterDto> params = Collections.singletonList(new ParameterDto("String", city));

        return DtoGenerator.generateConsumptionNewsDto(runQuery(query, params));
    }

    public List<ConsumptionNewsDto> fetchConsumptionNews(String city, Boolean reported) {
        String query = "SELECT * FROM `consumption_news` WHERE `city` = ? AND `reported` = ?";

        List<ParameterDto> params = new ArrayList<>();
        params.add(new ParameterDto("String", city));
        params.add(new ParameterDto("Boolean", reported));

        return DtoGenerator.generateConsumptionNewsDto(runQuery(query, params));
    }

    public void uploadConsumptionNews(ConsumptionNewsDto consumptionNews) {
        String query = "INSERT INTO `consumption_news` (`city`, `type`, `amount`) VALUES (?, ?, ?)";

        List<ParameterDto> params = new ArrayList<>();
        params.add(new ParameterDto("String", consumptionNews.getCity()));
        params.add(new ParameterDto("String", consumptionNews.getType()));
        params.add(new ParameterDto("Double", consumptionNews.getAmount()));

        runQuery(query, params);
    }

    public void setConsumptionNewsToReported(String city) {
        String query = "UPDATE `consumption_news` SET `reported` = 1 WHERE `city` = ?";

        List<ParameterDto> params = Collections.singletonList(new ParameterDto("String", city));

        runQuery(query, params);
    }

    public List<PersonNewsDto> fetchPersonNews(String type, String city) {
        String query = (type.equals("death") ? "SELECT * FROM `death_news` WHERE `city` = ?" : "SELECT * FROM `new_born_news` WHERE `city` = ?");

        List<ParameterDto> params = Collections.singletonList(new ParameterDto("String", city));

        return DtoGenerator.generatePersonNewsDto(runQuery(query,params));
    }

    public List<PersonNewsDto> fetchPersonNews(String type, String city, Boolean reported) {
        String query = (type.equals("death") ? "SELECT * FROM `death_news` WHERE `city` = ? AND `reported` = ?" : "SELECT * FROM `new_born_news` WHERE `city` = ? AND `reported` = ?");

        List<ParameterDto> params = new ArrayList<>();
        params.add(new ParameterDto("String", city));
        params.add(new ParameterDto("Boolean", reported));

        return DtoGenerator.generatePersonNewsDto(runQuery(query,params));
    }

    public void uploadPersonNews(String type, PersonNewsDto deathNews) {
        String query = (type.equals("death") ? "INSERT INTO `death_news` (`type`, `fullname`, `age`, `weight`, `height`, `city`, `energy`, `lastfood`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)" : "INSERT INTO `new_born_news` (`type`, `fullname`, `age`, `weight`, `height`, `city`, `energy`, `lastfood`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

        List<ParameterDto> params = new ArrayList<>();
        params.add(new ParameterDto("String", deathNews.getType()));
        params.add(new ParameterDto("String", deathNews.getFullname()));
        params.add(new ParameterDto("Integer", deathNews.getAge()));
        params.add(new ParameterDto("Integer", deathNews.getWeight()));
        params.add(new ParameterDto("Integer", deathNews.getHeight()));
        params.add(new ParameterDto("String", deathNews.getCity()));
        params.add(new ParameterDto("String", deathNews.getEnergy()));
        params.add(new ParameterDto("String", deathNews.getLastFood()));

        runQuery(query, params);
    }

    public void setPersonNewsToReported(String type, String city) {
        String query = (type.equals("death") ? "UPDATE `death_news` SET `reported` = 1 WHERE `city` = ?" : "UPDATE `new_born_news` SET `reported` = 1 WHERE `city` = ?");

        List<ParameterDto> params = Collections.singletonList(new ParameterDto("String", city));

        runQuery(query, params);
    }
}
