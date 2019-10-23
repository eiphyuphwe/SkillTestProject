package eh.com.skilltestproject.model;

import java.util.List;

public class SingletonClass {

    private static volatile SingletonClass sSoleInstance = new SingletonClass();

    public List<City> cityList;
    //private constructor.
    private SingletonClass(){}

    public static SingletonClass getInstance() {
        return sSoleInstance;
    }

    public static SingletonClass getsSoleInstance() {
        return sSoleInstance;
    }

    public static void setsSoleInstance(SingletonClass sSoleInstance) {
        SingletonClass.sSoleInstance = sSoleInstance;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}
