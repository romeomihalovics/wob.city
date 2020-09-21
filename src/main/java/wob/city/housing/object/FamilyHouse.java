package wob.city.housing.object;

import wob.city.housing.abstraction.Housing;

public class FamilyHouse extends Housing {

    @Override
    public int maxFamilies() {
        return 1;
    }
}
