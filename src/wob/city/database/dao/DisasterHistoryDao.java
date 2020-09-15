package wob.city.database.dao;

import wob.city.database.dao.abstraction.Dao;
import wob.city.database.dto.DisasterHistoryDto;
import wob.city.database.dto.ParameterDto;

import java.util.*;

public class DisasterHistoryDao implements Dao {
    public List<DisasterHistoryDto> fetchDisasterHistory(String city) {
        List<DisasterHistoryDto> disasterHistory = new ArrayList<>();
        String query = "SELECT * FROM `disaster_history` WHERE `city` = ? ORDER BY `date`";

        List<ParameterDto> params = Collections.singletonList(new ParameterDto("String", city));

        List<List<Object>> fetchResult = runQuery(query, params);
        for(List<Object> row : fetchResult) {
            DisasterHistoryDto tempDto = new DisasterHistoryDto();
            tempDto.setId((Integer) row.get(0));
            tempDto.setCityName((String) row.get(1));
            tempDto.setDestroyedBuildings((Integer) row.get(2));
            tempDto.setDiedFamilies((Integer) row.get(3));
            tempDto.setDiedPeople((Integer) row.get(4));
            tempDto.setDate((Date) row.get(5));
            tempDto.setEvent((String) row.get(6));
        }

        return disasterHistory;
    }

    public List<DisasterHistoryDto> fetchDisasterHistory(String city, String type) {
        List<DisasterHistoryDto> disasterHistory = new ArrayList<>();
        String query = "SELECT * FROM `disaster_history` WHERE `city` = ? AND `type` = ? ORDER BY `date`";

        List<ParameterDto> params = new ArrayList<>();
        params.add(new ParameterDto("String", city));
        params.add(new ParameterDto("String", type));

        List<List<Object>> fetchResult = runQuery(query, params);
        for(List<Object> row : fetchResult) {
            DisasterHistoryDto tempDto = new DisasterHistoryDto();
            tempDto.setId((Integer) row.get(0));
            tempDto.setCityName((String) row.get(1));
            tempDto.setDestroyedBuildings((Integer) row.get(2));
            tempDto.setDiedFamilies((Integer) row.get(3));
            tempDto.setDiedPeople((Integer) row.get(4));
            tempDto.setDate((Date) row.get(5));
            tempDto.setEvent((String) row.get(6));
        }

        return disasterHistory;
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
