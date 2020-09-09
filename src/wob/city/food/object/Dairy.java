package wob.city.food.object;

import wob.city.food.abstraction.Food;

import java.util.List;

public class Dairy extends Food {
    public Dairy(List<String> data) {
        super(data);
    }

    @Override
    public String getType() {
        return "Dairy";
    }
}
