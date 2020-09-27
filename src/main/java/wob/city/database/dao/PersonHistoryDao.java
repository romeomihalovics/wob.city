package wob.city.database.dao;

import wob.city.database.dao.abstraction.Dao;
import wob.city.database.dto.PersonHistoryDto;
import wob.city.util.DtoGenerator;

import java.util.ArrayList;
import java.util.List;

public class PersonHistoryDao implements Dao {
    public List<PersonHistoryDto> fetchPersonHistory(String city, int limit, int fromId) {
        String query = "SELECT `id`, `city`, `full_name`, `event`, `date` FROM `person_history` WHERE `city` = ? AND `id` > ? ORDER BY `id` LIMIT ?";

        List<Object> params = new ArrayList<>();
        params.add(city);
        params.add(fromId);
        params.add(limit);

        return DtoGenerator.generatePersonHistoryDto(runQuery(query, params));
    }

    public List<PersonHistoryDto> fetchPersonHistory(String city, String fullName, int limit, int fromId) {
        String query = "SELECT `id`, `city`, `full_name`, `event`, `date` FROM `person_history` WHERE `city` = ? AND `full_name` = ? AND `id` > ? ORDER BY `id` LIMIT ?";

        List<Object> params = new ArrayList<>();
        params.add(city);
        params.add(fullName);
        params.add(fromId);
        params.add(limit);

        return DtoGenerator.generatePersonHistoryDto(runQuery(query, params));
    }

    public void uploadPersonHistory(PersonHistoryDto personHistory) {
        String query = "INSERT INTO `person_history` (`city`, `full_name`, `event`, `date`) VALUES (?, ?, ?, NOW())";

        List<Object> params = new ArrayList<>();
        params.add(personHistory.getCityName());
        params.add(personHistory.getFullName());
        params.add(personHistory.getEvent());

        runQuery(query, params);
    }

    public void setPersonHistoryToReported(String city, int limit, int fromId) {
        String query = "UPDATE `person_history` SET `reported` = 1 WHERE `city` = ? AND `reported` = 0  AND `id` > ? AND `id` < ?";

        List<Object> params = new ArrayList<>();
        params.add(city);
        params.add(fromId);
        params.add(limit+fromId);

        runQuery(query, params);
    }
}
