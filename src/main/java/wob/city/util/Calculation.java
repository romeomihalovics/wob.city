package wob.city.util;

import wob.city.database.dto.ConsumptionNewsDto;
import wob.city.food.abstraction.Food;
import wob.city.person.abstraction.Person;
import wob.city.person.enums.Type;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Calculation {
    public static int getRandomIntBetween(int min, int max){
        return (int) ((Math.random() * ((max - min) + 1)) + min);
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
                "\n  \"fromDate\": \"" + (fetchResult.get(0) != null ? fetchResult.get(0).getDate() : "unknown") + "\"," +
                "\n  \"toDate\": \"" + (fetchResult.get(fetchResult.size() - 1) != null ? fetchResult.get(fetchResult.size() - 1).getDate() : "unknown") + "\"," +
                "\n  \"meat\": \"" + round(meat / 1000, 2) + "kg\"," +
                "\n  \"dairy\": \"" + round(dairy / 1000, 2)  + "kg\"," +
                "\n  \"vegetable\": \"" + round(vegetable / 1000, 2) + "kg\"," +
                "\n  \"grain\": \"" + round(grain / 1000, 2) + "kg\"" +
                "\n}";
    }
}
