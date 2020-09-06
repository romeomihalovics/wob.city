package wob.city.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wob.city.abstractions.Person;
import wob.city.objects.Boy;
import wob.city.objects.Girl;
import wob.city.objects.Man;
import wob.city.objects.Woman;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PeopleGeneratorTest {
    @Test
    @DisplayName("Generating a list of 10 people should return a list of 10 people [could be man, woman, girl, boy]")
    public void generatePeopleShouldReturn() {
        PeopleGenerator peopleGenerator = new PeopleGenerator();
        List<Person> people = peopleGenerator.generatePeople(10);
        List<Type> personTypes = Arrays.asList(Man.class, Woman.class, Girl.class, Boy.class);

        assertEquals(people.size(), 10);
        for(Person person : people) {
            assertTrue(personTypes.contains(person.getClass()));
        }
    }
}
