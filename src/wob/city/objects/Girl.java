package wob.city.objects;

import wob.city.factory.KidFactory;

public class Girl extends Woman {
    public Girl() {
        KidFactory.setDetails(this);
    }
}
