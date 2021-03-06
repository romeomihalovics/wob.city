package wob.city.util;

import wob.city.city.City;
import wob.city.database.dto.ConsumptionNewsDto;
import wob.city.database.dto.RecordTemperatureDto;
import wob.city.disaster.enums.TemperatureLimit;
import wob.city.food.abstraction.Food;
import wob.city.person.abstraction.Person;
import wob.city.person.enums.StatInFamily;
import wob.city.person.enums.Type;
import wob.city.season.abstraction.Season;
import wob.city.season.enums.PartOfDay;
import wob.city.season.object.Autumn;
import wob.city.season.object.Spring;
import wob.city.season.object.Summer;
import wob.city.season.object.Winter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Calculation {
    public static int getRandomIntBetween(int min, int max){
        return (int) ((Math.random() * ((max - min) + 1)) + min);
    }

    public static double getRandomDoubleBetween(double min, double max, int decimalPlaces) {
        return round(Math.random() * (max - min) + min, decimalPlaces);
    }

    public static PartOfDay getPartOfDay(LocalDateTime time) {
        PartOfDay partOfDay;
        if(time.getHour() < 12) {
            partOfDay = PartOfDay.MORNING;
        }else if(time.getHour() < 18) {
            partOfDay = PartOfDay.AFTERNOON;
        }else{
            partOfDay = PartOfDay.EVENING;
        }
        return partOfDay;
    }

    public static Season getRandomSeason() {
        Season season;
        switch (getRandomIntBetween(0,3)) {
            case 0:
                season = new Autumn();
                break;
            case 1:
                season = new Spring();
                break;
            case 2:
                season = new Summer();
                break;
            default:
                season = new Winter();
                break;

        }
        return season;
    }

    public static double calculateTemperature(City city) {
        return Calculation.getRandomDoubleBetween(city.getCurrentSeason().getMinTemperature(city.getCurrentDateTime()), city.getCurrentSeason().getMaxTemperature(city.getCurrentDateTime()), 2);
    }

    public static int getPeopleCountByType(List<Person> people, Type type) {
        int count = 0;
        List<Person> syncPeople = Collections.synchronizedList(people);

        synchronized (syncPeople){
            for(Person person : syncPeople) {
                if(person.getType() == type) {
                    count++;
                }
            }
        }
        return count;
    }

    @SuppressWarnings("unchecked")
    public static List<Person> getRandomNPeople(List<Person> people, int n) {
        List<Person> result = (List<Person>) shuffleList(people);
        return result.subList(0, n);
    }

    public static Food getRandomFood(List<Food> foods) {
        return foods.get(Calculation.getRandomIntBetween(0, foods.size() - 1));
    }

    public static Integer getEnergy(Food fromFood) {
        return (fromFood.getProtein() * 4) + (fromFood.getCarbohydrate() * 4) + (fromFood.getFat() * 9);
    }

    public static Double getAmountByEnergy(int amount, int energy) {
        return Calculation.round(((double) amount / 100) * energy, 2);
    }

    public static Double round(Double number, Integer decimalPlaces){
        if(decimalPlaces < 0) throw new IllegalArgumentException("Decimal places must be greater than 0");

        BigDecimal bigDecimal = BigDecimal.valueOf(number);
        bigDecimal = bigDecimal.setScale(decimalPlaces, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    public static List<?> shuffleList(List<?> list) {
        List<?> shuffled = new LinkedList<>(list);
        Collections.shuffle(shuffled);
        return shuffled;
    }

    public static void sortFertileParentsFromChildren(Person person, List<Person> parents, List<Person> children) {
        if ((person.getType() == Type.MAN || person.getType() == Type.WOMAN) &&
                person.getStatInFamily() == StatInFamily.PARENT && person.getAge() >= 20 && person.getAge() <= 40) {
            parents.add(person);
        }else if(person.getType() == Type.BOY || person.getType() == Type.GIRL){
            children.add(person);
        }
    }

    public static String sumConsumptionToString(List<ConsumptionNewsDto> fetchResult) {
        Double meat = 0.0;
        Double dairy = 0.0;
        Double vegetable = 0.0;
        Double grain = 0.0;
        for (ConsumptionNewsDto row : fetchResult) {
            switch (row.getType().toLowerCase()) {
                case "meat":
                    meat += row.getAmount();
                    break;
                case "dairy":
                    dairy += row.getAmount();
                    break;
                case "vegetable":
                    vegetable += row.getAmount();
                    break;
                case "grain":
                    grain += row.getAmount();
                    break;
                default:
                    break;
            }
        }

        return "{" +
                "\n  \"fromDate\": \"" + (!fetchResult.isEmpty() ? fetchResult.get(0).getDate() : "unknown") + "\"," +
                "\n  \"toDate\": \"" + (!fetchResult.isEmpty() ? fetchResult.get(fetchResult.size() - 1).getDate() : "unknown") + "\"," +
                "\n  \"meat\": \"" + round(meat / 1000, 2) + "kg\"," +
                "\n  \"dairy\": \"" + round(dairy / 1000, 2)  + "kg\"," +
                "\n  \"vegetable\": \"" + round(vegetable / 1000, 2) + "kg\"," +
                "\n  \"grain\": \"" + round(grain / 1000, 2) + "kg\"" +
                "\n}";
    }

    public static boolean checkIfTemperatureRecordsAreOverX(List<RecordTemperatureDto> records, TemperatureLimit temperatureLimit) {
        boolean allOverAmount = true;
        for (RecordTemperatureDto record : records) {
            if (record.getTemperature() < temperatureLimit.getValue()) {
                allOverAmount = false;
                break;
            }
        }
        return allOverAmount;
    }
}
