package in.co.rajpusht.rajpusht.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import in.co.rajpusht.rajpusht.R;
import in.co.rajpusht.rajpusht.activity.CashCorrectionActivity;
import in.co.rajpusht.rajpusht.activity.CashEditForms;
import in.co.rajpusht.rajpusht.dialog.DialogAccountReason;
import in.co.rajpusht.rajpusht.model.CashCorrectionModel;
import in.co.rajpusht.rajpusht.model.CashTransferModel;
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
 * Created by LENOVO on 20-Sep-18.
 */

public class CashCorrectionAdapter extends RecyclerView.Adapter<CashCorrectionAdapter.MyViewHolder> {

    private List<CashCorrectionModel> cashCorrectionModelList;
    Context mContext;
    String user_id;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, reason, due, id;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.textView_beneficiary);
            reason = (TextView)itemView.findViewById(R.id.textView_reason);
            due = (TextView)itemView.findViewById(R.id.textView_dueInstallment);
            id = (TextView)itemView.findViewById(R.id.textView_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, CashEditForms.class);
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("reason", reason.getText().toString());
                    intent.putExtra("rejected_form", due.getText().toString());
                    intent.putExtra("aadhar_id", id.getText().toString());
                    mContext.startActivity(intent);

                    user_id= id.getText().toString();

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("beneficiary_id",id.getText().toString());
                    editor.apply();


                  //  parseJSONById();

                   // Toast.makeText(mContext, id.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });


            reason.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     Intent intent = new Intent(mContext, DialogAccountReason.class);
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("reason", reason.getText().toString());
                    intent.putExtra("rejected_form", due.getText().toString());
                    mContext.startActivity(intent);

                }
            });

        }
    }


    public CashCorrectionAdapter(Context mContext, List<CashCorrectionModel> cashCorrectionModelsList){
        this.mContext = mContext;
        this.cashCorrectionModelList = cashCorrectionModelsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cash_correction_items, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CashCorrectionAdapter.MyViewHolder holder, int position) {
        CashCorrectionModel cashCorrectionModel = cashCorrectionModelList.get(position);
        holder.name.setText(cashCorrectionModel.getName().substring(0,1).toUpperCase()+cashCorrectionModel.getName().substring(1));
        holder.reason.setText(cashCorrectionModel.getReason());
        holder.due.setText(cashCorrectionModel.getDue());
        holder.id.setText(cashCorrectionModel.getId());
    }

    @Override
    public int getItemCount() {
        return cashCorrectionModelList.size();
    }


    private void parseJSONById() {
        //1. parse JSON

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String json = preferences.getString("string_json", "");
        Log.d("jsonStringIneditText", json);


        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(json);
            JSONArray jsonDataArray = jsonObj.getJSONArray("data");

            for(int i =0; i< jsonDataArray.length(); i++) {
                JSONObject jsonObjList = jsonDataArray.getJSONObject(i);

                String _id = jsonObjList.getString("_id");
                if(user_id.equalsIgnoreCase(_id))
                {

                    JSONObject jsonForms = jsonObjList.getJSONObject("forms");


                    //Form 1
                    JSONObject jsonForm1 = jsonForms.getJSONObject("form_1");
                    JSONObject jsonSection1 = jsonForm1.getJSONObject("section_1");

                    Form1Model form1Model = new Form1Model();
                    form1Model.setProject(jsonSection1.getString(Constants.JSON_PROJECT));
                    form1Model.setSector(jsonSection1.getString(Constants.JSON_SECTOR));
                    form1Model.setVillage(jsonSection1.getString(Constants.JSON_DISTRICT));
                    form1Model.setAnganwadi_center(jsonSection1.getString(Constants.JSON_ANGANWADI));
                    form1Model.setDate_of_registtration(jsonSection1.getString(Constants.JSON_DATE_OF_REGISTRATION));
                    form1Model.setBeneficiary_name(jsonSection1.getString(Constants.JSON_BENEFICIARY_NAME));

                    Log.d("CashCorrectionGet", form1Model.getBeneficiary_name());

                    form1Model.setAge(jsonSection1.getString(Constants.JSON_AGE));
                    form1Model.setLiving_children_count(jsonSection1.getString(Constants.JSON_LIVING_CHILDREN_COUNT));
                    form1Model.setCaste(jsonSection1.getString(Constants.JSON_CASTE));
                    form1Model.setIs_bpl(jsonSection1.getString(Constants.JSON_IS_BPL));
                    form1Model.setMobile_number(jsonSection1.getString(Constants.JSON_MOBILE_NUMBER));
                    form1Model.setBhamashah_number(jsonSection1.getString(Constants.JOSN_BHAMASHAH_NUMBER));
                    form1Model.setDate_time_stamp(jsonSection1.getString(Constants.JSON_DATE_TIME_STAMP));

                    JSONObject jsonGuardian = jsonSection1.getJSONObject("beneficiary_guardian");
                    form1Model.setName(jsonGuardian.getString(Constants.JSON_NAME));

                    form1Model.setRelation(jsonGuardian.getString(Constants.JSON_RELATION));

                    JSONObject jsonAadhar = jsonSection1.getJSONObject("aadhar");
                    form1Model.setAadhar_card_number(jsonAadhar.getString(Constants.JSON_AADHAR_CARD_NUMBER));
                    form1Model.setAadhar_enrolment_number(jsonAadhar.getString(Constants.JSON_AADHAR_ENROLMENT_NUMBER));



                    JSONObject jsonSection2 = jsonForm1.getJSONObject("section_2");


                    form1Model.setOwns_bank_account(jsonSection2.getString(Constants.JSON_OWNS_BANK_ACCOUNT));
                    form1Model.setAccount_holder_name(jsonSection2.getString(Constants.JSON_ACCOUNT_HOLDER_NAME));
                    form1Model.setBank_name(jsonSection2.getString(Constants.JSON_BANK_NAME));
                    form1Model.setBranch_name(jsonSection2.getString(Constants.JSON_BRANCH_NAME));
                    form1Model.setBank_account_number(jsonSection2.getString(Constants.JSON_BANK_ACCOUNT_NUMBER));
                    form1Model.setIfsc_code(jsonSection2.getString(Constants.JSON_IFSC_CODE));


                    JSONObject jsonSection3 = jsonForm1.getJSONObject("section_3");

                    form1Model.setLast_menstrual_period(jsonSection3.getString(Constants.JSON_LAST_MENSTRUAL_PERIOD));


                    JSONObject jsonANCDetail = jsonSection3.getJSONObject("anc_details");

                    form1Model.setLast_anc_detail(jsonANCDetail.getString(Constants.JSON_LAST_ANC_DATE));
                    form1Model.setWeight(jsonANCDetail.getString(Constants.JSON_WEIGHT));
                    form1Model.setHeight(jsonANCDetail.getString(Constants.JSON_HEIGHT));
                    form1Model.setPlace_of_anc(jsonANCDetail.getString(Constants.JSON_PLACE_OF_ANC));

                    JSONObject jsonOtherDetails = jsonForm1.getJSONObject("other_details");
                    form1Model.setAww_name(jsonOtherDetails.getString(Constants.JSON_AWW_NAME));
                    form1Model.setAww_username(jsonOtherDetails.getString(Constants.JSON_AWW_USERNAME));
                    form1Model.setForm_received_date(jsonOtherDetails.getString(Constants.JSON_FORM_RECEIVED_DATE));
                    form1Model.setData_entry_date(jsonOtherDetails.getString(Constants.JSON_DATA_ENTRY_DATE));



                    //Form2
                    JSONObject jsonForm2 = jsonForms.getJSONObject("form_2");
                    JSONObject jsonF2Section1 = jsonForm2.getJSONObject("section_1");

                    Form2Model form2Model = new Form2Model();
                    form2Model.setBeneficiary_name(jsonF2Section1.getString(Constants.JSON_BENEFICIARY_NAME));
                    form2Model.setMobile_number(jsonF2Section1.getString(Constants.JSON_MOBILE_NUMBER));

                    JSONObject jsonF2S1BeneficiaryGuardian = jsonF2Section1.getJSONObject("beneficiary_guardian");


                    form2Model.setName(jsonF2S1BeneficiaryGuardian.getString(Constants.JSON_NAME));
                    form2Model.setRelation(jsonF2S1BeneficiaryGuardian.getString(Constants.JSON_RELATION));

                    JSONObject jsonF2S1Aadhar = jsonF2Section1.getJSONObject("aadhar");
                    form2Model.setAadhar_card_number(jsonF2S1Aadhar.getString(Constants.JSON_AADHAR_CARD_NUMBER));
                    form2Model.setAadhar_enrolment_number(jsonF2S1Aadhar.getString(Constants.JSON_AADHAR_ENROLMENT_NUMBER));

                    JSONObject jsonF2S2 = jsonForm2.getJSONObject("section_2");
                    JSONObject jsonF2S2ANCDetails = jsonF2S2.getJSONObject("anc_details");
                    form2Model.setAnc_completed(jsonF2S2ANCDetails.getString(Constants.JSON_ANC_COMPLETED));
                    form2Model.setLast_anc_date(jsonF2S2ANCDetails.getString(Constants.JSON_LAST_ANC_DATE));
                    form2Model.setWeight(jsonF2S2ANCDetails.getString(Constants.JSON_WEIGHT));

                    JSONObject jsonF2S3 = jsonForm2.getJSONObject("section_3");
                    form2Model.setNon_compliance_reason(jsonF2S3.getString(Constants.JSON_NON_COMPLIANCE_REASON));

                    JSONObject jsonF2OD = jsonForm2.getJSONObject("other_details");
                    form2Model.setAww_name(jsonF2OD.getString(Constants.JSON_AWW_NAME));
                    form2Model.setAww_username(jsonF2OD.getString(Constants.JSON_AWW_USERNAME));
                    form2Model.setForm_received_date(jsonF2OD.getString(Constants.JSON_FORM_RECEIVED_DATE));
                    form2Model.setData_entry_date(jsonF2OD.getString(Constants.JSON_DATA_ENTRY_DATE));


                    //Form3
                    Form3Model form3Model = new Form3Model();

                    JSONObject jsonF3 = jsonForms.getJSONObject("form_3");
                    JSONObject jsonF3S1 = jsonF3.getJSONObject("section_1");

                    form3Model.setBeneficiary_name(jsonF3S1.getString(Constants.JSON_BENEFICIARY_NAME));
                    form3Model.setMobile_nummber(jsonF3S1.getString(Constants.JSON_MOBILE_NUMBER));

                    JSONObject jsonF3S1BeneficiaryGuardian = jsonF3S1.getJSONObject("beneficiary_guardian");


                    form3Model.setName(jsonF3S1BeneficiaryGuardian.getString(Constants.JSON_NAME));
                    form3Model.setRelation(jsonF3S1BeneficiaryGuardian.getString(Constants.JSON_RELATION));

                    JSONObject jsonF3S1Aadhar = jsonF3S1.getJSONObject("aadhar");
                    form3Model.setAadhar_card_number(jsonF3S1Aadhar.getString(Constants.JSON_AADHAR_CARD_NUMBER));
                    form3Model.setAadhar_enrolment_number(jsonF3S1Aadhar.getString(Constants.JSON_AADHAR_ENROLMENT_NUMBER));

                    JSONObject jsonF3S2 = jsonF3.getJSONObject("section_2");
                    JSONObject jsonF3S2ANCDetails = jsonF3S2.getJSONObject("anc_details");
                    form3Model.setAnc_completed(jsonF3S2ANCDetails.getString(Constants.JSON_ANC_COMPLETED));
                    form3Model.setLast_anc_date(jsonF3S2ANCDetails.getString(Constants.JSON_LAST_ANC_DATE));
                    form3Model.setWeight(jsonF3S2ANCDetails.getString(Constants.JSON_WEIGHT));

                    JSONObject jsonF3S3 = jsonF3.getJSONObject("section_3");
                    form3Model.setNon_compliance_reason(jsonF3S3.getString(Constants.JSON_NON_COMPLIANCE_REASON));

                    JSONObject jsonF3OD = jsonF3.getJSONObject("other_details");
                    form3Model.setAww_name(jsonF3OD.getString(Constants.JSON_AWW_NAME));
                    form3Model.setAww_username(jsonF3OD.getString(Constants.JSON_AWW_USERNAME));
                    form3Model.setForm_received_date(jsonF3OD.getString(Constants.JSON_FORM_RECEIVED_DATE));
                    form3Model.setData_entry_date(jsonF3OD.getString(Constants.JSON_DATA_ENTRY_DATE));


                    //Form4
                    Form4Model form4Model = new Form4Model();

                    JSONObject jsonF4 = jsonForms.getJSONObject("form_4");
                    JSONObject jsonF4S1 = jsonF4.getJSONObject("section_1");

                    form4Model.setBeneficiary_name(jsonF4S1.getString(Constants.JSON_BENEFICIARY_NAME));
                    form4Model.setMobile_nummber(jsonF4S1.getString(Constants.JSON_MOBILE_NUMBER));

                    JSONObject jsonF4S1BeneficiaryGuardian = jsonF4S1.getJSONObject("beneficiary_guardian");


                    form4Model.setName(jsonF4S1BeneficiaryGuardian.getString(Constants.JSON_NAME));
                    form4Model.setRelation(jsonF4S1BeneficiaryGuardian.getString(Constants.JSON_RELATION));

                    JSONObject jsonF4S1Aadhar = jsonF4S1.getJSONObject("aadhar");
                    form4Model.setAadhar_card_number(jsonF4S1Aadhar.getString(Constants.JSON_AADHAR_CARD_NUMBER));
                    form4Model.setAadhar_enrolment_number(jsonF4S1Aadhar.getString(Constants.JSON_AADHAR_ENROLMENT_NUMBER));


                    JSONObject jsonF4S2 = jsonF4.getJSONObject("section_2");
                    form4Model.setDelivery_date(jsonF4S2.getString(Constants.JSON_DELIVERY_DATE));
                    form4Model.setIs_child_alive(jsonF4S2.getString(Constants.JSON_IS_CHILD_ALIVE));
                    form4Model.setChild_gender(jsonF4S2.getString(Constants.JSON_CHILD_GENDER));
                    form4Model.setBirth_weight(jsonF4S2.getString(Constants.JSON_BIRTH_WEIGHT));

                    JSONObject jsonF4S3 = jsonF4.getJSONObject("section_3");
                    form4Model.setNon_compliance_reason(jsonF4S3.getString(Constants.JSON_NON_COMPLIANCE_REASON));

                    JSONObject jsonF4OD = jsonF4.getJSONObject("other_details");
                    form4Model.setAww_name(jsonF4OD.getString(Constants.JSON_AWW_NAME));
                    form4Model.setAww_username(jsonF4OD.getString(Constants.JSON_AWW_USERNAME));
                    form4Model.setForm_received_date(jsonF4OD.getString(Constants.JSON_FORM_RECEIVED_DATE));
                    form4Model.setData_entry_date(jsonF4OD.getString(Constants.JSON_DATA_ENTRY_DATE));


                    //Form5
                    Form5Model form5Model = new Form5Model();

                    JSONObject jsonF5 = jsonForms.getJSONObject("form_5");
                    JSONObject jsonF5S1 = jsonF5.getJSONObject("section_1");

                    form5Model.setBeneficiary_name(jsonF5S1.getString(Constants.JSON_BENEFICIARY_NAME));
                    form5Model.setMobile_nummber(jsonF5S1.getString(Constants.JSON_MOBILE_NUMBER));

                    JSONObject jsonF5S1BeneficiaryGuardian = jsonF5S1.getJSONObject("beneficiary_guardian");


                    form5Model.setName(jsonF5S1BeneficiaryGuardian.getString(Constants.JSON_NAME));
                    form5Model.setRelation(jsonF5S1BeneficiaryGuardian.getString(Constants.JSON_RELATION));

                    JSONObject jsonF5S1Aadhar = jsonF5S1.getJSONObject("aadhar");
                    form5Model.setAadhar_card_number(jsonF5S1Aadhar.getString(Constants.JSON_AADHAR_CARD_NUMBER));
                    form5Model.setAadhar_enrolment_number(jsonF5S1Aadhar.getString(Constants.JSON_AADHAR_ENROLMENT_NUMBER));

                    JSONObject jsonF5S2 = jsonF5.getJSONObject("section_2");
                    form5Model.setIs_child_alive(jsonF5S2.getString(Constants.JSON_IS_CHILD_ALIVE));
                    form5Model.setChild_weight(jsonF5S2.getString(Constants.JSON_CHILD_WEIGHT));

                    JSONObject jsonF5S2Vaccinations = jsonF5S2.getJSONObject("vaccinations");
                    form5Model.setBcg(jsonF5S2Vaccinations.getString(Constants.JSON_BCG));
                    form5Model.setHepatitis_b(jsonF5S2Vaccinations.getString(Constants.JSON_HEPATITIS_B));
                    form5Model.setOpv(jsonF5S2Vaccinations.getString(Constants.JSON_OPV));
                    form5Model.setPentavalent(jsonF5S2Vaccinations.getString(Constants.JSON_PENTAVALENT));

                    JSONObject jsonF5S3 = jsonF5.getJSONObject("section_3");
                    form5Model.setNon_compliance_reason(jsonF5S3.getString(Constants.JSON_NON_COMPLIANCE_REASON));

                    JSONObject jsonF5OD = jsonF5.getJSONObject("other_details");
                    form5Model.setAww_name(jsonF5OD.getString(Constants.JSON_AWW_NAME));
                    form5Model.setAww_username(jsonF5OD.getString(Constants.JSON_AWW_USERNAME));
                    form5Model.setForm_received_date(jsonF5OD.getString(Constants.JSON_FORM_RECEIVED_DATE));
                    form5Model.setData_entry_date(jsonF5OD.getString(Constants.JSON_DATA_ENTRY_DATE));

                    //Installment Status

                    JSONObject jsonInstallmentStatus = jsonForms.getJSONObject("installment_status");

                    //Installment 1
                    Installment1Model installment1Model = new Installment1Model();
                    installment1Model.setCurrent_installment(jsonInstallmentStatus.getString(Constants.JSON_CURRENT_INSTALLMENT));

                    JSONObject jsonInstallment1 = jsonInstallmentStatus.getJSONObject("installment_1");
                    installment1Model.setStatus(jsonInstallment1.getString(Constants.JSON_STATUS));
                    installment1Model.setApprovedBySectorCoordinator(jsonInstallment1.getString(Constants.JSON_APPROVED_BY_SECTOR_COORDINATOR));
                    installment1Model.setApprovedByBIDM(jsonInstallment1.getString(Constants.JSON_APPROVED_BY_BIDM));
                    installment1Model.setApprovedByCDPO(jsonInstallment1.getString(Constants.JSON_APPROVED_BY_CDPO));
                    installment1Model.setApprovedByDIDM(jsonInstallment1.getString(Constants.JOSN_APPROVED_BY_DIDM));
                    installment1Model.setApprovedByDD(jsonInstallment1.getString(Constants.JSON_APPROVED_BY_DD));
                    installment1Model.setApprovedByBank(jsonInstallment1.getString(Constants.JSON_APPROVED_BY_BANK));

                    //Installment 2

                    JSONObject jsonInstallment2 = jsonInstallmentStatus.getJSONObject("installment_2");
                    Installment2Model installment2Model = new Installment2Model();

                    installment2Model.setStatus(jsonInstallment2.getString(Constants.JSON_STATUS));
                    installment2Model.setApprovedBySectorCoordinator(jsonInstallment2.getString(Constants.JSON_APPROVED_BY_SECTOR_COORDINATOR));
                    installment2Model.setApprovedByBIDM(jsonInstallment2.getString(Constants.JSON_APPROVED_BY_BIDM));
                    installment2Model.setApprovedByCDPO(jsonInstallment2.getString(Constants.JSON_APPROVED_BY_CDPO));
                    installment2Model.setApprovedByDIDM(jsonInstallment2.getString(Constants.JOSN_APPROVED_BY_DIDM));
                    installment2Model.setApprovedByDD(jsonInstallment2.getString(Constants.JSON_APPROVED_BY_DD));
                    installment2Model.setApprovedByBank(jsonInstallment2.getString(Constants.JSON_APPROVED_BY_BANK));


                    //Installment 3

                    JSONObject jsonInstallment3 = jsonInstallmentStatus.getJSONObject("installment_3");
                    Installment3Model installment3Model = new Installment3Model();

                    installment3Model.setStatus(jsonInstallment3.getString(Constants.JSON_STATUS));
                    installment3Model.setApprovedBySectorCoordinator(jsonInstallment3.getString(Constants.JSON_APPROVED_BY_SECTOR_COORDINATOR));
                    installment3Model.setApprovedByBIDM(jsonInstallment3.getString(Constants.JSON_APPROVED_BY_BIDM));
                    installment3Model.setApprovedByCDPO(jsonInstallment3.getString(Constants.JSON_APPROVED_BY_CDPO));
                    installment3Model.setApprovedByDIDM(jsonInstallment3.getString(Constants.JOSN_APPROVED_BY_DIDM));
                    installment3Model.setApprovedByDD(jsonInstallment3.getString(Constants.JSON_APPROVED_BY_DD));
                    installment3Model.setApprovedByBank(jsonInstallment3.getString(Constants.JSON_APPROVED_BY_BANK));


                    //Installment 4

                    JSONObject jsonInstallment4 = jsonInstallmentStatus.getJSONObject("installment_4");
                    Installment4Model installment4Model = new Installment4Model();

                    installment4Model.setStatus(jsonInstallment4.getString(Constants.JSON_STATUS));
                    installment4Model.setApprovedBySectorCoordinator(jsonInstallment4.getString(Constants.JSON_APPROVED_BY_SECTOR_COORDINATOR));
                    installment4Model.setApprovedByBIDM(jsonInstallment4.getString(Constants.JSON_APPROVED_BY_BIDM));
                    installment4Model.setApprovedByCDPO(jsonInstallment4.getString(Constants.JSON_APPROVED_BY_CDPO));
                    installment4Model.setApprovedByDIDM(jsonInstallment4.getString(Constants.JOSN_APPROVED_BY_DIDM));
                    installment4Model.setApprovedByDD(jsonInstallment4.getString(Constants.JSON_APPROVED_BY_DD));
                    installment4Model.setApprovedByBank(jsonInstallment4.getString(Constants.JSON_APPROVED_BY_BANK));


                    //Installment 5

                    JSONObject jsonInstallment5 = jsonInstallmentStatus.getJSONObject("installment_5");
                    Installment5Model installment5Model = new Installment5Model();

                    installment5Model.setStatus(jsonInstallment2.getString(Constants.JSON_STATUS));
                    installment5Model.setApprovedBySectorCoordinator(jsonInstallment5.getString(Constants.JSON_APPROVED_BY_SECTOR_COORDINATOR));
                    installment5Model.setApprovedByBIDM(jsonInstallment5.getString(Constants.JSON_APPROVED_BY_BIDM));
                    installment5Model.setApprovedByCDPO(jsonInstallment5.getString(Constants.JSON_APPROVED_BY_CDPO));
                    installment5Model.setApprovedByDIDM(jsonInstallment5.getString(Constants.JOSN_APPROVED_BY_DIDM));
                    installment5Model.setApprovedByDD(jsonInstallment5.getString(Constants.JSON_APPROVED_BY_DD));
                    installment5Model.setApprovedByBank(jsonInstallment5.getString(Constants.JSON_APPROVED_BY_BANK));
                }



            }
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }



}
