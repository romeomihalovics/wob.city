package wob.city.objects;

import wob.city.factory.KidFactory;

public class Boy extends Man {
    public Boy() {
        KidFactory.setDetails(this);
    }
}
