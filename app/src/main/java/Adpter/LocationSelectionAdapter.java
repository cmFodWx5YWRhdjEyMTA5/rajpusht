package Adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import extras.ProfileGetSetMethod;
import extras.SessionManager;
import in.co.rajpusht.rajpusht.Login;
import in.co.rajpusht.rajpusht.Profile;
import in.co.rajpusht.rajpusht.R;

/**
 * Created by Ranjeet on 3/5/2018.
 */

public class LocationSelectionAdapter extends RecyclerView.Adapter<LocationSelectionAdapter.MyViewHolder>
implements Filterable{

    private List<ProfileGetSetMethod> horizontalList;
    private List<ProfileGetSetMethod> horizontalListFiltered;
    Context context;
    SessionManager session;
    String status = "0";

    private int selectedPosition = -1;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textviewLocation, textviewSector;
        RadioButton radiobutton;

        public MyViewHolder(View view) {
            super(view);
            textviewLocation = (TextView) view.findViewById(R.id.textviewLocation);
            textviewSector = (TextView) view.findViewById(R.id.textviewSector);
            radiobutton = (RadioButton) view.findViewById(R.id.radiobutton);

        }
    }


    public LocationSelectionAdapter(Context context ,List<ProfileGetSetMethod> horizontalList) {
        this.context = context;
        this.horizontalList = horizontalList;
        this.horizontalListFiltered = horizontalList;
//        session = new SessionManager(context);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.locationlayout, parent, false);


        LocationSelectionAdapter.MyViewHolder holder = new LocationSelectionAdapter.MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Log.d("PositinVAlaue", "" + position);
//        holder.txtView.setText(String.valueOf(position + 1));

       // final ProfileGetSetMethod profile = horizontalList.get(position);
        final ProfileGetSetMethod profile = horizontalListFiltered.get(position);


        holder.radiobutton.setChecked(position == selectedPosition);
        try {
            if (status.equalsIgnoreCase("0") && profile.getLoginchecked().equalsIgnoreCase("y")) {
                holder.radiobutton.setChecked(true);
                status = "1";
            }
        } catch (Exception e) {

        }

        holder.radiobutton.setTag(position);
        holder.textviewLocation.setText(profile.getVillage_name());
        holder.textviewSector.setText(profile.getSectorName() + " , " + profile.getProjectName());

        holder.radiobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.radiobutton.setChecked(false);
//        if(profile.getLoginchecked().equalsIgnoreCase("y")) {
//            holder.radiobutton.setChecked(false);
//        }
                selectedPosition = (Integer) v.getTag();
//        session.setLocationId(profile.getId());
                new Login().selectedId = profile.getId();
//        session.setVillageSelectedId(profile.getId());
                notifyDataSetChanged();
                Log.d("SelectedPosition", "" + position + profile.getVillage_name());
            }
        });


    }


    @Override
    public int getItemCount() {
        //return horizontalList.size();
        return  horizontalListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    horizontalListFiltered = horizontalList;
                } else {
                    List<ProfileGetSetMethod> filteredList = new ArrayList<>();
                    for (ProfileGetSetMethod row : horizontalList) {

                        // name match condition. this might differ depending on your requirement
                        if (row.getVillage_name().toLowerCase().contains(charString.toLowerCase()) || row.getSurveyor_name().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    horizontalListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = horizontalListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                horizontalListFiltered = (ArrayList<ProfileGetSetMethod>) filterResults.values;
                notifyDataSetChanged();
            }
        };


    }
}



