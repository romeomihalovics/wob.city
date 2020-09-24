package wob.city.food.abstraction;

import wob.city.food.enums.Type;
import wob.city.util.Calculation;

import java.util.List;

public abstract class Food {
    protected final String name;
    protected final Integer protein;
    protected final Integer carbohydrate;
    protected final Integer fat;

    public Food(List<String> data){
        this.name = data.get(0);
        this.protein = Integer.valueOf(data.get(1));
        this.carbohydrate = Integer.valueOf(data.get(2));
        this.fat = Integer.valueOf(data.get(3));
    }

    public String getName() {
        return name;
    }

    public Integer getProtein() {
        return protein;
    }

    public Integer getCarbohydrate() {
        return carbohydrate;
    }

    public Integer getFat() {
        return fat;
    }

    public Integer getEnergy() {
        return Calculation.getEnergy(this);
    }

    public String toString(Integer gramm) {
        Double rate = ((double) gramm) / 100;
        return "\n" + getType().getValue() +
                " (" + Calculation.round(100 * rate, 2) + "g) " +
                "{" +
                "name='" + name + '\'' +
                ", protein=" + Calculation.round(protein * rate, 2) +
                ", carbohydrate=" + Calculation.round(carbohydrate * rate, 2) +
                ", fat=" + Calculation.round(fat * rate, 2) +
                "}" +
                "\n " + Calculation.round(getEnergy() * rate, 2) + "kcal \n";
    }

    public abstract Type getType();
}
