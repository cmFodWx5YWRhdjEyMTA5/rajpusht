package Adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import extras.ProfileGetSetMethod;
import extras.SessionManager;
import in.co.rajpusht.rajpusht.Login;
import in.co.rajpusht.rajpusht.R;

/**
 * Created by Ranjeet on 3/5/2018.
 */

public class LocationSelectionAdapter extends RecyclerView.Adapter<LocationSelectionAdapter.MyViewHolder> {

    private List<ProfileGetSetMethod> horizontalList;
    Context context;
    SessionManager session ;
    String status="0";

    private int   selectedPosition=-1;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textviewLocation;
        RadioButton radiobutton;

        public MyViewHolder(View view) {
            super(view);
      textviewLocation = (TextView) view.findViewById(R.id.textviewLocation);
            radiobutton = (RadioButton) view.findViewById(R.id.radiobutton);

        }
    }




    public LocationSelectionAdapter(List<ProfileGetSetMethod> horizontalList) {
        this.horizontalList = horizontalList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.locationlayout, parent, false);


                LocationSelectionAdapter.MyViewHolder holder =     new LocationSelectionAdapter.MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Log.d("PositinVAlaue", "" + position);
//        holder.txtView.setText(String.valueOf(position + 1));

        final ProfileGetSetMethod profile = horizontalList.get(position);

        holder.radiobutton.setChecked(position == selectedPosition);
       if(status.equalsIgnoreCase("0") && profile.getLoginchecked().equalsIgnoreCase("y")) {
           holder.radiobutton.setChecked(true);
status="1";
       }

        holder.radiobutton.setTag(position);
holder.textviewLocation.setText(profile.getVillage_name());

holder.radiobutton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

      holder.radiobutton.setChecked(false);
//        if(profile.getLoginchecked().equalsIgnoreCase("y")) {
//            holder.radiobutton.setChecked(false);
//        }
            selectedPosition = (Integer) v.getTag();
//        session.setLocationId(profile.getId());
        new Login().selectedId=profile.getId();

        notifyDataSetChanged();
Log.d("SelectedPosition",""+position+ profile.getVillage_name());
    }
});
//        holder.radiobutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


//                if(holder.radiobutton.isChecked())
//                {
// holder.radiobutton.setChecked(false);
//// value=1;
//                }
//                else{
//                    holder.radiobutton.setChecked(true);
//                }



//            }
//        });



    }

    @Override
    public int getItemCount() {
        return horizontalList.size();
    }

}
