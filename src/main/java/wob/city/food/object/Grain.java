package wob.city.food.object;

import wob.city.food.abstraction.Food;
import wob.city.food.enums.Types;

import java.util.List;

public class Grain extends Food {
    public Grain(List<String > data) {
        super(data);
    }

    @Override
    public Types getType() {
        return Types.GRAIN;
    }
}
