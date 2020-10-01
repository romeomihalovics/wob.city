package wob.city.util;

import wob.city.city.City;
import wob.city.database.dto.*;
import wob.city.database.enums.PersonNewsCategory;
import wob.city.person.abstraction.Person;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DtoGenerator {
    public static List<DisasterHistoryDto> generateDisasterHistoryDto(List<HashMap<String, Object>> fetchResult){
        List<DisasterHistoryDto> disasterHistory = new ArrayList<>();

        for(HashMap<String, Object> row : fetchResult) {
            DisasterHistoryDto tempDto = new DisasterHistoryDto();
            tempDto.setId((Integer) row.get("id"));
            tempDto.setCityName((String) row.get("city"));
            tempDto.setType((String) row.get("type"));
            tempDto.setDestroyedBuildings((Integer) row.get("destroyed_buildings"));
            tempDto.setDiedFamilies((Integer) row.get("died_families"));
            tempDto.setDiedPeople((Integer) row.get("died_people"));
            tempDto.setDate(((Timestamp) row.get("date")).toLocalDateTime());
            tempDto.setEvent((String) row.get("event"));
            disasterHistory.add(tempDto);
        }

        return disasterHistory;
    }

    public static List<PersonHistoryDto> generatePersonHistoryDto(List<HashMap<String, Object>> fetchResult) {
        List<PersonHistoryDto> personHistory = new ArrayList<>();

        for(HashMap<String, Object> row : fetchResult) {
            PersonHistoryDto tempDto = new PersonHistoryDto();
            tempDto.setId((Integer) row.get("id"));
            tempDto.setCityName((String) row.get("city"));
            tempDto.setFullName((String) row.get("full_name"));
            tempDto.setEvent((String) row.get("event"));
            tempDto.setDate(((Timestamp) row.get("date")).toLocalDateTime());
            personHistory.add(tempDto);
        }

        return personHistory;
    }

    public static List<ConsumptionNewsDto> generateConsumptionNewsDto(List<HashMap<String, Object>> fetchResult) {
        List<ConsumptionNewsDto> consumptionNews = new ArrayList<>();

        for(HashMap<String, Object> row : fetchResult) {
            ConsumptionNewsDto tempDto = new ConsumptionNewsDto();
            tempDto.setId((Integer) row.get("id"));
            tempDto.setCity((String) row.get("city"));
            tempDto.setType((String) row.get("type"));
            tempDto.setAmount((Double) row.get("amount"));
            tempDto.setReported(((Integer) row.get("reported") != 0));
            tempDto.setDate(((Timestamp) row.get("date")).toLocalDateTime());
            consumptionNews.add(tempDto);
        }

        return consumptionNews;
    }

    public static List<PersonNewsDto> generatePersonNewsDto(List<HashMap<String, Object>> fetchResult) {
        List<PersonNewsDto> deathNews = new ArrayList<>();

        for(HashMap<String, Object> row : fetchResult) {
            PersonNewsDto tempDto = new PersonNewsDto();
            tempDto.setId((Integer) row.get("id"));
            tempDto.setType((String) row.get("type"));
            tempDto.setFullName((String) row.get("full_name"));
            tempDto.setAge((Integer) row.get("age"));
            tempDto.setWeight((Integer) row.get("weight"));
            tempDto.setHeight((Integer) row.get("height"));
            tempDto.setCity((String) row.get("city"));
            tempDto.setEnergy((String) row.get("energy"));
            tempDto.setLastFood((String) row.get("last_food"));
            tempDto.setReported(((Integer) row.get("reported") != 0));
            tempDto.setInvolvedPerson((String) row.get("involved_person"));
            tempDto.setDate(((Timestamp) row.get("date")).toLocalDateTime());
            deathNews.add(tempDto);
        }

        return deathNews;
    }

    public static List<FoodAmountDto> generateFoodAmountDto(List<HashMap<String, Object>> fetchResult) {
        List<FoodAmountDto> foodAmounts = new ArrayList<>();

        for(HashMap<String, Object> row : fetchResult) {
            FoodAmountDto tempDto = new FoodAmountDto();
            tempDto.setId((Integer) row.get("id"));
            tempDto.setCityName((String) row.get("city"));
            tempDto.setFoodName((String) row.get("food_name"));
            tempDto.setAmount((Double) row.get("amount"));
            foodAmounts.add(tempDto);
        }

        return foodAmounts;
    }

    public static DisasterHistoryDto setupDisasterHistoryDto(String event, City city) {
        DisasterHistoryDto tempDto = new DisasterHistoryDto();
        tempDto.setCityName(city.getName());
        tempDto.setEvent(event);
        tempDto.setType(city.getDisaster().get(0).getId());
        tempDto.setDestroyedBuildings(city.getDisaster().get(0).getDestroyedApartments());
        tempDto.setDiedFamilies(city.getDisaster().get(0).getDiedFamilies());
        tempDto.setDiedPeople(city.getDisaster().get(0).getDiedPeople());
        tempDto.setDate(city.getCurrentDateTime());
        return tempDto;
    }

    public static PersonHistoryDto setupPersonHistoryDto(String event, Person person) {
        PersonHistoryDto tempDto = new PersonHistoryDto();
        tempDto.setCityName(person.getLocation().getName());
        tempDto.setFullName(person.getFullName());
        tempDto.setEvent(event);
        tempDto.setDate(person.getLocation().getCurrentDateTime());
        return tempDto;
    }

    public static PersonNewsDto setupPersonNewsDto(PersonNewsCategory category, Person person, Person involvedPerson) {
        PersonNewsDto tempDto = new PersonNewsDto();
        tempDto.setType(person.getType().getValue());
        tempDto.setCategory(category);
        tempDto.setFullName(person.getFullName());
        tempDto.setAge(person.getAge());
        tempDto.setWeight(person.getWeight());
        tempDto.setHeight(person.getHeight());
        tempDto.setCity(person.getLocation().getName());
        tempDto.setEnergy(person.getEnergy() + "kcal");
        tempDto.setLastFood(person.getLastFood());
        tempDto.setInvolvedPerson((involvedPerson != null ? involvedPerson.getFullName() : "nobody"));
        tempDto.setDate(person.getLocation().getCurrentDateTime());
        return tempDto;
    }
}
