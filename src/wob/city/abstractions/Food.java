package wob.city.abstractions;

public abstract class Food {
    private String name;
    private Integer energy;
    private Integer protein;
    private Integer carbohydrate;
    private Integer fat;

    public Food(String[] data){
        this.name = data[0];
        this.protein = Integer.valueOf(data[1]);
        this.carbohydrate = Integer.valueOf(data[2]);
        this.fat = Integer.valueOf(data[3]);
    }

    public String getName() {
        return name;
    }

    public Integer getEnergy() {
        return energy;
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

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "name='" + name + '\'' +
                ", energy=" + energy +
                ", protein=" + protein +
                ", carbohydrate=" + carbohydrate +
                ", fat=" + fat +
                '}';
    }
}
