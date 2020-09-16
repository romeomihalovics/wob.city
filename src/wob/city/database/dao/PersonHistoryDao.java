package wob.city.database.dao;

import wob.city.database.dao.abstraction.Dao;
import wob.city.database.dto.ParameterDto;
import wob.city.database.dto.PersonHistoryDto;
import wob.city.util.DtoGenerator;

import java.util.ArrayList;
import java.util.List;

public class PersonHistoryDao implements Dao {
    public List<PersonHistoryDto> fetchPersonHistory(String city) {
        String query = "SELECT * FROM `person_history` WHERE `city` = ?";

        List<ParameterDto> params = new ArrayList<>();
        params.add(new ParameterDto("String", city));

        return DtoGenerator.generatePersonHistoryDto(runQuery(query, params));
    }

    public List<PersonHistoryDto> fetchPersonHistory(String city, String fullName) {
        String query = "SELECT * FROM `person_history` WHERE `city` = ? AND `fullname` = ?";

        List<ParameterDto> params = new ArrayList<>();
        params.add(new ParameterDto("String", city));
        params.add(new ParameterDto("String", fullName));

        return DtoGenerator.generatePersonHistoryDto(runQuery(query, params));
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
