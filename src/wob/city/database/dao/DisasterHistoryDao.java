package wob.city.database.dao;

import wob.city.database.dao.abstraction.Dao;
import wob.city.database.dto.DisasterHistoryDto;
import wob.city.util.DtoGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DisasterHistoryDao implements Dao {
    public List<DisasterHistoryDto> fetchDisasterHistory(String city) {
        String query = "SELECT * FROM `disaster_history` WHERE `city` = ?";

        List<Object> params = Collections.singletonList(city);

        return DtoGenerator.generateDisasterHistoryDto(runQuery(query, params));
    }

    public List<DisasterHistoryDto> fetchDisasterHistory(String city, Boolean reported) {
        String query = "SELECT * FROM `disaster_history` WHERE `city` = ? AND `reported` = ?";

        List<Object> params = new ArrayList<>();
        params.add(city);
        params.add(reported);

        return DtoGenerator.generateDisasterHistoryDto(runQuery(query, params));
    }

    public List<DisasterHistoryDto> fetchDisasterHistory(String city, String type) {
        String query = "SELECT * FROM `disaster_history` WHERE `city` = ? AND `type` = ?";

        List<Object> params = new ArrayList<>();
        params.add(city);
        params.add(type);

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

    public void setDisasterHistoryToReported(String city) {
        String query = "UPDATE `disaster_history` SET `reported` = 1 WHERE `city` = ?";

        List<Object> params = Collections.singletonList(city);

        runQuery(query, params);
    }
}
