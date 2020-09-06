package wob.city.util;

import wob.city.abstractions.Food;
import wob.city.abstractions.Person;
import wob.city.objects.City;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandUtils {
    public static City parseCityName(List<City> cities, String input, boolean withPersonName) {
        City result = null;
        Matcher matcher = getRegexMatch(input, (withPersonName) ? "^person -c (.*) -n (.*)$" : "^people -c (.*)$");
        if(matcher != null) {
            result = cities.stream().filter(city -> city.getName().equals(matcher.group(1))).findFirst().orElse(null);
        }
        return result;
    }

    public static Food parseFoodName(List<Food> foods, String input, boolean withGramm){
        Food result = null;
        Matcher matcher = getRegexMatch(input, (withGramm) ? "^food -n (.*) -gramm ([0-9]*)$" : "^food -n (.*)$");
        if(matcher != null) {
            result = getFood(foods, matcher.group(1));
        }
        return result;
    }

    public static Integer parseFoodGramm(String input) {
        Integer result = 0;
        Matcher matcher = getRegexMatch(input, "^food -n (.*) -gramm ([0-9]*)$");
        if (matcher != null){
            result = Integer.parseInt(matcher.group(2));
        }
        return result;
    }

    public static Food getFood(List<Food> foods, String name) {
        return foods.stream().filter(food -> food.getName().equals(name)).findFirst().orElse(null);
    }

    public static Person getPerson(City city, String input) {
        Person person = null;
        Matcher matcher = getRegexMatch(input, "^person -c (.*) -n (.*)$");
        if(matcher != null) {
            person = city.getPeople().stream().filter(p -> matcher.group(2).equals(p.getFullName())).findFirst().orElse(null);
        }
        return person;
    }

    public static Matcher getRegexMatch(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()){
            return matcher;
        }else{
            return null;
        }
    }
}
