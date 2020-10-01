package wob.city.database.dao;

import wob.city.database.dao.abstraction.Dao;
import wob.city.database.dto.DisasterHistoryDto;
import wob.city.database.dto.QueryDto;
import wob.city.util.DtoGenerator;

import java.util.List;

public class DisasterHistoryDao implements Dao {
    public List<DisasterHistoryDto> fetchDisasterHistory(String city, int limit, int fromId) {
        QueryDto queryDto = new QueryDto();

        queryDto.setQuery("SELECT `id`, `city`, `type`, `destroyed_buildings`, `died_families`, `died_people`, `date`, `event`, `reported` FROM `disaster_history` WHERE `city` = ? AND `id` > ? ORDER BY `id` LIMIT ?");

        queryDto.addParam(city);
        queryDto.addParam(fromId);
        queryDto.addParam(limit);

        return DtoGenerator.generateDisasterHistoryDto(runQuery(queryDto));
    }

    public List<DisasterHistoryDto> fetchDisasterHistory(String city, Boolean reported, int limit, int fromId) {
        QueryDto queryDto = new QueryDto();

        queryDto.setQuery("SELECT `id`, `city`, `type`, `destroyed_buildings`, `died_families`, `died_people`, `date`, `event`, `reported` FROM `disaster_history` WHERE `city` = ? AND `reported` = ? AND `id` > ? ORDER BY `id` LIMIT ?");

        queryDto.addParam(city);
        queryDto.addParam(reported);
        queryDto.addParam(fromId);
        queryDto.addParam(limit);

        return DtoGenerator.generateDisasterHistoryDto(runQuery(queryDto));
    }

    public List<DisasterHistoryDto> fetchDisasterHistory(String city, String type, int limit, int fromId) {
        QueryDto queryDto = new QueryDto();

        queryDto.setQuery("SELECT `id`, `city`, `type`, `destroyed_buildings`, `died_families`, `died_people`, `date`, `event`, `reported` FROM `disaster_history` WHERE `city` = ? AND `type` = ? AND `id` > ? ORDER BY `id` LIMIT ?");

        queryDto.addParam(city);
        queryDto.addParam(type);
        queryDto.addParam(fromId);
        queryDto.addParam(limit);

        return DtoGenerator.generateDisasterHistoryDto(runQuery(queryDto));
    }

    public void uploadDisasterHistory(DisasterHistoryDto disasterHistory) {
        QueryDto queryDto = new QueryDto();

        queryDto.setQuery("INSERT INTO `disaster_history` (`city`, `type`, `destroyed_buildings`, `died_families`, `died_people`, `event`, `date`) VALUES (?, ?, ?, ?, ?, ?, ?)");


        queryDto.addParam(disasterHistory.getCityName());
        queryDto.addParam(disasterHistory.getType());
        queryDto.addParam(disasterHistory.getDestroyedBuildings());
        queryDto.addParam(disasterHistory.getDiedFamilies());
        queryDto.addParam(disasterHistory.getDiedPeople());
        queryDto.addParam(disasterHistory.getEvent());
        queryDto.addParam(disasterHistory.getDate());

        runQuery(queryDto);
    }

    public void setDisasterHistoryToReported(String city, int limit, int fromId) {
        QueryDto queryDto = new QueryDto();

        queryDto.setQuery("UPDATE `disaster_history` SET `reported` = 1 WHERE `city` = ? AND `reported` = 0 AND `id` > ? AND `id` < ?");


        queryDto.addParam(city);
        queryDto.addParam(fromId);
        queryDto.addParam(limit+fromId);

        runQuery(queryDto);
    }
}
