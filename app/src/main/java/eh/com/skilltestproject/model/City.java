package eh.com.skilltestproject.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class City implements Serializable {

   @SerializedName("_id")
public double _id;
   @SerializedName("country")
public String country;
   @SerializedName("name")
public String name;
   @SerializedName("coord")
public Coord coord;

public City()
{

}

    public City(double _id, String country, String name, Coord coord) {
        this._id = _id;
        this.country = country;
        this.name = name;
        this.coord = coord;
    }

    public double get_id() {
        return _id;
    }

    public void set_id(double _id) {
        this._id = _id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    @Override
    public String toString() {
        return "City{" +
                "_id=" + _id +
                ", country='" + country + '\'' +
                ", name='" + name + '\'' +
                ", coord=" + coord +
                '}';
    }
}
