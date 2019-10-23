package eh.com.skilltestproject.main.presenter;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import eh.com.skilltestproject.main.MainActivity;
import eh.com.skilltestproject.main.main_model.CityModelView;
import eh.com.skilltestproject.model.City;
import eh.com.skilltestproject.utils.Utilities;

public class CityPresenter {

    public CityModelView mvpView;
    List<City> cityList;



    public CityPresenter(CityModelView view) {
        this.mvpView = view;
        cityList = new ArrayList<>();

    }

    public void loadFile()
    {
        new ReadCityJsonFromAssets().execute();

    }

   public void showOutOfMemoryException(String s)
   {
    mvpView.showOutOfMemoryException(s);
   }

   public void showException(String e)
   {
       mvpView.showError(e);
   }

    public class ReadCityJsonFromAssets extends AsyncTask<String,String,String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

          //  mvpView.onShowProgress();

        }

        @Override
        protected String doInBackground(String... strings) {

            String s = "";
            try {
                Utilities utilities = new Utilities();
                cityList = Utilities.sortCountryList(utilities.loadDataFromAsset((MainActivity) mvpView));
                MainActivity.constantCityList = cityList;


            } catch (OutOfMemoryError error) {
                showOutOfMemoryException("You Memeory is not enough to use our Liquid App!");
            } catch (Exception e) {
                showException("Error:" + e.toString());
            }
            return s;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        //    mvpView.onHideProgress();

            mvpView.setUpRecyclerView(cityList);


        }
    }
}






