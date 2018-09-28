package in.co.rajpusht.rajpusht.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.co.rajpusht.rajpusht.R;
import in.co.rajpusht.rajpusht.model.CashTransferModel;

/**
 * Created by LENOVO on 18-Sep-18.
 */

public class CashTransferAdapter  extends RecyclerView.Adapter<CashTransferAdapter.MyViewHolder>
implements Filterable{
    private List<CashTransferModel> cashTransferModelList;
    private List<CashTransferModel> cashTransferModelListFiltered;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        ImageView received, submitted, due, eligible, exit;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.textViewName);
            received = (ImageView) itemView.findViewById(R.id.imageReceived);
            submitted = (ImageView) itemView.findViewById(R.id.imageSubmitted);
            due = (ImageView) itemView.findViewById(R.id.imageDuePayment);
            eligible = (ImageView) itemView.findViewById(R.id.imageEligible);
            exit = (ImageView) itemView.findViewById(R.id.imageExit);
        }
    }

    public CashTransferAdapter(List<CashTransferModel> cashTransferModelList){
        this.cashTransferModelList = cashTransferModelList;
        this.cashTransferModelListFiltered = cashTransferModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cash_transfer_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CashTransferAdapter.MyViewHolder holder, int position) {
        CashTransferModel cashTransferModel = cashTransferModelListFiltered.get(position);
        holder.name.setText(cashTransferModel.getName());

        switch(cashTransferModel.getReceived())
        {
            case "0":
                holder.received.setImageResource(R.drawable.ic_blank);
                break;
            case "1":
                holder.received.setImageResource(R.drawable.ic_installment_received);
                break;
            case "2":
                holder.received.setImageResource(R.drawable.ic_form_submitted);
                break;
            case "3":
                holder.received.setImageResource(R.drawable.ic_due_payment);
                break;
            case "4":
                holder.received.setImageResource(R.drawable.ic_eligible_new);
                break;
            case "5":
                holder.received.setImageResource(R.drawable.ic_exit);
                break;
            case "6":
                holder.received.setImageResource(R.drawable.ic_rejected);
                break;
        }

        switch(cashTransferModel.getSubmitted())
        {
            case "0":
                holder.submitted.setImageResource(R.drawable.ic_blank);
                break;
            case "1":
                holder.submitted.setImageResource(R.drawable.ic_installment_received);
                break;
            case "2":
                holder.submitted.setImageResource(R.drawable.ic_form_submitted);
                break;
            case "3":
                holder.submitted.setImageResource(R.drawable.ic_due_payment);
                break;
            case "4":
                holder.submitted.setImageResource(R.drawable.ic_eligible_new);
                break;
            case "5":
                holder.submitted.setImageResource(R.drawable.ic_exit);
                break;
            case "6":
                holder.submitted.setImageResource(R.drawable.ic_rejected);
                break;
        }

        switch(cashTransferModel.getDue())
        {
            case "0":
                holder.due.setImageResource(R.drawable.ic_blank);
                break;
            case "1":
                holder.due.setImageResource(R.drawable.ic_installment_received);
                break;
            case "2":
                holder.due.setImageResource(R.drawable.ic_form_submitted);
                break;
            case "3":
                holder.due.setImageResource(R.drawable.ic_due_payment);
                break;
            case "4":
                holder.due.setImageResource(R.drawable.ic_eligible_new);
                break;
            case "5":
                holder.due.setImageResource(R.drawable.ic_exit);
                break;
            case "6":
                holder.due.setImageResource(R.drawable.ic_rejected);
                break;
        }

        switch(cashTransferModel.getEligible())
        {
            case "0":
                holder.eligible.setImageResource(R.drawable.ic_blank);
                break;
            case "1":
                holder.eligible.setImageResource(R.drawable.ic_installment_received);
                break;
            case "2":
                holder.eligible.setImageResource(R.drawable.ic_form_submitted);
                break;
            case "3":
                holder.eligible.setImageResource(R.drawable.ic_due_payment);
                break;
            case "4":
                holder.eligible.setImageResource(R.drawable.ic_eligible_new);
                break;
            case "5":
                holder.eligible.setImageResource(R.drawable.ic_exit);
                break;
            case "6":
                holder.eligible.setImageResource(R.drawable.ic_rejected);
                break;
        }

        switch(cashTransferModel.getExit())
        {
            case "0":
                holder.exit.setImageResource(R.drawable.ic_blank);
                break;
            case "1":
                holder.exit.setImageResource(R.drawable.ic_installment_received);
                break;
            case "2":
                holder.exit.setImageResource(R.drawable.ic_form_submitted);
                break;
            case "3":
                holder.exit.setImageResource(R.drawable.ic_due_payment);
                break;
            case "4":
                holder.exit.setImageResource(R.drawable.ic_eligible_new);
                break;
            case "5":
                holder.exit.setImageResource(R.drawable.ic_exit);
                break;
            case "6":
                holder.exit.setImageResource(R.drawable.ic_rejected);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return cashTransferModelListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return  new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString  = charSequence.toString();
                if(charString.isEmpty()) {
                    cashTransferModelListFiltered = cashTransferModelList;
                } else
                {
                    List<CashTransferModel> filteredList = new ArrayList<>();
                    for (CashTransferModel row : cashTransferModelList) {
                        if(row.getName().toLowerCase().contains(charString.toLowerCase()))
                        {
                            filteredList.add(row);
                        }
                    }
                    cashTransferModelListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = cashTransferModelListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                    cashTransferModelListFiltered = (ArrayList<CashTransferModel>) results.values;
                    notifyDataSetChanged();
            }
        };
    }


}
