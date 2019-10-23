package eh.com.skilltestproject.main;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.FrameLayout;

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
import eh.com.skilltestproject.model.SingletonClass;
import eh.com.skilltestproject.utils.Utilities;

public class MainActivity extends AppCompatActivity implements CityModelView {

    private boolean mTwoPane;
    Toolbar toolbar;
    List<City> cityList;
    View recyclerView;
   // public static List<City> constantCityList ;
    SearchView searchView;
    CityAdapter mAdapter;
    CityPresenter presenter;

    RecyclerView.LayoutManager layoutManager;
    boolean rotation = true;

   Dialog progressDialog;
    FrameLayout mapLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityList = new ArrayList<>();
        //constantCityList = new ArrayList<>();
        setUpTooolbar();
        setUI();
        checkTowPane();
        mAdapter = new CityAdapter(this,cityList,mTwoPane);
        presenter = new CityPresenter(this);

        if(SingletonClass.getInstance().getCityList()!=null)
        {
            cityList = SingletonClass.getInstance().getCityList();
            setupRecyclerView((RecyclerView)recyclerView);
        }
        else {
            presenter.loadFile();
        }

        search();


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

        progressDialog = new Dialog(MainActivity.this);
        progressDialog.setContentView(R.layout.loading_dialog);
        progressDialog.setCancelable(false);
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
        if(progressDialog!=null) {
            progressDialog.show();
        }
    }

    @Override
    public void onHideProgress() {

        if(progressDialog!=null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void setUpRecyclerView(List<City> cityList) {

        this.cityList = cityList;
        //constantCityList = cityList;
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
                    cityList = SingletonClass.getInstance().getCityList();

                }
                else {


                    // cityList = mAdapter.getDataFilter(newText,constantCityList);
                    cityList = Utilities.filterCities(SingletonClass.getInstance().getCityList(),query);

                }
                mAdapter = new CityAdapter(MainActivity.this, cityList, mTwoPane);
                ((RecyclerView) recyclerView).setAdapter(mAdapter);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {




                if(newText.isEmpty()){
                    cityList = SingletonClass.getInstance().getCityList();

                }
                else {


                   // cityList = mAdapter.getDataFilter(newText,constantCityList);
                    cityList = Utilities.filterCities(SingletonClass.getInstance().getCityList(),newText);

                }
                mAdapter = new CityAdapter(MainActivity.this, cityList, mTwoPane);
                ((RecyclerView) recyclerView).setAdapter(mAdapter);
                return true;
            }
        });
    }





    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        progressDialog.dismiss();
    }
}
