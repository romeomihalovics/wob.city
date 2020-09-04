package wob.city.abstractions;

import wob.city.util.Calculations;

import java.util.List;

public abstract class Food {
    private final String name;
    private final Integer protein;
    private final Integer carbohydrate;
    private final Integer fat;

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
        return Calculations.getEnergy(this);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "name='" + name + '\'' +
                ", protein=" + protein +
                ", carbohydrate=" + carbohydrate +
                ", fat=" + fat +
                "}" +
                "\n " + this.getEnergy() + "kcal / 100g";
    }
}
