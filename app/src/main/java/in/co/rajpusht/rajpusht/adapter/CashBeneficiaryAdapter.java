package in.co.rajpusht.rajpusht.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import in.co.rajpusht.rajpusht.R;
import in.co.rajpusht.rajpusht.activity.CashEditForms;
import in.co.rajpusht.rajpusht.activity.CashFifthActivity;
import in.co.rajpusht.rajpusht.activity.CashFillFormsActivity;
import in.co.rajpusht.rajpusht.activity.CashFourthActivity;
import in.co.rajpusht.rajpusht.activity.CashSecondThirdActivity;
import in.co.rajpusht.rajpusht.model.CashBeneficiaryModel;
import in.co.rajpusht.rajpusht.model.Form1Model;
import in.co.rajpusht.rajpusht.model.Form2Model;
import in.co.rajpusht.rajpusht.model.Form3Model;
import in.co.rajpusht.rajpusht.model.Form4Model;
import in.co.rajpusht.rajpusht.model.Form5Model;
import in.co.rajpusht.rajpusht.model.Installment1Model;
import in.co.rajpusht.rajpusht.model.Installment2Model;
import in.co.rajpusht.rajpusht.model.Installment3Model;
import in.co.rajpusht.rajpusht.model.Installment4Model;
import in.co.rajpusht.rajpusht.model.Installment5Model;
import in.co.rajpusht.rajpusht.tool.Constants;

/**
 * Created by LENOVO on 18-Sep-18.
 */

public class CashBeneficiaryAdapter extends RecyclerView.Adapter<CashBeneficiaryAdapter.MyViewHolder>{

    private List<CashBeneficiaryModel> cashBeneficiaryModelList;
    Context context;
    String id;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, aadharId, textViewId;
        Button phase, paid;
        RelativeLayout container;

        public MyViewHolder(final View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.textView_beneficiary_name);
            aadharId = (TextView)itemView.findViewById(R.id.textViewAadharID) ;
            phase = (Button)itemView.findViewById(R.id.button_current_phase);
            paid = (Button)itemView.findViewById(R.id.button_paid);
            textViewId = (TextView)itemView.findViewById(R.id.textViewUserId);
            container = (RelativeLayout)itemView.findViewById(R.id.container);

//            paid.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                   // Log.d("cashAdapter", paid.getText().toString());
//                    String [] arr = paid.getText().toString().split("/");
//
//                    if(arr[0].equalsIgnoreCase("2nd ")&&arr[1].equalsIgnoreCase(" N"))
//                    {
//                        itemView.getContext().startActivity(new Intent(itemView.getContext(), CashSecondThirdActivity.class));
//                    }
//                    else if(arr[0].equalsIgnoreCase("3rd ")&&arr[1].equalsIgnoreCase(" N"))
//                    {
//                        itemView.getContext().startActivity(new Intent(itemView.getContext(), CashSecondThirdActivity.class));
//                    }
//                    else if(arr[0].equalsIgnoreCase("4th ")&&arr[1].equalsIgnoreCase(" N"))
//                    {
//                        itemView.getContext().startActivity(new Intent(itemView.getContext(), CashFourthActivity.class));
//                    }
//                    else if(arr[0].equalsIgnoreCase("5th ")&&arr[1].equalsIgnoreCase(" N"))
//                    {
//                        itemView.getContext().startActivity(new Intent(itemView.getContext(), CashFifthActivity.class));
//                    }
//
//                }
//            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.d("CashBeneficiaryAdapter", aadharId.getText().toString());
                    SharedPreferences preferencesJSON = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = preferencesJSON.edit();
                    editor.putString("aadharId",aadharId.getText().toString());
                    editor.putString("beneficiary_id",textViewId.getText().toString());
                    editor.apply();

                    id = textViewId.getText().toString();
                    parseJsonById();


