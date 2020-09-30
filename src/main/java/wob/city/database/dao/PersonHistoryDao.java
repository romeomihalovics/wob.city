package wob.city.database.dao;

import wob.city.database.dao.abstraction.Dao;
import wob.city.database.dto.PersonHistoryDto;
import wob.city.database.dto.QueryDto;
import wob.city.util.DtoGenerator;

import java.util.ArrayList;
import java.util.List;

public class PersonHistoryDao implements Dao {
    public List<PersonHistoryDto> fetchPersonHistory(String city, int limit, int fromId) {
        QueryDto queryDto = new QueryDto();

        queryDto.setQuery("SELECT `id`, `city`, `full_name`, `event`, `date` FROM `person_history` WHERE `city` = ? AND `id` > ? ORDER BY `id` LIMIT ?");

        queryDto.addParam(city);
        queryDto.addParam(fromId);
        queryDto.addParam(limit);

        return DtoGenerator.generatePersonHistoryDto(runQuery(queryDto));
    }

    public List<PersonHistoryDto> fetchPersonHistory(String city, String fullName, int limit, int fromId) {
        QueryDto queryDto = new QueryDto();

        queryDto.setQuery("SELECT `id`, `city`, `full_name`, `event`, `date` FROM `person_history` WHERE `city` = ? AND `full_name` = ? AND `id` > ? ORDER BY `id` LIMIT ?");

        queryDto.addParam(city);
        queryDto.addParam(fullName);
        queryDto.addParam(fromId);
        queryDto.addParam(limit);

        return DtoGenerator.generatePersonHistoryDto(runQuery(queryDto));
    }

    public void uploadPersonHistory(PersonHistoryDto personHistory) {
        QueryDto queryDto = new QueryDto();

        queryDto.setQuery("INSERT INTO `person_history` (`city`, `full_name`, `event`, `date`) VALUES (?, ?, ?, NOW())");

        queryDto.addParam(personHistory.getCityName());
        queryDto.addParam(personHistory.getFullName());
        queryDto.addParam(personHistory.getEvent());

        runQuery(queryDto);
    }

    public void setPersonHistoryToReported(String city, int limit, int fromId) {
        QueryDto queryDto = new QueryDto();

        queryDto.setQuery("UPDATE `person_history` SET `reported` = 1 WHERE `city` = ? AND `reported` = 0  AND `id` > ? AND `id` < ?");

        queryDto.addParam(city);
        queryDto.addParam(fromId);
        queryDto.addParam(limit+fromId);

        runQuery(queryDto);
    }

    public boolean runEatingTransaction(String city, String foodName, double amount, PersonHistoryDto personHistoryDto) {
        QueryDto logPersonHistory = new QueryDto();
        QueryDto setAvailableFoodAmount = new QueryDto();

        logPersonHistory.setQuery("INSERT INTO `person_history` (`city`, `full_name`, `event`, `date`) VALUES (?, ?, ?, NOW())");
        logPersonHistory.addParam(personHistoryDto.getCityName());
        logPersonHistory.addParam(personHistoryDto.getFullName());
        logPersonHistory.addParam(personHistoryDto.getEvent());

        setAvailableFoodAmount.setQuery("UPDATE `available_food` SET `amount` = `amount` - ? WHERE `city` = ? AND `food_name` = ? AND `amount` = ?");
        setAvailableFoodAmount.addParam(amount);
        setAvailableFoodAmount.addParam(city);
        setAvailableFoodAmount.addParam(foodName);
        setAvailableFoodAmount.addParam(amount);

        List<QueryDto> transaction = new ArrayList<>();
        transaction.add(logPersonHistory);
        transaction.add(setAvailableFoodAmount);

        return runTransaction(transaction);
    }
}
