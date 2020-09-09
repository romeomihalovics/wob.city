package wob.city.food.object;

import wob.city.food.abstraction.Food;

import java.util.List;

public class Vegetable extends Food {
    public Vegetable(List<String> data) {
        super(data);
    }

    @Override
    public String getType() {
        return "Vegetable";
    }
}
