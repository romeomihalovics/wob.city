package wob.city.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wob.city.abstractions.Food;
import wob.city.objects.City;
import wob.city.objects.Meat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskControllerTest {
    @Test
    @DisplayName("Generating a city with name 'Wob City' and with a population '10' should add a city object to a list with name field being 'Wob City', the people list with a size of 10, and a population field being '10'")
    void createCityShouldAddToList() {
        TaskController taskController = new TaskController();
        List<City> cities = new ArrayList<>();
        List<Food> foods = Collections.singletonList(new Meat(Arrays.asList("Sausage", "14", "1", "28", "meat")));

        taskController.createCity("Wob City", 10, cities, foods);

        assertEquals(1, cities.size());
        assertEquals("Wob City", cities.get(0).getName());
        assertEquals(10, cities.get(0).getPeople().size());
    }
}
