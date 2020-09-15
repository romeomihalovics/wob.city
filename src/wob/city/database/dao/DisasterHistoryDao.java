package wob.city.database.dao;

import wob.city.database.dao.abstraction.Dao;
import wob.city.database.dto.DisasterHistoryDto;
import wob.city.database.dto.ParameterDto;
import wob.city.util.DtoGenerator;

import java.util.*;

public class DisasterHistoryDao implements Dao {
    public List<DisasterHistoryDto> fetchDisasterHistory(String city) {
        String query = "SELECT * FROM `disaster_history` WHERE `city` = ? ORDER BY `date`";

        List<ParameterDto> params = Collections.singletonList(new ParameterDto("String", city));

        return DtoGenerator.generateDisasterHistoryDto(runQuery(query, params));
    }

    public List<DisasterHistoryDto> fetchDisasterHistory(String city, String type) {
        String query = "SELECT * FROM `disaster_history` WHERE `city` = ? AND `type` = ? ORDER BY `date`";

        List<ParameterDto> params = new ArrayList<>();
        params.add(new ParameterDto("String", city));
        params.add(new ParameterDto("String", type));

        return DtoGenerator.generateDisasterHistoryDto(runQuery(query, params));
    }

    public void uploadDisasterHistory(DisasterHistoryDto disasterHistory) {
        String query = "INSERT INTO `disaster_history` (`city`, `type`, `destroyed_buildings`, `died_families`, `died_people`, `event`, `date`) VALUES (?, ?, ?, ?, ?, ?, NOW())";

        List<ParameterDto> params = new ArrayList<>();
        params.add(new ParameterDto("String", disasterHistory.getCityName()));
        params.add(new ParameterDto("String", disasterHistory.getType()));
        params.add(new ParameterDto("Integer", disasterHistory.getDestroyedBuildings()));
        params.add(new ParameterDto("Integer", disasterHistory.getDiedFamilies()));
        params.add(new ParameterDto("Integer", disasterHistory.getDiedPeople()));
        params.add(new ParameterDto("String", disasterHistory.getEvent()));

        runQuery(query, params);
    }
}
