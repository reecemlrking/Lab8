package com.example.lab8;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


//import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CustomListTest {


    private CustomList list;
    /**
     * create a mocklist for my citylist
     * @return
     */
    public CustomList MockCityList(){
        list = new CustomList(null,new ArrayList<>());
        return list;
    }
    /**
     * get the size of the list
     * increase the list by adding a new city
     * check if our current size matches the initial size
     plus one
     */
    @Test
    public void addCityTest(){
        list = MockCityList();
        int listSize = list.getCount();
        list.addCity(new City("Estevan", "SK"));
        assertEquals(list.getCount(),listSize + 1);
    }

    /**
     * Tests if after adding a city to the list, the list has that city and
     * checks if the list has a city that is not in the list.
     */
    @Test
    public void hasCityTest(){
        list = MockCityList();
        City city = new City("Estevan", "SK");
        list.addCity(city);
        assertTrue(list.hasCity(city));
        assertFalse(list.hasCity(new City("Saskatoon", "SK")));
    }

    /**
     * Adds a city to the list, asserts no IllegalArgumentException is thrown when deleting that city
     * as wells as that that city was in fact removed from the list and checks the same exception is
     * thrown when trying to delete a city not present in the list
     */
    @Test
    public void deleteCityTest(){
        list = MockCityList();
        City city = new City("Estevan", "SK");
        list.addCity(city);
        assertAll(() -> list.delete(city));
        assertFalse(list.hasCity(city)); //forgot this test case
        assertThrows(IllegalArgumentException.class, () -> list.delete(new City("Edmonton", "AB")));
    }


    /**
     * adds various cities to the list and checks whether count returns the correct number for
     * what should be there
     */
    @Test
    public void count(){
        list = MockCityList();
        City city1 = new City("Estevan", "SK");
        City city2 = new City("Saskatoon", "SK");

        assertEquals(list.count(), 0);
        list.addCity(city1);
        assertEquals(list.count(), 1);
        list.addCity(city2);
        assertEquals(list.count(), 2);
        list.delete(city1);
        assertEquals(list.count(), 1);
        list.delete(city2);
        assertEquals(list.count(), 0);
    }
}
