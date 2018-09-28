package in.co.rajpusht.rajpusht.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.co.rajpusht.rajpusht.R;
import in.co.rajpusht.rajpusht.model.Form1Model;
import in.co.rajpusht.rajpusht.model.Form2Model;

/**
 * Created by LENOVO on 12-Sep-18.
 */

public class FragmentBankAccount extends Fragment{
    String TAG = getClass().getSimpleName();

    OnCallbackReceivedBankAccount mCallbackBankAccount;

    Spinner spinnerb1;
    String string_spinnerb1;
    EditText eb2 , eb3, eb4, eb5, eb6;

    public FragmentBankAccount() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.cash_fragment_bank, container, false);

        spinnerb1 = (Spinner)rootView.findViewById(R.id.spinnerb1);
        eb2 = (EditText)rootView.findViewById(R.id.eb2);
        eb3 = (EditText)rootView.findViewById(R.id.eb3);
        eb4 = (EditText)rootView.findViewById(R.id.eb4);
        eb5= (EditText)rootView.findViewById(R.id.eb5);
        eb6 = (EditText)rootView.findViewById(R.id.eb6);

        List<String> categories = new ArrayList<String>();
        categories.add("YES");
        categories.add("NO");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerb1.setAdapter(dataAdapter);

        spinnerb1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                string_spinnerb1 = parent.getItemAtPosition(position).toString();
              //  Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //In Edit Mode
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String inEditMode = preferences.getString("in_edit_form_1", "Err");
        // Toast.makeText(getActivity(), inEditMode, Toast.LENGTH_SHORT).show();
        if(inEditMode.equalsIgnoreCase("in_edit_form_1"))
        {
            //fill data's i edittext
             fillDataInEditMode();
            // Toast.makeText(getActivity(), inEditMode, Toast.LENGTH_SHORT).show();

        }


        return rootView;
    }

    private void fillDataInEditMode() {

        Form1Model form1Model = new Form1Model();

//        switch (form1Model.getOwns_bank_account())
//        {
//            case "YES":
//                spinnerb1.setSelection(0);
//                break;
//            case "NO":
//                spinnerb1.setSelection(1);
//                break;
//        }
        eb2.setText(form1Model.getAccount_holder_name());
        eb3.setText(form1Model.getBank_name());
        eb4.setText(form1Model.getBranch_name());
        eb5.setText(form1Model.getBank_account_number());
        eb6.setText(form1Model.getIfsc_code());




    }


        @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.d(TAG, "isVisible");
        }
        else {
            try {
                Log.d(TAG, "notVisible");

                updateBankAccount(string_spinnerb1
                        + "# " + eb2.getText().toString()
                        + "# " + eb3.getText().toString()
                        + "# " + eb4.getText().toString()
                        + "# " + eb5.getText().toString()
                        + "# " + eb6.getText().toString()
                );  // sending data to activity

            }catch (Exception e)
            {
                //
            }

        }
    }


    private void updateBankAccount(String s) {
        mCallbackBankAccount.updateBankAccount(s);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallbackBankAccount = (FragmentBankAccount.OnCallbackReceivedBankAccount) context;
        }
        catch ( ClassCastException e)
        {
            //
        }
    }

    @Override
    public void onDetach() {
        mCallbackBankAccount = null;
        super.onDetach();
    }

    public interface OnCallbackReceivedBankAccount {
        void updateBankAccount(String mData);
    }
}
