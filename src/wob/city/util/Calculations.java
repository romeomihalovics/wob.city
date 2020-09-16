package wob.city.util;

import wob.city.food.abstraction.Food;
import wob.city.person.abstraction.Person;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Calculations {
    public static int getRandomIntBetween(int min, int max){
        return (int) ((Math.random() * ((max - min) + 1)) + min);
    }

    public static int getPeopleCountByType(List<Person> people, String type) {
        int count = 0;
        List<Person> syncPeople = Collections.synchronizedList(people);

        synchronized (syncPeople){
            for(Person person : syncPeople) {
                if(person.getType().equals(type)) {
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
        return foods.get(Calculations.getRandomIntBetween(0, foods.size() - 1));
    }

    public static Integer getEnergy(Food fromFood) {
        return (fromFood.getProtein() * 4) + (fromFood.getCarbohydrate() * 4) + (fromFood.getFat() * 9);
    }

    public static Double getAmountByEnergy(int amount, int energy) {
        return Calculations.round(((double) amount / 100) * energy, 2);
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
}
