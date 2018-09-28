package in.co.rajpusht.rajpusht.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import in.co.rajpusht.rajpusht.R;
import in.co.rajpusht.rajpusht.activity.CashBeneficiaryActivity;
import in.co.rajpusht.rajpusht.activity.CashProfileActivity;
import in.co.rajpusht.rajpusht.model.AWCModel;

/**
 * Created by LENOVO on 11-Sep-18.
 */

public class CashProfileAdapter extends RecyclerView.Adapter<CashProfileAdapter.MyViewHolder> {
   private Context mContext;
   private List<AWCModel> awcModelList;


    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewLocation, textViewSector;
        public RelativeLayout cashProfileSelect;

        public MyViewHolder(View itemView) {
            super(itemView);
            textViewSector = (TextView)itemView.findViewById(R.id.textviewSector);
            textViewLocation = (TextView)itemView.findViewById(R.id.textviewLocation);
            cashProfileSelect = (RelativeLayout)itemView.findViewById(R.id.cashProfileSelectLayout);
            cashProfileSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String sector = textViewSector.getText().toString();
                    String location = textViewLocation.getText().toString();



                    Intent intent = new Intent(mContext, CashBeneficiaryActivity.class);
                    intent.putExtra("sector", sector);
                    intent.putExtra("location", location);
                    mContext.startActivity(intent);
                }
            });
        }
    }
    public  CashProfileAdapter (Context context , List<AWCModel> awcModelList) {
        this.mContext = context;
        this.awcModelList = awcModelList;
    }

    @Override
    public CashProfileAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cash_profile_items,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CashProfileAdapter.MyViewHolder holder, int position) {
        AWCModel awcModel = awcModelList.get(position);
        holder.textViewSector.setText(awcModel.getSectorName()+", "+ awcModel.getDistName());
        holder.textViewLocation.setText(awcModel.getAwcName());
    }

    @Override
    public int getItemCount() {
        return awcModelList.size();
    }


}
