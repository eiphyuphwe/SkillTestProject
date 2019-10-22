package eh.com.skilltestproject.main.main_model;

import java.util.List;

import eh.com.skilltestproject.model.City;

public interface CityModelView {

    void onShowProgress();
    void onHideProgress();
    void setUpRecyclerView(List<City> cityList);
    void showOutOfMemoryException(String e);
    void showError(String e);
}
