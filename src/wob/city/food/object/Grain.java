package wob.city.food.object;

import wob.city.food.abstraction.Food;

import java.util.List;

public class Grain extends Food {
    public Grain(List<String > data) {
        super(data);
    }

    @Override
    public String getType() {
        return "Grain";
    }
}
