package eh.com.skilltestproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import eh.com.skilltestproject.CityInfoActivity;
import eh.com.skilltestproject.MapDetailActivity;
import eh.com.skilltestproject.R;
import eh.com.skilltestproject.fragments.MapFragment;
import eh.com.skilltestproject.main.MainActivity;
import eh.com.skilltestproject.model.City;

public class CityAdapter
        extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private  MainActivity mParentActivity;
    private  List<City> cityList;
    private List<City> mFilteredList;
    private  boolean mTwoPane;

public CityAdapter()
{}

    public CityAdapter(MainActivity parent,
                                  List<City> cityList,
                                  boolean twoPane) {


        this.cityList = cityList;
        this.mFilteredList = cityList;
        this.mParentActivity = parent;
        this.mTwoPane = twoPane;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.txtCountryName.setText(cityList.get(position).getName()+", "+cityList.get(position).getCountry());
        holder.txtLongtitude.setText(cityList.get(position).getCoord().getLat()+"");
        holder.txtLongtitude.setText(cityList.get(position).getCoord().getLon()+"");
        holder.itemView.setTag(cityList.get(position));
        holder.txtMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();
                Intent intent = new Intent(context, CityInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(MapFragment.ARG_POSITION,
                        position+"");
                bundle.putSerializable(MapFragment.ARG_CITY,(Serializable)cityList.get(position));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                City city = cityList.get(position);
                if(mTwoPane)
                {
                    Bundle args = new Bundle();
                    args.putString(MapFragment.ARG_POSITION,position+"");
                    args.putSerializable(MapFragment.ARG_CITY,(Serializable)cityList.get(position));
                    MapFragment fragment = new MapFragment();
                    fragment.setArguments(args);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container,fragment)
                            .commit();


                }
                else {

                    Context context = v.getContext();
                    Intent intent = new Intent(context, MapDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(MapFragment.ARG_POSITION,
                            position+"");
                    bundle.putSerializable(MapFragment.ARG_CITY,(Serializable)cityList.get(position));
                    intent.putExtras(bundle);
                    context.startActivity(intent);



                }
            }
        });
    }

    @Override
    public int getItemCount() {

        return mFilteredList.size();
    }




    class ViewHolder extends RecyclerView.ViewHolder {
         TextView txtCountryName;
         TextView txtLatitude;
         TextView txtLongtitude;
         TextView txtMoreInfo;

        ViewHolder(View view) {
            super(view);
            txtCountryName = (TextView) view.findViewById(R.id.txtCountryName);
            txtLatitude = (TextView) view.findViewById(R.id.txtLatitude);
            txtLongtitude = (TextView) view.findViewById(R.id.txtLongitude);
            txtMoreInfo = (TextView) view.findViewById(R.id.txtMoreInfo);
        }
    }





}
