package wob.city.database.dao;

import wob.city.database.dao.abstraction.Dao;
import wob.city.database.dto.DisasterHistoryDto;
import wob.city.util.DtoGenerator;

import java.util.ArrayList;
import java.util.List;

public class DisasterHistoryDao implements Dao {
    public List<DisasterHistoryDto> fetchDisasterHistory(String city, int limit, int fromId) {
        String query = "SELECT `id`, `city`, `type`, `destroyed_buildings`, `died_families`, `died_people`, `date`, `event`, `reported` FROM `disaster_history` WHERE `city` = ? AND `id` > ? ORDER BY `id` LIMIT ?";

        List<Object> params = new ArrayList<>();
        params.add(city);
        params.add(fromId);
        params.add(limit);

        return DtoGenerator.generateDisasterHistoryDto(runQuery(query, params));
    }

    public List<DisasterHistoryDto> fetchDisasterHistory(String city, Boolean reported, int limit, int fromId) {
        String query = "SELECT `id`, `city`, `type`, `destroyed_buildings`, `died_families`, `died_people`, `date`, `event`, `reported` FROM `disaster_history` WHERE `city` = ? AND `reported` = ? AND `id` > ? ORDER BY `id` LIMIT ?";

        List<Object> params = new ArrayList<>();
        params.add(city);
        params.add(reported);
        params.add(fromId);
        params.add(limit);

        return DtoGenerator.generateDisasterHistoryDto(runQuery(query, params));
    }

    public List<DisasterHistoryDto> fetchDisasterHistory(String city, String type, int limit, int fromId) {
        String query = "SELECT `id`, `city`, `type`, `destroyed_buildings`, `died_families`, `died_people`, `date`, `event`, `reported` FROM `disaster_history` WHERE `city` = ? AND `type` = ? AND `id` > ? ORDER BY `id` LIMIT ?";

        List<Object> params = new ArrayList<>();
        params.add(city);
        params.add(type);
        params.add(fromId);
        params.add(limit);

        return DtoGenerator.generateDisasterHistoryDto(runQuery(query, params));
    }

    public void uploadDisasterHistory(DisasterHistoryDto disasterHistory) {
        String query = "INSERT INTO `disaster_history` (`city`, `type`, `destroyed_buildings`, `died_families`, `died_people`, `event`, `date`) VALUES (?, ?, ?, ?, ?, ?, NOW())";

        List<Object> params = new ArrayList<>();
        params.add(disasterHistory.getCityName());
        params.add(disasterHistory.getType());
        params.add(disasterHistory.getDestroyedBuildings());
        params.add(disasterHistory.getDiedFamilies());
        params.add(disasterHistory.getDiedPeople());
        params.add(disasterHistory.getEvent());

        runQuery(query, params);
    }

    public void setDisasterHistoryToReported(String city, int limit, int fromId) {
        String query = "UPDATE `disaster_history` SET `reported` = 1 WHERE `city` = ? AND `reported` = 0 AND `id` > ? AND `id` < ?";

        List<Object> params = new ArrayList<>();
        params.add(city);
        params.add(fromId);
        params.add(limit+fromId);

        runQuery(query, params);
    }
}
