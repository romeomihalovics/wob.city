package wob.city.person.worker;

import wob.city.person.abstraction.Person;
import wob.city.person.enums.DeathCause;
import wob.city.util.Calculation;

import java.util.TimerTask;

public class CPRWorker extends TimerTask {
    private final Person paramedic;
    private final Person toSave;
    private final DeathCause deathCause;
    private final String event;

    public CPRWorker(Person paramedic, Person toSave, DeathCause deathCause, String event) {
        this.paramedic = paramedic;
        this.toSave = toSave;
        this.deathCause = deathCause;
        this.event = event;
    }

    @Override
    public void run() {
        String diedEvent = "A paramedic ("+ paramedic.getFullName()+") failed to save a ";
        if (Calculation.getRandomIntBetween(0,100) <= 20 && toSave.getAge() < Person.ABSOLUTE_MAX_AGE) {
            if(deathCause == DeathCause.STARVED) {
                toSave.setEnergy(2500);
            }
            // Person saved
        }else{
            toSave.recordAsDied(diedEvent+event);
        }
        paramedic.setBusy(false);
    }
}
