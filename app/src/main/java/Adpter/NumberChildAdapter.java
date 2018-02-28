package Adpter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import in.co.rajpusht.rajpusht.R;

/**
 * Created by Ranjeet on 2/25/2018.
 */

public class NumberChildAdapter extends RecyclerView.Adapter<NumberChildAdapter.MyViewHolder> {

    private List<String> horizontalList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public Button txtView;

        public MyViewHolder(View view) {
            super(view);
            txtView = (Button) view.findViewById(R.id.viewChildItem);

        }
    }


    public NumberChildAdapter(List<String> horizontalList) {
        this.horizontalList = horizontalList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.addbuttonchild, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Log.d("PositinVAlaue",""+position);
        holder.txtView.setText(String.valueOf(position+1));


    }

    @Override
    public int getItemCount() {
        return horizontalList.size();
    }
}