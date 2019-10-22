package eh.com.skilltestproject;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import eh.com.skilltestproject.model.City;
import eh.com.skilltestproject.model.Coord;
import eh.com.skilltestproject.utils.Utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void checkTruefilterCities() {

        List<City> arraylist = new ArrayList<>();
        arraylist.add(new City(1,"US","Alabama",new Coord(0.0,0.0)));
        arraylist.add(new City(2,"US","Albuquerque",new Coord(0.0,0.0)));
        arraylist.add(new City(3,"US","Anaheim",new Coord(0.0,0.0)));
        arraylist.add(new City(4,"AU","Sydney",new Coord(0.0,0.0)));

      //  CityAdapter adapter = new CityAdapter();

        assertEquals(4,(Utilities.filterCities(arraylist,"a")).size());
    }

    @Test
    public void checkfalsefilterCities() {

        List<City> arraylist = new ArrayList<>();
        arraylist.add(new City(1,"US","Alabama",new Coord(0.0,0.0)));
        arraylist.add(new City(2,"US","Albuquerque",new Coord(0.0,0.0)));
        arraylist.add(new City(3,"US","Anaheim",new Coord(0.0,0.0)));
        arraylist.add(new City(4,"AU","Sydney",new Coord(0.0,0.0)));

        //CityAdapter adapter = new CityAdapter();
       // assertNotEquals(4, (adapter.getDataFilter("al",arraylist)).size());
        assertNotEquals(4,(Utilities.filterCities(arraylist,"al")).size());
    }

    @Test
    public void checkSortingAlphaBeticalOrder() {

        List<City> arraylist = new ArrayList<>();
        arraylist.add(new City(1,"AU","Sydney",new Coord(0.0,0.0)));
        arraylist.add(new City(2,"US","Alabama",new Coord(0.0,0.0)));
        arraylist.add(new City(3,"US","Albuquerque",new Coord(0.0,0.0)));
        arraylist.add(new City(4,"US","Anaheim",new Coord(0.0,0.0)));

        assertTrue((Utilities.sortCountryList(arraylist)).get(3).getName().equals("Sydney"));


    }

    @Test
    public void checkFalseSortingOrder()
    {
        List<City> arraylist = new ArrayList<>();
        arraylist.add(new City(1,"AU","Sydney",new Coord(0.0,0.0)));
        arraylist.add(new City(2,"US","Alabama",new Coord(0.0,0.0)));
        arraylist.add(new City(3,"US","Albuquerque",new Coord(0.0,0.0)));
        arraylist.add(new City(4,"US","Anaheim",new Coord(0.0,0.0)));

        assertFalse((Utilities.sortCountryList(arraylist)).get(0).getName().equals("Sydney"));
    }

    @Test
    public void convertingStringToInputStream_thenCorrect()
            throws IOException {
        String initialString = "text";
        InputStream targetStream = new ByteArrayInputStream(initialString.getBytes());
    }




}