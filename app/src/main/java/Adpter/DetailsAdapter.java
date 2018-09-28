package Adpter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Set;

import extras.BeneficiaryList;
import in.co.rajpusht.rajpusht.Helper.ColorHelper;
import in.co.rajpusht.rajpusht.R;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by manav boro on 12/20/2016.
 */

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private Context context;
//    private ItemClickListener clickListener;


    private List<BeneficiaryList> moviesList;
    public DetailsAdapter(List<BeneficiaryList> moviesList, Context context) {
//        inflater = LayoutInflater.from(context);
        this.moviesList = moviesList;
        this.context = context;

    }

    @Override
    public DetailsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.from(parent.getContext()).inflate(R.layout.beneficiarylistlayout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(DetailsAdapter.MyViewHolder holder, int position) {



if(position%2==0) {
    holder.itemView.setBackgroundColor(Color.WHITE);
}
        else{
    holder.itemView.setBackgroundColor(Color.LTGRAY);
        }

        BeneficiaryList movie=moviesList.get(position);

        holder.husbandName.setText("(w/o) " + movie.getHusband());
        holder.pctsId.setText("Mamta Card: "+movie.getPctsid());

//getchild==mother_id;
//        getchildname()==mothername;
if(movie.getChild_id()!=null){

    holder.date.setText("Delivery Date : "+movie.getDedelivery());
    holder.womenname.setText(movie.getChildname()+ " " +"("+"M/o"+" "+movie.getName()+")");
}
else{
    holder.date.setText("LMP : "+movie.getLmp_date());
    holder.womenname.setText(movie.getName());
}

Log.d("getstage_name", movie.getName());
Log.d("getstage_sub", movie.getSub_stage());                //changes after saving
Log.d("getstage_current", movie.getCurrent_sub_stage());    //remains same
Log.d("getstage_familyId", movie.getFamily_id());

if(movie.getSub_stage().equalsIgnoreCase(movie.getCurrent_sub_stage())){

    SharedPreferences arrayFamilyId = context.getSharedPreferences("Prefs", MODE_PRIVATE);
    Set<String> set = arrayFamilyId.getStringSet("form_filled", null);
    ColorHelper.getList().addAll(set);
        for(String isApprove : ColorHelper.getList())
        {
            if(isApprove.equalsIgnoreCase(movie.getFamily_id()))
            {
                holder.buttonststuss.setBackgroundResource(R.color.green);  // v1.6- R.color.green
            }
        }

    holder.buttonststuss.setText(movie.getCurrent_sub_stage());
}
else{

    holder.buttonststuss.setText(movie.getCurrent_sub_stage());
}
try {
    if (movie.getIs_anc().equalsIgnoreCase("n")) {
        holder.imageFlags.setBackgroundResource(R.drawable.flag);
        holder.buttonststuss.setBackgroundResource(R.color.yellow);
    }
}catch (Exception e){
    Log.d("IS_anc",e.toString());
}


//        holder.dt.setText(movie.getBook_date());

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


//    @Override
//    public int getItemCount() {
//        return moviesList.size();
//    }
//
//    public void setClickListener(ItemClickListener itemClickListener) {
//        this.clickListener = itemClickListener;
//    }
//
//    public void setClickListener(BookedJob bookedJob) {
//
//
//        Toast.makeText(bookedJob, "Ranjeet checking single value ", Toast.LENGTH_SHORT).show();
//    }

    public class MyViewHolder extends RecyclerView.ViewHolder  {

        public TextView  womenname, date, husbandName, pctsId;
        Button buttonststuss;
        ImageView imageFlags;
        public MyViewHolder(View itemView) {
            super(itemView);

            womenname = (TextView) itemView.findViewById(R.id.womenname);
            date = (TextView) itemView.findViewById(R.id.date);
            buttonststuss = (Button) itemView.findViewById(R.id.buttonststus);
            imageFlags = (ImageView) itemView.findViewById(R.id.imageFlag);
            husbandName = (TextView)itemView.findViewById(R.id.husbanName);
            pctsId = (TextView)itemView.findViewById(R.id.pctsId);
//
            itemView.setTag(itemView);




        }


    }
}
