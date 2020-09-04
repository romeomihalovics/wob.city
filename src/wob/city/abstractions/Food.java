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

    public String toString(Integer gramm) {
        Double rate = ((double) gramm) / 100;
        return this.getClass().getSimpleName() +
                " (" + Calculations.round(100 * rate, 2) + "g) " +
                "{" +
                "name='" + name + '\'' +
                ", protein=" + Calculations.round(protein * rate, 2) +
                ", carbohydrate=" + Calculations.round(carbohydrate * rate, 2) +
                ", fat=" + Calculations.round(fat * rate, 2) +
                "}" +
                "\n " + Calculations.round(this.getEnergy() * rate, 2) + "kcal";
    }
}
