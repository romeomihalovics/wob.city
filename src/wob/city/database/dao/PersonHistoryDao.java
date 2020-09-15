package wob.city.database.dao;

import wob.city.database.dao.abstraction.Dao;
import wob.city.database.dto.ParameterDto;
import wob.city.database.dto.PersonHistoryDto;

import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonHistoryDao implements Dao {
    public List<PersonHistoryDto> fetchPersonHistory(String city) {
        List<PersonHistoryDto> personHistory = new ArrayList<>();
        String query = "SELECT * FROM `person_history` WHERE `city` = ? ORDER BY `date`";

        List<ParameterDto> params = new ArrayList<>();
        params.add(new ParameterDto("String", city));

        List<List<Object>> fetchResult = runQuery(query, params);
        for(List<Object> row : fetchResult) {
            PersonHistoryDto tempDto = new PersonHistoryDto();
            tempDto.setId((Integer) row.get(0));
            tempDto.setCityName((String) row.get(1));
            tempDto.setFullName((String) row.get(2));
            tempDto.setEvent((String) row.get(3));
            tempDto.setDate((Date) row.get(4));
            personHistory.add(tempDto);
        }

        return personHistory;
    }

    public List<PersonHistoryDto> fetchPersonHistory(String city, String fullName) {
        List<PersonHistoryDto> personHistory = new ArrayList<>();
        String query = "SELECT * FROM `person_history` WHERE `city` = ? AND `fullname` = ? ORDER BY `date`";

        List<ParameterDto> params = new ArrayList<>();
        params.add(new ParameterDto("String", city));
        params.add(new ParameterDto("String", fullName));

        List<List<Object>> fetchResult = runQuery(query, params);
        for(List<Object> row : fetchResult) {
            PersonHistoryDto tempDto = new PersonHistoryDto();
            tempDto.setId((Integer) row.get(0));
            tempDto.setCityName((String) row.get(1));
            tempDto.setFullName((String) row.get(2));
            tempDto.setEvent((String) row.get(3));
            tempDto.setDate((Date) row.get(4));
            personHistory.add(tempDto);
        }

        return personHistory;
    }

    public void uploadPersonHistory(PersonHistoryDto personHistory) {
        String query = "INSERT INTO `person_history` (`city`, `fullname`, `event`, `date`) VALUES (?, ?, ?, NOW())";

        List<ParameterDto> params = new ArrayList<>();
        params.add(new ParameterDto("String", personHistory.getCityName()));
        params.add(new ParameterDto("String", personHistory.getFullName()));
        params.add(new ParameterDto("String", personHistory.getEvent()));

        runQuery(query, params);
    }
}
