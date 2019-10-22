package eh.com.skilltestproject;

import android.os.Bundle;

import java.io.Serializable;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import eh.com.skilltestproject.fragments.MapFragment;
import eh.com.skilltestproject.model.City;

public class MapDetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    City city = new City();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpToolbar();

        if(savedInstanceState == null)
        {
            Bundle args = new Bundle();
            args.putString(MapFragment.ARG_POSITION,
                    getIntent().getStringExtra(MapFragment.ARG_POSITION));
            city = (City) getIntent().getSerializableExtra(MapFragment.ARG_CITY);
            args.putSerializable(MapFragment.ARG_CITY,(Serializable) city);

            MapFragment fragment = new MapFragment();
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.map_container,fragment)
                    .commit();
        }

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
