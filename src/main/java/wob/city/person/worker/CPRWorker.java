package wob.city.person.worker;

import wob.city.console.logger.ActivityLogger;
import wob.city.database.dao.NewsPaperDao;
import wob.city.database.enums.PersonNewsCategory;
import wob.city.person.abstraction.Person;
import wob.city.person.enums.DeathCause;
import wob.city.util.Calculation;
import wob.city.util.DtoGenerator;

import java.util.TimerTask;

public class CPRWorker extends TimerTask {
    private final Person paramedic;
    private final Person toSave;
    private final DeathCause deathCause;
    private final String event;
    private final NewsPaperDao newsPaperDao;

    public CPRWorker(Person paramedic, Person toSave, DeathCause deathCause, String event, NewsPaperDao newsPaperDao) {
        this.paramedic = paramedic;
        this.toSave = toSave;
        this.deathCause = deathCause;
        this.event = event;
        this.newsPaperDao = newsPaperDao;
    }

    @Override
    public void run() {
        String diedEvent = "A paramedic ("+ paramedic.getFullName()+") failed to save a ";
        if (Calculation.getRandomIntBetween(0,100) <= 20 && toSave.getAge() < Person.ABSOLUTE_MAX_AGE) {
            if(deathCause == DeathCause.STARVED) {
                toSave.setEnergy(2500);
            }
            ActivityLogger.getLogger().log("A paramedic ("+ paramedic.getFullName() +") successfully saved a person ("+ toSave.getFullName() +")");
            newsPaperDao.uploadPersonNews(DtoGenerator.setupPersonNewsDto(PersonNewsCategory.SAVED_BY_PARAMEDIC, toSave, paramedic));
        }else{
            toSave.recordAsDied(diedEvent+event);
        }
        paramedic.setBusy(false);
    }
}
