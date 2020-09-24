package wob.city.person.object;

import wob.city.person.abstraction.Person;
import wob.city.person.enums.DeathCause;
import wob.city.person.enums.Profession;
import wob.city.person.enums.Type;
import wob.city.util.Calculation;

public class Woman extends Person {
    public Woman(){
        super(50, 65, NAMES.getFemaleName(), Calculation.getRandomIntBetween(50, 65));
    }

    public Woman(int normalMinWeight, int normalMaxWeight, int weight, int age) {
        super(normalMinWeight, normalMaxWeight, weight, age, NAMES.getFemaleName());
    }

    public Woman(Girl newAdult) {
        super(newAdult);
    }

    @Override
    public Type getType() {
        return Type.WOMAN;
    }

    @Override
    public void doAging() {
        this.addAge();
        if((this.getAge() >= 60 && Calculation.getRandomIntBetween(0, 100) <= 25) || this.getAge() >= Person.ABSOLUTE_MAX_AGE) {
            this.die(DeathCause.AGING);
        }
    }

    @Override
    public void setProfession() {
        if(this.profession == null && !this.criminal && !(this instanceof Girl)) {
            int random = Calculation.getRandomIntBetween(1, 100);
            if(random <= 10) {
                this.profession = Profession.POLICE;
            }else if(random <= 15) {
                this.profession = Profession.PARAMEDIC;
            }else if(random <= 25) {
                this.profession = Profession.FIREFIGHTER;
            }else if(random <= 33) {
                this.criminal = true;
            }

            if(this.profession != null) {
                this.location.getProfessionals()
                        .get(this.profession.getValue())
                        .add(this);
            }

            if(this.criminal) {
                this.location.getCriminals().add(this);
            }
        }
    }
}
