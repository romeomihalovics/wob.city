package wob.city.database;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import wob.city.database.dao.DisasterHistoryDao;
import wob.city.database.dao.NewsPaperDao;
import wob.city.database.dao.PersonHistoryDao;
import wob.city.database.dto.ConsumptionNewsDto;
import wob.city.database.dto.DisasterHistoryDto;
import wob.city.database.dto.PersonHistoryDto;
import wob.city.database.dto.PersonNewsDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DatabaseTest {
    @Test
    @DisplayName("Test Database connection")
    void testDatabaseConnection() {
        try {
            Connection connection = Database.getConnection();
            assertTrue(connection.isValid(0));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Truncate 'disaster_history' table or at least,
     * if you have records with city name 'DBTest City',
     * remove them before testing
     * **/
    @Test
    @DisplayName("Test uploading, fetching, updating a disaster history")
    void uploadingAndFetchingDisasterHistoryShouldDo() {
        DisasterHistoryDao disasterHistoryDao = new DisasterHistoryDao();

        DisasterHistoryDto disasterHistoryDto = new DisasterHistoryDto();
        disasterHistoryDto.setEvent("Test disaster event");
        disasterHistoryDto.setCityName("DBTest City");
        disasterHistoryDto.setDiedPeople(5);
        disasterHistoryDto.setDiedFamilies(1);
        disasterHistoryDto.setDestroyedBuildings(1);
        disasterHistoryDto.setType("test");

        disasterHistoryDao.uploadDisasterHistory(disasterHistoryDto);
        disasterHistoryDao.setDisasterHistoryToReported("DBTest City");

        List<DisasterHistoryDto> disasterHistoryFetched = disasterHistoryDao.fetchDisasterHistory("DBTest City");
        List<DisasterHistoryDto> disasterHistoryFetched1 = disasterHistoryDao.fetchDisasterHistory("DBTest City", "test");
        List<DisasterHistoryDto> disasterHistoryFetched2 = disasterHistoryDao.fetchDisasterHistory("DBTest City", true);

        assertTrue(disasterHistoryFetched.size() == 1 && disasterHistoryFetched1.size() == 1 && disasterHistoryFetched2.size() == 1);
        assertEquals("Test disaster event", disasterHistoryFetched.get(0).getEvent());
        assertEquals("DBTest City", disasterHistoryFetched.get(0).getCityName());
        assertEquals(5, disasterHistoryFetched.get(0).getDiedPeople());
        assertEquals(1, disasterHistoryFetched.get(0).getDiedFamilies());
        assertEquals(1, disasterHistoryFetched.get(0).getDestroyedBuildings());
        assertEquals("test", disasterHistoryFetched.get(0).getType());
    }

    /**
     * Truncate 'person_history' table or at least,
     * if you have records with city name 'DBTest City',
     * remove them before testing
     * **/
    @Test
    @DisplayName("Test uploading, fetching person history")
    void uploadingAndFetchingPersonHistoryShouldDo() {
        PersonHistoryDao personHistoryDao = new PersonHistoryDao();

        PersonHistoryDto personHistoryDto = new PersonHistoryDto();
        personHistoryDto.setCityName("DBTest City");
        personHistoryDto.setFullName("Test Person");
        personHistoryDto.setEvent("Test Event");

        personHistoryDao.uploadPersonHistory(personHistoryDto);

        List<PersonHistoryDto> personHistoryFetched = personHistoryDao.fetchPersonHistory("DBTest City");
        List<PersonHistoryDto> personHistoryFetched1 = personHistoryDao.fetchPersonHistory("DBTest City", "Test Person");

        assertTrue(personHistoryFetched.size() == 1 && personHistoryFetched1.size() == 1);
        assertEquals("DBTest City", personHistoryFetched.get(0).getCityName());
        assertEquals("Test Person", personHistoryFetched.get(0).getFullName());
        assertEquals("Test Event", personHistoryFetched.get(0).getEvent());
    }

    /**
     * Truncate 'consumption_news' table or at least,
     * if you have records with city name 'DBTest City',
     * remove them before testing
     * **/
    @Test
    @DisplayName("Test uploading, fetching, updating consumption news")
    void uploadingAndFetchingConsumptionNewsShouldDo(){
        NewsPaperDao newsPaperDao = new NewsPaperDao();

        ConsumptionNewsDto consumptionNewsDto = new ConsumptionNewsDto();
        consumptionNewsDto.setAmount(1.0);
        consumptionNewsDto.setCity("DBTest City");
        consumptionNewsDto.setType("Meat");

        newsPaperDao.uploadConsumptionNews(consumptionNewsDto);

        List<ConsumptionNewsDto> consumptionNewsFetched = newsPaperDao.fetchConsumptionNews("DBTest City");
        List<ConsumptionNewsDto> consumptionNewsFetched1 = newsPaperDao.fetchConsumptionNews("DBTest City", false);

        assertTrue(consumptionNewsFetched.size() == 1 && consumptionNewsFetched1.size() == 1);
        assertEquals(1.0, consumptionNewsFetched.get(0).getAmount());
        assertEquals("DBTest City", consumptionNewsFetched.get(0).getCity());
        assertEquals("Meat", consumptionNewsFetched.get(0).getType());
        assertEquals(false, consumptionNewsFetched.get(0).getReported());

        newsPaperDao.setConsumptionNewsToReported("DBTest City");
        consumptionNewsFetched = newsPaperDao.fetchConsumptionNews("DBTest City");
        assertEquals(true, consumptionNewsFetched.get(0).getReported());
    }

    /**
     * Truncate 'death_news' & 'new_born_news' table or at least,
     * if you have records with city name 'DBTest City',
     * remove them before testing
     * **/
    @Test
    @DisplayName("Test uploading, fetching, updating person news (death news, new born news)")
    void uploadingAndFetchingPersonNewsShouldDo() {
        NewsPaperDao newsPaperDao = new NewsPaperDao();

        PersonNewsDto personNewsDto = new PersonNewsDto();
        personNewsDto.setCity("DBTest City");
        personNewsDto.setAge(1);
        personNewsDto.setFullname("Test Person");
        personNewsDto.setEnergy("2500kcal");
        personNewsDto.setHeight(30);
        personNewsDto.setWeight(10);
        personNewsDto.setType("Boy");
        personNewsDto.setLastFood("Test Food");

        newsPaperDao.uploadPersonNews("death", personNewsDto);
        newsPaperDao.uploadPersonNews("new_born", personNewsDto);

        List<PersonNewsDto> personDeathNewsFetched = newsPaperDao.fetchPersonNews("death", "DBTest City");
        List<PersonNewsDto> personDeathNewsFetched1 = newsPaperDao.fetchPersonNews("death", "DBTest City", false);
        List<PersonNewsDto> personNewBornNewsFetched = newsPaperDao.fetchPersonNews("new_born", "DBTest City");
        List<PersonNewsDto> personNewBornNewsFetched1 = newsPaperDao.fetchPersonNews("new_born", "DBTest City", false);

        assertTrue(personDeathNewsFetched.size() == 1 && personDeathNewsFetched1.size() == 1);
        assertTrue(personNewBornNewsFetched.size() == 1 && personNewBornNewsFetched1.size() == 1);

        assertEquals("DBTest City", personDeathNewsFetched.get(0).getCity());
        assertEquals(1, personDeathNewsFetched.get(0).getAge());
        assertEquals("Test Person", personDeathNewsFetched.get(0).getFullname());
        assertEquals("2500kcal", personDeathNewsFetched.get(0).getEnergy());
        assertEquals(30, personDeathNewsFetched.get(0).getHeight());
        assertEquals(10, personDeathNewsFetched.get(0).getWeight());
        assertEquals("Boy", personDeathNewsFetched.get(0).getType());
        assertEquals("Test Food", personDeathNewsFetched.get(0).getLastFood());

        newsPaperDao.setPersonNewsToReported("death", "DBTest City");
        personDeathNewsFetched = newsPaperDao.fetchPersonNews("death", "DBTest City");
        assertEquals(true, personDeathNewsFetched.get(0).getReported());

        newsPaperDao.setPersonNewsToReported("new_born", "DBTest City");
        personNewBornNewsFetched = newsPaperDao.fetchPersonNews("new_born", "DBTest City");
        assertEquals(true, personNewBornNewsFetched.get(0).getReported());
    }
}