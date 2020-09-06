package wob.city.util;

import wob.city.abstractions.Food;
import wob.city.abstractions.Person;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Calculations {
    public static int getRandomIntBetween(int min, int max){
        return (int) ((Math.random() * ((max - min) + 1)) + min);
    }

    public static int getPeopleCountByType(List<Person> people, Class<?> type) {
        int count = 0;
        for(Person person : people) {
            if(person.getClass().equals(type)) {
                count++;
            }
        }
        return count;
    }

    public static List<Person> getRandomNPeople(List<Person> people, int n) {
        List<Person> result = new LinkedList<>(people);
        Collections.shuffle(result);
        return result.subList(0, n);
    }

    public static Integer getEnergy(Food fromFood) {
        return (fromFood.getProtein() * 4) + (fromFood.getCarbohydrate() * 4) + (fromFood.getFat() * 9);
    }

    public static Double round(Double number, Integer decimalPlaces){
        if(decimalPlaces < 0) throw new IllegalArgumentException("Decimal places must be greater than 0");

        BigDecimal bigDecimal = BigDecimal.valueOf(number);
        bigDecimal = bigDecimal.setScale(decimalPlaces, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}
