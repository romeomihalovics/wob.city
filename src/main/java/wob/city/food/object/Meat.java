package wob.city.food.object;

import wob.city.food.abstraction.Food;
import wob.city.food.enums.Type;

import java.util.List;

public class Meat extends Food {
    public Meat(List<String> data) {
        super(data);
    }

    @Override
    public Type getType() {
        return Type.MEAT;
    }
}
