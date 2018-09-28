package in.co.rajpusht.rajpusht.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import in.co.rajpusht.rajpusht.R;
import in.co.rajpusht.rajpusht.model.Form5Model;

/**
 * Created by LENOVO on 17-Sep-18.
 */

public class FragmentCashFifth extends Fragment {

    OnCallbackReceivedFifth mOnCallbackFifth;

    private EditText effi_2, effi_4_2;
    Spinner spinner1, spinner3_1, spinner3_2, spinner3_3, spinner3_4;
    String stringSpinner1, stringSpinner3_1, stringSpinner3_2, stringSpinner3_3, stringSpinner3_4;

    public FragmentCashFifth() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cash_fragment_fifth, container, false);

        spinner1 = (Spinner)rootView.findViewById(R.id.spinner_1) ;
        effi_2 = (EditText)rootView.findViewById(R.id.effi_2);
        spinner3_1 = (Spinner) rootView.findViewById(R.id.spinner_3_1);
        spinner3_2 = (Spinner)rootView.findViewById(R.id.spinner_3_2);
        spinner3_3 = (Spinner) rootView.findViewById(R.id.spinner_3_3);
        spinner3_4 = (Spinner) rootView.findViewById(R.id.spinner_3_4);
        effi_4_2 = (EditText)rootView.findViewById(R.id.effi_4_2);


        List<String> categories = new ArrayList<String>();
        categories.add("SELECT");
        categories.add("YES");
        categories.add("NO");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(dataAdapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).toString().equalsIgnoreCase("YES"))
                {
                    stringSpinner1 = "True";
                }
                else
                {
                    stringSpinner1 = "False";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner3_1.setAdapter(dataAdapter);
        spinner3_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stringSpinner3_1 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner3_2.setAdapter(dataAdapter);
        spinner3_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stringSpinner3_2 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner3_3.setAdapter(dataAdapter);
        spinner3_3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stringSpinner3_3 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner3_4.setAdapter(dataAdapter);
        spinner3_4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stringSpinner3_4 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //In Edit Mode
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String inEditMode = preferences.getString("in_edit_form_5", "Err");
        if(inEditMode.equalsIgnoreCase("in_edit_form_5"))
        {
           populateFields();

        }
        return  rootView;
    }

    private void populateFields() {

        Form5Model form5Model = new Form5Model();

        switch (form5Model.getIs_child_alive().trim())
        {
            case "true":
                spinner1.setSelection(1);
                break;
            case "false":
                spinner1.setSelection(2);
                break;
            default:
                spinner1.setSelection(0);
                break;
        }

        effi_2.setText(form5Model.getChild_weight().trim());

        switch (form5Model.getBcg().trim())
        {
            case "YES":
                spinner3_1.setSelection(1);
                break;
            case "NO":
                spinner3_1.setSelection(2);
                break;
            default:
                spinner3_1.setSelection(0);
                break;
        }

        switch (form5Model.getHepatitis_b().trim())
        {
            case "YES":
                spinner3_2.setSelection(1);
                break;
            case "NO":
                spinner3_2.setSelection(2);
                break;
            default:
                spinner3_2.setSelection(0);
                break;
        }

        switch (form5Model.getOpv().trim())
        {
            case "YES":
                spinner3_3.setSelection(1);
                break;
            case "NO":
                spinner3_3.setSelection(2);
                break;
            default:
                spinner3_3.setSelection(0);
                break;
        }


        switch (form5Model.getPentavalent().trim())
        {
            case "YES":
                spinner3_4.setSelection(1);
                break;
            case "NO":
                spinner3_4.setSelection(2);
                break;
            default:
                spinner3_4.setSelection(0);
                break;
        }


        effi_4_2.setText(form5Model.getNon_compliance_reason());




    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser)
        {
            //
        }
        else
        {
            try {
                updateFifth(stringSpinner1
                        + "# " + effi_2.getText().toString()
                        + "# " + stringSpinner3_1
                        + "# " + stringSpinner3_2
                        + "# " + stringSpinner3_3
                        + "# " + stringSpinner3_4
                        + "# " + effi_4_2.getText().toString());

            } catch (Exception e) {
                //
            }
        }
    }

    public void updateFifth(String s) {
        mOnCallbackFifth.updateFifth(s);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnCallbackFifth = (OnCallbackReceivedFifth) context;
        }
        catch (ClassCastException e) {
            //
        }
    }

    @Override
    public void onDetach() {
        mOnCallbackFifth= null;
        super.onDetach();
    }

    public interface OnCallbackReceivedFifth {
        void updateFifth(String mData);
    }


}
