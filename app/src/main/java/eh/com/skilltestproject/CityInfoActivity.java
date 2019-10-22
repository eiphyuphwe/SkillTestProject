package eh.com.skilltestproject;

import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import eh.com.skilltestproject.fragments.MapFragment;
import eh.com.skilltestproject.model.City;

public class CityInfoActivity extends AppCompatActivity {

    Toolbar toolbar;
    City city = new City();
    TextView txtName,txtLat,txtLong;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_info);

        setUpToolbar();
        setUI();
        if(savedInstanceState == null)
        {
            Bundle args = new Bundle();
            args.putString(MapFragment.ARG_POSITION,
                    getIntent().getStringExtra(MapFragment.ARG_POSITION));
            city = (City) getIntent().getSerializableExtra(MapFragment.ARG_CITY);
            args.putSerializable(MapFragment.ARG_CITY,(Serializable) city);
            txtName.setText(city.getName()+" ,"+city.getCountry());
            txtLat.setText("Latitude : "+city.getCoord().getLat());
            txtLong.setText("Longitude : "+city.getCoord().getLon());


        }

    }

    public void setUI()
    {
       txtName = findViewById(R.id.txtCountryName);
       txtLat =findViewById(R.id.txtLatitude);
       txtLong = findViewById(R.id.txtLon);

    }


    public void setUpToolbar()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }
}
