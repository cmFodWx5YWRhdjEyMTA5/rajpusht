package Adpter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import extras.BeneficiaryList;
import in.co.rajpusht.rajpusht.R;


/**
 * Created by manav boro on 12/20/2016.
 */

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private Context context;
//    private ItemClickListener clickListener;


    private List<BeneficiaryList> moviesList;
    public DetailsAdapter(List<BeneficiaryList> moviesList) {
//        inflater = LayoutInflater.from(context);
        this.moviesList = moviesList;
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


//getchild==mother_id;
//        getchildname()==mothername;
if(movie.getChild_id()!=null){

    holder.date.setText("DoD"+movie.getDedelivery());
    holder.womenname.setText(movie.getChildname()+ " " +"("+"M/o"+" "+movie.getName()+")");
}
else{
    holder.date.setText("LMP"+movie.getLmp_date());
    holder.womenname.setText(movie.getName());
}

if(movie.getSub_stage().equalsIgnoreCase(movie.getCurrent_sub_stage())){
    holder.buttonststuss.setBackgroundResource(R.color.green);
    holder.buttonststuss.setText(movie.getCurrent_sub_stage());
}
else{
    holder.buttonststuss.setText(movie.getCurrent_sub_stage());
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

        public TextView  womenname, date;
        Button buttonststuss;
        public MyViewHolder(View itemView) {
            super(itemView);

            womenname = (TextView) itemView.findViewById(R.id.womenname);
            date = (TextView) itemView.findViewById(R.id.date);
            buttonststuss = (Button) itemView.findViewById(R.id.buttonststus);
//
            itemView.setTag(itemView);




        }


    }
}
