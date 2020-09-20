package wob.city.util;

import wob.city.city.City;
import wob.city.database.dto.ConsumptionNewsDto;
import wob.city.database.dto.DisasterHistoryDto;
import wob.city.database.dto.PersonHistoryDto;
import wob.city.database.dto.PersonNewsDto;
import wob.city.person.abstraction.Person;

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
            tempDto.setType((String) row.get(2));
            tempDto.setDestroyedBuildings((Integer) row.get(3));
            tempDto.setDiedFamilies((Integer) row.get(4));
            tempDto.setDiedPeople((Integer) row.get(5));
            tempDto.setDate((Date) row.get(6));
            tempDto.setEvent((String) row.get(7));
            disasterHistory.add(tempDto);
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

    public static List<ConsumptionNewsDto> generateConsumptionNewsDto(List<List<Object>> fetchResult) {
        List<ConsumptionNewsDto> consumptionNews = new ArrayList<>();

        for(List<Object> row : fetchResult) {
            ConsumptionNewsDto tempDto = new ConsumptionNewsDto();
            tempDto.setId((Integer) row.get(0));
            tempDto.setCity((String) row.get(1));
            tempDto.setType((String) row.get(2));
            tempDto.setAmount((Double) row.get(3));
            tempDto.setReported(((Integer) row.get(4) != 0));
            consumptionNews.add(tempDto);
        }

        return consumptionNews;
    }

    public static List<PersonNewsDto> generatePersonNewsDto(List<List<Object>> fetchResult) {
        List<PersonNewsDto> deathNews = new ArrayList<>();

        for(List<Object> row : fetchResult) {
            PersonNewsDto tempDto = new PersonNewsDto();
            tempDto.setId((Integer) row.get(0));
            tempDto.setType((String) row.get(1));
            tempDto.setFullname((String) row.get(2));
            tempDto.setAge((Integer) row.get(3));
            tempDto.setWeight((Integer) row.get(4));
            tempDto.setHeight((Integer) row.get(5));
            tempDto.setCity((String) row.get(6));
            tempDto.setEnergy((String) row.get(7));
            tempDto.setLastFood((String) row.get(8));
            tempDto.setReported(((Integer) row.get(9) != 0));
            deathNews.add(tempDto);
        }

        return deathNews;
    }

    public static DisasterHistoryDto setupDisasterHistoryDto(String event, City city) {
        DisasterHistoryDto tempDto = new DisasterHistoryDto();
        tempDto.setCityName(city.getName());
        tempDto.setEvent(event);
        tempDto.setType(city.getDisaster().get(0).getId());
        tempDto.setDestroyedBuildings(city.getDisaster().get(0).getDestroyed().size());
        tempDto.setDiedFamilies(city.getDisaster().get(0).getDiedFamilies());
        tempDto.setDiedPeople(city.getDisaster().get(0).getDiedPeople());
        return tempDto;
    }

    public static PersonHistoryDto setupPersonHistoryDto(String event, Person person) {
        PersonHistoryDto tempDto = new PersonHistoryDto();
        tempDto.setCityName(person.getLocation().getName());
        tempDto.setFullName(person.getFullName());
        tempDto.setEvent(event);
        return tempDto;
    }

    public static PersonNewsDto setupPersonNewsDto(Person person) {
        PersonNewsDto tempDto = new PersonNewsDto();
        tempDto.setType(person.getType().getValue());
        tempDto.setFullname(person.getFullName());
        tempDto.setAge(person.getAge());
        tempDto.setWeight(person.getWeight());
        tempDto.setHeight(person.getHeight());
        tempDto.setCity(person.getLocation().getName());
        tempDto.setEnergy(person.getEnergy() + "kcal");
        tempDto.setLastFood(person.getLastFood());
        return tempDto;
    }

    public static ConsumptionNewsDto setupConsumptionNewsDto() {
        ConsumptionNewsDto consumptionNewsDto = new ConsumptionNewsDto();
        return consumptionNewsDto;
    }
}
