package wob.city.util;

import wob.city.abstractions.Food;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandUtils {
    public static Food parseFoodName(List<Food> foods, String input, boolean withGramm){
        Food result = null;
        Pattern pattern = Pattern.compile((withGramm) ? "^food -n (.*) -gramm ([0-9]*)$" : "^food -n (.*)$");
        Matcher matcher = pattern.matcher(input);
        if(matcher.matches()) {
            result = getFood(foods, matcher.group(1));
        }
        return result;
    }

    public static Integer parseFoodGramm(String input) {
        Integer result = 0;
        Pattern pattern = Pattern.compile("^food -n (.*) -gramm ([0-9]*)$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()){
            result = Integer.parseInt(matcher.group(2));
        }
        return result;
    }

    public static Food getFood(List<Food> foods, String name) {
        return foods.stream().filter(food -> food.getName().equals(name)).findFirst().orElse(null);
    }
}
