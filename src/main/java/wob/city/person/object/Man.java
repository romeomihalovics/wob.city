package wob.city.person.object;

import wob.city.person.abstraction.Person;
import wob.city.person.enums.DeathCause;
import wob.city.person.enums.Profession;
import wob.city.person.enums.Type;
import wob.city.util.Calculation;

public class Man extends Person {
    public Man(){
        super(65, 120, NAMES.getMaleName(), Calculation.getRandomIntBetween(65, 120));
    }

    public Man(int normalMinWeight, int normalMaxWeight, int weight, int age) {
        super(normalMinWeight, normalMaxWeight, weight, age, NAMES.getMaleName());
    }

    public Man(Boy newAdult){
        super(newAdult);
    }

    @Override
    public Type getType() {
        return Type.MAN;
    }

    @Override
    public void doAging() {
        addAge();
        if((getAge() >= 60 && Calculation.getRandomIntBetween(0, 100) <= 25) || getAge() >= Person.ABSOLUTE_MAX_AGE) {
            die(DeathCause.AGING);
        }
    }

    @Override
    public void setProfession() {
        if(profession == null && !criminal && !(this instanceof Boy)) {
            int random = Calculation.getRandomIntBetween(1, 100);
            if(random <= 10) {
                profession = Profession.POLICE;
            }else if(random <= 15) {
                profession = Profession.PARAMEDIC;
            }else if(random <= 25) {
                profession = Profession.FIREFIGHTER;
            }else if(random <= 33) {
                criminal = true;
            }

            if(profession != null) {
                location.getProfessionals()
                        .get(profession.getValue())
                        .add(this);
            }

            if(criminal) {
                location.getCriminals().add(this);
            }
        }
    }
}
