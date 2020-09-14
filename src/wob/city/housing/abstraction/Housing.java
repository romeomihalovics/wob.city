package wob.city.housing.abstraction;

import wob.city.family.Family;

import java.util.ArrayList;
import java.util.List;

public abstract class Housing {
    protected List<Family> families = new ArrayList<>();

    public List<Family> getFamilies(){
        return families;
    }

    public void addFamily(Family family) {
        if(this.families.size() < maxFamilies()) {
            this.families.add(family);
        }
    }

    public abstract int maxFamilies();

}
