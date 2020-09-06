package wob.city.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wob.city.objects.City;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskControllerTest {
    @Test
    @DisplayName("Generating a city with name 'Wob City' and with a population '10' should add a city object to a list with name field being 'Wob City', the people list with a size of 10, and a population field being '10'")
    public void createCityShouldAddToList() {
        TaskController taskController = new TaskController();
        List<City> cities = new ArrayList<>();

        taskController.createCity("Wob City", 10, cities);

        assertEquals(cities.size(), 1);
        assertEquals(cities.get(0).getName(), "Wob City");
        assertEquals(cities.get(0).getPopulation(), 10);
        assertEquals(cities.get(0).getPeople().size(), 10);
    }
}
