package wob.city.person.object;

import wob.city.person.abstraction.Person;
import wob.city.person.enums.Professions;
import wob.city.person.enums.Types;
import wob.city.util.Calculations;

public class Woman extends Person {
    public Woman(){
        super(50, 65, NAMES.getFemaleName(), Calculations.getRandomIntBetween(50, 65));
    }

    public Woman(int normalMinWeight, int normalMaxWeight, int weight, int age) {
        super(normalMinWeight, normalMaxWeight, weight, age, NAMES.getFemaleName());
    }

    public Woman(Girl newAdult) {
        super(newAdult);
    }

    @Override
    public Types getType() {
        return Types.WOMAN;
    }

    @Override
    public void doAging() {
        this.addAge();
        if((this.getAge() >= 60 && Calculations.getRandomIntBetween(0, 100) <= 25) || this.getAge() >= Person.ABSOLUTE_MAX_AGE) {
            this.die();
        }
    }

    @Override
    public void setProfession() {
        if(this.profession == null && !this.isCriminal && !(this instanceof Girl)) {
            int random = Calculations.getRandomIntBetween(1, 100);
            if(random <= 10) {
                this.profession = Professions.POLICE;
            }else if(random <= 15) {
                this.profession = Professions.AMBULANCE;
            }else if(random <= 25) {
                this.profession = Professions.FIREFIGHTER;
            }else if(random <= 33) {
                this.isCriminal = true;
            }

            if(this.profession != null) {
                this.location.getProfessionals()
                        .get(this.profession.getValue())
                        .add(this);
            }

            if(this.isCriminal) {
                this.location.getCriminals().add(this);
            }
        }
    }
}
