package wob.city.housing.abstraction;

import wob.city.family.Family;

import java.util.List;

public abstract class Housing {
    protected List<Family> families;

    public List<Family> getFamilies(){
        return families;
    }

    public void addFamily(Family family) {
        this.families.add(family);
    }

    public abstract int maxFamilies();

}
