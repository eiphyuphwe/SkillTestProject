package eh.com.skilltestproject.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import eh.com.skilltestproject.R;
import eh.com.skilltestproject.model.City;

public class MapFragment extends Fragment {


    public static final String ARG_POSITION = "item_position";
    public static final String ARG_CITY = "item_city";


    public City city;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments().containsKey(ARG_POSITION))
        {
            if(getArguments().containsKey(ARG_CITY)) {
                city = (City) getArguments().getSerializable(ARG_CITY);
            }
            //city = MainActivity.constantCityList.get(Integer.parseInt(getArguments().getString(ARG_POSITION)));
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_map,container,false);
        if(city!=null)
        {
            SupportMapFragment mapFragment =(SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {

                    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    googleMap.clear();

                    CameraPosition googlePlex = CameraPosition.builder()
                            .target(new LatLng(city.getCoord().getLat(),city.getCoord().getLon()))
                            .zoom(20)
                            .bearing(0)
                            .tilt(45)
                            .build();

                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 1000, null);

                    googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(city.getCoord().getLat(),city.getCoord().getLon()))
                    .title(city.name)
                    .icon(bitmapDescriptorFromVector(getActivity(),R.drawable.icon_map)));
                }
            });
        }
        return rootView;
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
