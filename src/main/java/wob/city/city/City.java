package wob.city.city;

import wob.city.city.worker.FoodFactoryWorker;
import wob.city.city.worker.NewBornWorker;
import wob.city.city.worker.TimeWorker;
import wob.city.console.logger.ActivityLogger;
import wob.city.console.logger.ConsoleLogger;
import wob.city.database.dao.DisasterHistoryDao;
import wob.city.database.dao.NewsPaperDao;
import wob.city.database.dao.TemperatureDao;
import wob.city.database.dto.TemperatureReportDto;
import wob.city.database.enums.PersonNewsCategory;
import wob.city.disaster.abstraction.Consequence;
import wob.city.disaster.abstraction.Disaster;
import wob.city.family.Family;
import wob.city.food.abstraction.Food;
import wob.city.housing.abstraction.Housing;
import wob.city.housing.object.BrickBlock;
import wob.city.housing.object.FamilyHouse;
import wob.city.housing.object.PanelBlock;
import wob.city.newspaper.object.*;
import wob.city.person.abstraction.Person;
import wob.city.person.enums.DeathCause;
import wob.city.person.enums.Profession;
import wob.city.person.enums.StatInFamily;
import wob.city.person.enums.Type;
import wob.city.season.abstraction.Season;
import wob.city.season.object.Autumn;
import wob.city.season.object.Spring;
import wob.city.season.object.Summer;
import wob.city.season.object.Winter;
import wob.city.timing.Timing;
import wob.city.util.Calculation;
import wob.city.util.DtoGenerator;

import java.time.LocalDateTime;
import java.util.*;

public class City {
    private String name;
    private LocalDateTime currentDateTime;
    private Season currentSeason;
    private Double currentTemperature;
    private final List<Family> families;
    private final List<Housing> houses;
    private final List<Person> people;
    private final List<Food> foodRecipes;
    private final List<Person> died = Collections.synchronizedList(new ArrayList<>());
    private Timer timer;
    private NewBornWorker newBornWorker;
    private FoodFactoryWorker foodFactoryWorker;
    private TimeWorker timeWorker;
    private final ConsumptionNews consumptionNews;
    private final DeathNews deathNews;
    private final NewBornNews newBornNews;
    private final SavedByParamedicNews savedByParamedicNews;
    private final SavedByFireFighterNews savedByFireFighterNews;
    private final CaughtCriminalNews caughtCriminalNews;
    private final EscapedCriminalNews escapedCriminalNews;
    private final KilledByCriminalNews killedByCriminalNews;
    private final PersonHistoryNews personHistoryNews;
    private final NewsPaperDao newsPaperDao;
    private final List<Disaster> disaster = Collections.synchronizedList(new ArrayList<>());
    private final DisasterNews disasterNews;
    private final DisasterHistoryDao disasterHistoryDao = new DisasterHistoryDao();
    private final TemperatureDao temperatureDao = new TemperatureDao();
    private final HashMap<String, List<Person>> professionals = new HashMap<>();
    private final List<Person> criminals = new ArrayList<>();

