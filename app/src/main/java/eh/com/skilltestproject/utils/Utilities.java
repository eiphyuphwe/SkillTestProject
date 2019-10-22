package eh.com.skilltestproject.utils;

import android.content.Context;
import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import eh.com.skilltestproject.model.City;
import eh.com.skilltestproject.model.Coord;

public class Utilities {

    public List<City> loadDataFromAsset(Context context) {
        List<City> cityList = new ArrayList<>();
        try {
            InputStream is = context.getAssets().open("cities.json");
            JsonReader reader = new JsonReader(new InputStreamReader(is, "UTF-8"));


            cityList = readCityArray(reader);


            reader.close();
        } catch (UnsupportedEncodingException ex) {
        } catch (IOException ex) {
        }
        catch (OutOfMemoryError e)
        {

        }

        return cityList;
    }

    public List<City> readCityArray(JsonReader reader) throws IOException {
        List<City> cityList = new ArrayList<City>();

        reader.beginArray();
        while (reader.hasNext()) {
            cityList.add(readCity(reader));
        }
        reader.endArray();
        return cityList;
    }

    public City readCity(JsonReader reader) throws IOException {
        double _id = 0.0;
        String country = "";
        String name = "";
        Coord coord = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String json_name = reader.nextName();
            if (json_name.equals("_id")) {
                _id = reader.nextDouble();
            } else if (json_name.equals("country")) {
                country = reader.nextString();
            } else if (json_name.equals("name")) {
                name = reader.nextString();
            } else if (json_name.equals("coord")) {
                coord = readCoord(reader);
            } else {
                reader.skipValue();
                ;
            }


        }
        reader.endObject();
        return new City(_id, country, name, coord);
    }

    public Coord readCoord(JsonReader reader) throws IOException {
        double lat = 0.0;
        double lon = 0.0;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("lat")) {
                lat = reader.nextDouble();
            } else if (name.equals("lon")) {
                lon = reader.nextDouble();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Coord(lat, lon);


    }

    public static List<City> filterCities(List<City> orgCityList, final String st) {
        List<City> filteredList = new ArrayList<>();
                   for(City c:orgCityList)
                   {
                        if((c.getName().toLowerCase().startsWith(st.toLowerCase()))
                        || (c.getCountry().toLowerCase().startsWith(st.toLowerCase())))
                        {
                            filteredList.add(c);
                        }
                   }

        return filteredList;

    }



    public static List<City> sortCountryList(List<City> cityList)
    {
        Collections.sort(cityList, new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        return cityList;
    }


}