                    Intent intent = new Intent(context, CashFillFormsActivity.class);
                    intent.putExtra("name", name.getText().toString());
                    context.startActivity(intent);



                }
            });

        }
    }



    public CashBeneficiaryAdapter(Context context, List<CashBeneficiaryModel> cashBeneficiaryModelList){
        this.context = context;
        this.cashBeneficiaryModelList = cashBeneficiaryModelList; }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cash_beneficiary_list_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CashBeneficiaryAdapter.MyViewHolder holder, int position) {
        CashBeneficiaryModel cashBeneficiaryModel = cashBeneficiaryModelList.get(position);
        holder.name.setText(cashBeneficiaryModel.getName().substring(0,1).toUpperCase()+ cashBeneficiaryModel.getName().substring(1));
        holder.aadharId.setText(cashBeneficiaryModel.getAadharId());
        holder.phase.setText(cashBeneficiaryModel.getPhase());
        holder.paid.setText(cashBeneficiaryModel.getInstallment());
        holder.textViewId.setText(cashBeneficiaryModel.getId());



        switch(cashBeneficiaryModel.getStatus())
        {
            case "1":
                holder.phase.setBackgroundResource(R.drawable.my_button_grey);
                break;

            case "2":
                holder.phase.setBackgroundResource(R.drawable.my_button_green);
                break;

            case "3":
                holder.phase.setBackgroundResource(R.drawable.my_button_orange);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return cashBeneficiaryModelList.size();
    }



    private void parseJsonById() {
            //1. parse JSON

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            String json = preferences.getString("string_json", "");
            Log.d("jsonStringIneditText", json);


            JSONObject jsonObj = null;
            try {
                jsonObj = new JSONObject(json);
                JSONArray jsonDataArray = jsonObj.getJSONArray("data");

                for(int i =0; i< jsonDataArray.length(); i++) {
                    JSONObject jsonObjList = jsonDataArray.getJSONObject(i);

                    String _id = jsonObjList.getString("_id");
                    if(id.equalsIgnoreCase(_id)) {

                        //Form1
                        JSONObject jsonSection1 = jsonObjList.getJSONObject("section1");

                        Form1Model form1Model = new Form1Model();
                        form1Model.setProject(jsonSection1.getString(Constants.JSON_PROJECT));
                        form1Model.setSector(jsonSection1.getString(Constants.JSON_SECTOR));
                        form1Model.setVillage(jsonSection1.getString(Constants.JSON_DISTRICT));
                        form1Model.setAnganwadi_center(jsonSection1.getString(Constants.JSON_ANGANWADI));
                        form1Model.setDate_of_registtration(jsonSection1.getString(Constants.JSON_DATE_OF_REGISTRATION));
                        form1Model.setBeneficiary_name(jsonSection1.getString(Constants.JSON_BENEFICIARY_NAME));

                        Log.d("CashBeneficiaryAdapter", form1Model.getBeneficiary_name());

                        Log.d("CashCorrectionGet", form1Model.getBeneficiary_name());

                        form1Model.setAge(jsonSection1.getString(Constants.JSON_AGE));
                        form1Model.setLiving_children_count(jsonSection1.getString(Constants.JSON_LIVING_CHILDREN_COUNT));
                        form1Model.setCaste(jsonSection1.getString(Constants.JSON_CASTE));
                        form1Model.setIs_bpl(jsonSection1.getString(Constants.JSON_IS_BPL));
                        form1Model.setMobile_number(jsonSection1.getString(Constants.JSON_MOBILE_NUMBER));
                        form1Model.setBhamashah_number(jsonSection1.getString(Constants.JOSN_BHAMASHAH_NUMBER));
                      //  form1Model.setDate_time_stamp(jsonSection1.getString(Constants.JSON_DATE_TIME_STAMP));

                        JSONObject jsonGuardian = jsonSection1.getJSONObject("beneficiaryGuardian");
                        form1Model.setName(jsonGuardian.getString(Constants.JSON_NAME));

                        form1Model.setRelation(jsonGuardian.getString(Constants.JSON_RELATION));

                        JSONObject jsonAadhar = jsonSection1.getJSONObject("aadhar");
                        form1Model.setAadhar_card_number(jsonAadhar.getString(Constants.JSON_AADHAR_CARD_NUMBER));
                        form1Model.setAadhar_enrolment_number(jsonAadhar.getString(Constants.JSON_AADHAR_ENROLMENT_NUMBER));


                        JSONObject jsonSection2 = jsonObjList.getJSONObject("section2");


                        // form1Model.setOwns_bank_account(jsonSection2.getString(Constants.JSON_OWNS_BANK_ACCOUNT));
                        form1Model.setAccount_holder_name(jsonSection2.getString(Constants.JSON_ACCOUNT_HOLDER_NAME));
                        form1Model.setBank_name(jsonSection2.getString(Constants.JSON_BANK_NAME));
                        form1Model.setBranch_name(jsonSection2.getString(Constants.JSON_BRANCH_NAME));
                        form1Model.setBank_account_number(jsonSection2.getString(Constants.JSON_BANK_ACCOUNT_NUMBER));
                        form1Model.setIfsc_code(jsonSection2.getString(Constants.JSON_IFSC_CODE));


                        JSONObject jsonSection3 = jsonObjList.getJSONObject("section3");

                        form1Model.setLast_menstrual_period(jsonSection3.getString(Constants.JSON_LAST_MENSTRUAL_PERIOD));


                        JSONObject jsonANCDetail = jsonSection3.getJSONObject("anc");

                        form1Model.setLast_anc_detail(jsonANCDetail.getString(Constants.JSON_LAST_ANC_DATE));
                        form1Model.setWeight(jsonANCDetail.getString(Constants.JSON_WEIGHT));
                        form1Model.setHeight(jsonANCDetail.getString(Constants.JSON_HEIGHT));
                        form1Model.setPlace_of_anc(jsonANCDetail.getString(Constants.JSON_PLACE_OF_ANC));


                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }



        }





}
