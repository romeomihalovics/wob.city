package wob.city.food.object;

import wob.city.food.abstraction.Food;
import wob.city.food.enums.Types;

import java.util.List;

public class Vegetable extends Food {
    public Vegetable(List<String> data) {
        super(data);
    }

    @Override
    public Types getType() {
        return Types.VEGETABLE;
    }
}
