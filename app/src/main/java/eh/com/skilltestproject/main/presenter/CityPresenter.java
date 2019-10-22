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

    public CityPresenter(CityModelView view) {
        this.mvpView = view;
    }

    public void loadFile()
    {
        new ReadCityJsonFromAssets().execute();
      //  showProgress();
       /* List<City> cityList = new ArrayList<>();
        try {
            Utilities utilities = new Utilities();
            cityList = Utilities.sortCountryList(utilities.loadDataFromAsset((MainActivity) mvpView));
            mvpView.setUpRecyclerView(cityList);
            hideProgress();

        }catch (OutOfMemoryError error)
        {
            mvpView.showOutOfMemoryException("You Memeory is not enough to use our Liquid App!");
        }
        catch (Exception e)
        {
            mvpView.showError("Error:"+e.toString());
        }*/
    }

    public void showProgress()
    {

        mvpView.onShowProgress();
    }
    public void hideProgress()
    {
        mvpView.onHideProgress();
    }

    public class ReadCityJsonFromAssets extends AsyncTask<String,String,String>{

        List<City> cityList = new ArrayList<>();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgress();
        }

        @Override
        protected String doInBackground(String... strings) {

            String s="";
            try {
                Utilities utilities = new Utilities();
                cityList = Utilities.sortCountryList(utilities.loadDataFromAsset((MainActivity) mvpView));


            }catch (OutOfMemoryError error)
            {
                mvpView.showOutOfMemoryException("You Memeory is not enough to use our Liquid App!");
            }
            catch (Exception e)
            {
                mvpView.showError("Error:"+e.toString());
            }
            return s;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mvpView.setUpRecyclerView(cityList);
            hideProgress();
        }
    }


}