    public City(String name, List<Person> people, List<Food> foods) {
        this.name = name;
        this.currentDateTime = LocalDateTime.now();
        this.currentSeason = Calculation.getRandomSeason();
        this.currentTemperature = Calculation.calculateTemperature(this);
        this.families = new ArrayList<>();
        this.houses = new ArrayList<>();
        this.people = people;
        this.foodRecipes = foods;
        this.consumptionNews = new ConsumptionNews(this);
        this.deathNews = new DeathNews(this);
        this.newBornNews = new NewBornNews(this);
        this.disasterNews = new DisasterNews(this);
        this.savedByParamedicNews = new SavedByParamedicNews(this);
        this.savedByFireFighterNews = new SavedByFireFighterNews(this);
        this.caughtCriminalNews = new CaughtCriminalNews(this);
        this.escapedCriminalNews = new EscapedCriminalNews(this);
        this.killedByCriminalNews = new KilledByCriminalNews(this);
        this.personHistoryNews = new PersonHistoryNews(this);
        this.newsPaperDao = new NewsPaperDao();

        this.professionals.put(Profession.PARAMEDIC.getValue(), new ArrayList<>());
        this.professionals.put(Profession.POLICE.getValue(), new ArrayList<>());
        this.professionals.put(Profession.FIREFIGHTER.getValue(), new ArrayList<>());

        reportTemperature();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(LocalDateTime currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    public Season getCurrentSeason() {
        return currentSeason;
    }

    public void setCurrentSeason(Season currentSeason) {
        this.currentSeason = currentSeason;
    }

    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(Double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public List<Family> getFamilies() {
        return families;
    }

    public void addFamily(Family family) {
        this.families.add(family);
    }

    public List<Housing> getHouses() {
        return houses;
    }

    public void addHousing(Housing housing) {
        this.houses.add(housing);
    }

    public List<Person> getPeople() {
        return people;
    }

    public List<Food> getFoodRecipes() {
        return foodRecipes;
    }

    public void addDied(Person person) {
        synchronized (people) {
            people.remove(person);
        }
        died.add(person);
    }

    public List<Person> getDied() {
        return died;
    }

    public void setWorkers() {
        timer = new Timer();
        newBornWorker = new NewBornWorker(this);
        foodFactoryWorker = new FoodFactoryWorker(this);
        timeWorker = new TimeWorker(this);

        timer.scheduleAtFixedRate(newBornWorker, Timing.NEW_BORN_WORKER.getValue(), Timing.NEW_BORN_WORKER.getValue());
        timer.scheduleAtFixedRate(foodFactoryWorker, 0, Timing.FOOD_FACTORY.getValue());
        timer.scheduleAtFixedRate(timeWorker, Timing.HOUR.getValue(), Timing.HOUR.getValue());
    }

    public List<Disaster> getDisaster() {
        return disaster;
    }

    public Map<String, List<Person>> getProfessionals() {
        return professionals;
    }

    public List<Person> getCriminals() {
        return criminals;
    }

    public void startDisaster(Disaster disaster) {
        synchronized (this.disaster) {
            if (this.disaster.isEmpty() && !(disaster instanceof Consequence)) {
                this.disaster.add(disaster);
                this.disaster.get(0).start();

                String event = "A natural disaster '" + disaster.getName() + "' caused by '" + disaster.getCause() + "' started happening in city '" + getName() + "'";
                ConsoleLogger.getLogger().log(event);

                disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, this));
            } else {
                ConsoleLogger.getLogger().log("A disaster is already happening in city '" + getName() + "'");
            }
        }
    }

    public void finishDisaster() {
        String event = "The disaster ("+disaster.get(0).getName()+") is ended in city '"+getName()+"' with "+disaster.get(0).getDiedPeople()+" deaths";
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, this));
        disasterNews.manualPublish();
        disaster.get(0).cancel();
        disaster.remove(0);
    }

    public void continueDisaster(Disaster disaster) {
        if(disaster instanceof Consequence && !this.disaster.isEmpty()) {
            this.disaster.get(0).cancel();
            String event = "A natural disaster ("+this.disaster.get(0).getName()+" with "+this.disaster.get(0).getDiedPeople()+" deaths) is being followed up with another disaster '"+disaster.getName()+"' in city '"+this.getName()+"'";
            ConsoleLogger.getLogger().log(event);

            disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, this));

            this.disaster.remove(0);
            this.disaster.add(disaster);
            this.disaster.get(0).setLocation(this);
            this.disaster.get(0).start();
        }
    }

    @SuppressWarnings("unchecked")
    public void createFamilies() {
        List<Person> orphans = new ArrayList<>();
        people.forEach(person -> {
            List<Family> shuffledFamilies = (List<Family>) Calculation.shuffleList(families);
            shuffledFamilies.forEach(family -> family.tryToAdd(person, false));
            checkIfGotIntoFamily(person, orphans);
        });

        tryToFindFamily(orphans);
    }

    private void checkIfGotIntoFamily(Person person, List<Person> orphans) {
        if(person.getFamily() == null && person.getAge() >= 18) {
            createNewFamily(person);
        }else if(person.getFamily() == null && person.getAge() < 18){
            orphans.add(person);
        }
    }

    private void createNewFamily(Person person) {
        Family family = new Family(this, person);
        family.findHousing();
        families.add(family);
        person.setFamily(family);
        person.setStatInFamily(StatInFamily.PARENT);
    }

    @SuppressWarnings("unchecked")
    private void tryToFindFamily(List<Person> orphans) {
        List<Person> leftToDie = new ArrayList<>();
        orphans.forEach(person -> {
            List<Family> shuffledFamilies = (List<Family>) Calculation.shuffleList(families);
            shuffledFamilies.forEach(family -> family.tryToAdd(person, false));
            if(person.getFamily() == null) {
                leftToDie.add(person);
            }
        });
        removeOrphans(leftToDie);
    }

    private void removeOrphans(List<Person> orphans) {
        synchronized (people) {
            orphans.forEach(orphan -> {
                orphan.getAgingWorker().cancel();
                orphan.getDigestionWorker().cancel();
                orphan.getEatingWorker().cancel();
                orphan.getTimer().cancel();
            });
            people.removeAll(orphans);
        }
    }

    @SuppressWarnings("unchecked")
    public Family getFertileFamily(){
        List<Family> family = new ArrayList<>();
        List<Family> shuffledFamilies = (List<Family>) Calculation.shuffleList(families);
        for (Family f : shuffledFamilies) {
            List<Person> parents = new ArrayList<>();
            List<Person> children = new ArrayList<>();
            f.getPeople().forEach(person -> Calculation.sortFertileParentsFromChildren(person, parents, children));
            if (parents.size() == 2 && children.size() < 3) {
                family.add(f);
                break;
            }
        }
        return (!family.isEmpty()) ? family.get(0) : null;
    }

    public Housing buildHouse() {
        Housing housing;
        switch (Calculation.getRandomIntBetween(1,3)) {
            case 1:
                housing = new BrickBlock();
                break;
            case 2:
                housing = new PanelBlock();
                break;
            default:
                housing = new FamilyHouse();
                break;
        }
        addHousing(housing);
        return housing;
    }

    public void callParamedic(Person person, DeathCause deathCause, String event) {
        synchronized (professionals.get(Profession.PARAMEDIC.getValue())){
            boolean foundParamedic = false;
            for(Person paramedic : professionals.get(Profession.PARAMEDIC.getValue())) {
                if(!paramedic.isBusy() && paramedic != person) {
                    paramedic.tryToRevivePerson(person, deathCause, event);
                    foundParamedic = true;
                    break;
                }
            }
            if(!foundParamedic) {
                person.recordAsDied("There was no available paramedic, thus " + event);
            }
        }
    }

    public void callPolice(Person criminal) {
        synchronized (professionals.get(Profession.POLICE.getValue())){
            boolean foundPolice = false;
            for(Person police : professionals.get(Profession.POLICE.getValue())) {
                if(!police.isBusy()) {
                    police.tryToCatchCriminal(criminal);
                    foundPolice = true;
                    break;
                }
            }
            if(!foundPolice) {
                ActivityLogger.getLogger().log("There was no police available, criminal " + criminal.getFullName() + " escaped the crime scene");
                newsPaperDao.uploadPersonNews(DtoGenerator.setupPersonNewsDto(PersonNewsCategory.ESCAPED_CRIMINAL, criminal, null));
            }
        }
    }

    public void callFireFighter(Housing housing, List<Person> toKill, Disaster disaster) {
        synchronized (professionals.get(Profession.FIREFIGHTER.getValue())){
            for(Person fireFighter : professionals.get(Profession.FIREFIGHTER.getValue())) {
                if(!fireFighter.isBusy()) {
                    fireFighter.tryToSaveHousing(housing, toKill, disaster);
                    break;
                }
            }
        }
    }

    public void addHour() {
        addElapsedDayIfNeeded(currentDateTime);
        currentDateTime = currentDateTime.plusHours(1);
        currentTemperature = Calculation.calculateTemperature(this);
        reportTemperature();
    }

    public void addElapsedDayIfNeeded(LocalDateTime time) {
        if(time.plusHours(1).getHour() == 0) {
            currentSeason.addElapsedDay();
            switchSeasonIfNeeded();
        }
    }

    public void switchSeasonIfNeeded() {
        if(currentSeason.getDaysElapsed() == 7) {
            if(currentSeason instanceof Spring) {
                currentSeason = new Summer();
            }else if(currentSeason instanceof Summer) {
                currentSeason = new Autumn();
            }else if(currentSeason instanceof Autumn) {
                currentSeason = new Winter();
            }else if(currentSeason instanceof Winter) {
                currentSeason = new Spring();
            }
        }
    }

    public void reportTemperature() {
        TemperatureReportDto temperatureReport = new TemperatureReportDto();
        temperatureReport.setCity(name);
        temperatureReport.setDate(currentDateTime);
        temperatureReport.setPartOfDay(Calculation.getPartOfDay(currentDateTime).name());
        temperatureReport.setSeason(currentSeason.getName());
        temperatureReport.setTemperature(currentTemperature);

        temperatureDao.uploadTemperatureReport(temperatureReport);
    }

    @Override
    public synchronized String toString() {
        return "\n City: {" +
                "\n  name: '" + name + "'," +
                "\n  population: " + people.size() + "," +
                "\n  died: " + died.size() + "," +
                "\n  people: {" +
                "\n     kids: {" +
                "\n         girls: " + Calculation.getPeopleCountByType(people, Type.GIRL) +
                "\n         boys: " + Calculation.getPeopleCountByType(people, Type.BOY) +
                "\n     }" +
                "\n     Woman: " + Calculation.getPeopleCountByType(people, Type.WOMAN) +
                "\n     Man: " + Calculation.getPeopleCountByType(people, Type.MAN) +
                "\n  }, " +
                "\n  families: " + families.size() +
                "\n }";
    }
}
