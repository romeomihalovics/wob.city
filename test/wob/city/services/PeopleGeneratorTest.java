package wob.city.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wob.city.controller.services.PeopleGenerator;
import wob.city.person.abstraction.Person;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PeopleGeneratorTest {
    @Test
    @DisplayName("Generating a list of 10 people should return a list of 10 people [could be man, woman, girl, boy]")
    void generatePeopleShouldReturn() {
        PeopleGenerator peopleGenerator = new PeopleGenerator();
        List<Person> people = peopleGenerator.generatePeople(10);
        List<String> personTypes = Arrays.asList("Man", "Woman", "Girl", "Boy");

        assertEquals(10, people.size());
        for(Person person : people) {
            assertTrue(personTypes.contains(person.getType()));
        }
    }
}
