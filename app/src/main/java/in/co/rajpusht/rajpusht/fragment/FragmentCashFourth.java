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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Calendar;

import in.co.rajpusht.rajpusht.R;
import in.co.rajpusht.rajpusht.model.Form4Model;

/**
 * Created by LENOVO on 17-Sep-18.
 */

public class FragmentCashFourth extends Fragment {

    OnCallbackReceivedfourth mOnCallbackFourth;

    private EditText efi_1, efi_4, efi_5;
    RadioGroup radioGroup_fi_2, radioGroup_fi_3;
    String radioDelivery, radioGender;

    public FragmentCashFourth() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cash_fragment_fourth, container, false);

        efi_1 = (EditText)rootView.findViewById(R.id.efi_1);
        radioGroup_fi_2 = (RadioGroup)rootView.findViewById(R.id.radioGroup_fi_2);
        radioGroup_fi_3 = (RadioGroup)rootView.findViewById(R.id.radioGroup_fi_3);
        efi_4 = (EditText)rootView.findViewById(R.id.efi_4);
        efi_5 = (EditText)rootView.findViewById(R.id.efi_5);

        //Setting to current date
        Calendar mCurrentDate = Calendar.getInstance();
        final int day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        final int month = mCurrentDate.get(Calendar.MONTH);
        final int year = mCurrentDate.get(Calendar.YEAR);


        efi_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dpd = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {

                        int s=monthOfYear+1;
                        String a = dayOfMonth+"/"+s+"/"+year;
                        // editText.setText(""+a);
                        efi_1.setText(""+a);
                    }
                };

                Time date = new Time();
                DatePickerDialog d = new DatePickerDialog(getActivity(), dpd,  year , month, day);
                d.show();
            }
        });

        radioGroup_fi_2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton)group.findViewById(checkedId);

                if(rb.getText().toString().equalsIgnoreCase("YES"))
                {
                    radioDelivery = "True";
                }else
                {
                    radioDelivery = "False";
                }

            }
        });

        radioGroup_fi_3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton)group.findViewById(checkedId);
                radioGender = rb.getText().toString();
            }
        });

        //In Edit Mode
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String inEditMode = preferences.getString("in_edit_form_4", "Err");
        if(inEditMode.equalsIgnoreCase("in_edit_form_4"))
        {
          populateFields();

        }

        return  rootView;
    }

    private void populateFields() {

        Form4Model form4Model = new Form4Model();
        efi_1.setText(form4Model.getDelivery_date());
        if (form4Model.getIs_child_alive().equalsIgnoreCase("True"))
        {
            radioGroup_fi_2.check(R.id.radioLive);
        }else
        {
            radioGroup_fi_2.check(R.id.radioStill);
        }
        if (form4Model.getChild_gender().equalsIgnoreCase("Male")) {
            radioGroup_fi_3.check(R.id.radioMale);
        }else
        {
            radioGroup_fi_3.check(R.id.radioFemale);
        }
        efi_4.setText(form4Model.getBirth_weight());
        efi_5.setText(form4Model.getNon_compliance_reason());



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
                updateFourth(efi_1.getText().toString()
                        + "# " + radioDelivery
                        + "# " + radioGender
                        + "# " + efi_4.getText().toString()
                        + "# " + efi_5.getText().toString());

            } catch (Exception e) {
                //
            }
        }
    }

    public void updateFourth(String s) {
        mOnCallbackFourth.updateFourth(s);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnCallbackFourth = (OnCallbackReceivedfourth)context;
        }
        catch (ClassCastException e) {
            //
        }
    }

    @Override
    public void onDetach() {
        mOnCallbackFourth= null;
        super.onDetach();
    }

    public interface OnCallbackReceivedfourth {
        void updateFourth(String mData);
    }


}
