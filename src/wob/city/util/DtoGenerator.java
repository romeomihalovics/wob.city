package wob.city.util;

import wob.city.database.dto.DisasterHistoryDto;
import wob.city.database.dto.PersonHistoryDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DtoGenerator {
    public static List<DisasterHistoryDto> generateDisasterHistoryDto(List<List<Object>> fetchResult){
        List<DisasterHistoryDto> disasterHistory = new ArrayList<>();

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

    public static List<PersonHistoryDto> generatePersonHistoryDto(List<List<Object>> fetchResult) {
        List<PersonHistoryDto> personHistory = new ArrayList<>();

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
}
