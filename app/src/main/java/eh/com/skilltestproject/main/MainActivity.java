package eh.com.skilltestproject.main;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.FrameLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import eh.com.skilltestproject.R;
import eh.com.skilltestproject.adapters.CityAdapter;
import eh.com.skilltestproject.main.main_model.CityModelView;
import eh.com.skilltestproject.main.presenter.CityPresenter;
import eh.com.skilltestproject.model.City;
import eh.com.skilltestproject.utils.Utilities;

public class MainActivity extends AppCompatActivity implements CityModelView {

    private boolean mTwoPane;
    Toolbar toolbar;
    List<City> cityList;
    View recyclerView;
    public static List<City> constantCityList ;
    SearchView searchView;
    CityAdapter mAdapter;
    CityPresenter presenter;

    RecyclerView.LayoutManager layoutManager;
    boolean rotation = true;
    ProgressDialog progressDialog;
    FrameLayout mapLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityList = new ArrayList<>();
        constantCityList = new ArrayList<>();
        setUpTooolbar();
        setUI();
        checkTowPane();
        mAdapter = new CityAdapter(this,cityList,mTwoPane);
        presenter = new CityPresenter(this);

        presenter.loadFile();

        search();


    }


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        rotation = false;

    }

    public void loadCityDataFromAssetsFolder()
    {

        Utilities utilities = new Utilities();
        cityList = Utilities.sortCountryList(utilities.loadDataFromAsset(MainActivity.this));
        constantCityList = cityList;
        setupRecyclerView((RecyclerView) recyclerView);


    }



    public void setupRecyclerView(RecyclerView recyclerView)
    {
        mAdapter = new CityAdapter(this,cityList,mTwoPane);
        recyclerView.setAdapter(mAdapter);

    }



    public void setUI()
    {
        recyclerView =findViewById(R.id.item_list);
        assert recyclerView !=null;

        searchView = findViewById(R.id.search);
        layoutManager = new LinearLayoutManager(this);
        ((RecyclerView)recyclerView).setLayoutManager(layoutManager);
        ((RecyclerView)recyclerView).setItemAnimator(new DefaultItemAnimator());
        ((RecyclerView)recyclerView).addItemDecoration(new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL));

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    private void setUpTooolbar()
    {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Country Lists");

    }

    public void checkTowPane()
    {
        if(findViewById(R.id.item_detail_container) !=null)
        {
            mTwoPane = true;
        }
    }



    @Override
   public void showError(String s)
   {
       AlertDialog.Builder builder;
       builder = new AlertDialog.Builder(this);
       builder.setTitle("Error")
               .setMessage(s)
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       dialog.cancel();
                       finish();
                   }
               });
   }

    @Override
    public void onShowProgress() {

        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }

    @Override
    public void onHideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void setUpRecyclerView(List<City> cityList) {

        this.cityList = cityList;
        constantCityList = cityList;
        mAdapter = new CityAdapter(this,cityList,mTwoPane);
        ((RecyclerView)recyclerView).setAdapter(mAdapter);
    }

    @Override
    public void showOutOfMemoryException(String e) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Error")
                .setMessage(e)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        finish();
                    }
                });
    }

    public void search()
    {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.isEmpty()){
                    cityList = constantCityList;

                }
                else {


                    // cityList = mAdapter.getDataFilter(newText,constantCityList);
                    cityList = Utilities.filterCities(constantCityList,query);

                }
                mAdapter = new CityAdapter(MainActivity.this, cityList, mTwoPane);
                ((RecyclerView) recyclerView).setAdapter(mAdapter);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                List<City> testList = new ArrayList<>();


                if(newText.isEmpty()){
                    cityList = constantCityList;

                }
                else {


                   // cityList = mAdapter.getDataFilter(newText,constantCityList);
                    cityList = Utilities.filterCities(constantCityList,newText);

                }
                mAdapter = new CityAdapter(MainActivity.this, cityList, mTwoPane);
                ((RecyclerView) recyclerView).setAdapter(mAdapter);
                return true;
            }
        });
    }



    public class ReadCityJsonFromAssets extends AsyncTask<String,String,String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings) {

            String s="";
            try {
                Utilities utilities = new Utilities();
                cityList = Utilities.sortCountryList(utilities.loadDataFromAsset(MainActivity.this));
                constantCityList = cityList;


            }catch (OutOfMemoryError error)
            {
             showOutOfMemoryException("You Memeory is not enough to use our Liquid App!");
            }
            catch (Exception e)
            {
               showError("Error:"+e.toString());
            }
            return s;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            setupRecyclerView((RecyclerView) recyclerView);




        }


    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putSerializable("data", (Serializable) cityList);
    }



}
