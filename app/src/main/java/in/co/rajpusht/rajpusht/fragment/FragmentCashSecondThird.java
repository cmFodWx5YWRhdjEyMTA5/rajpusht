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

import java.util.Calendar;

import in.co.rajpusht.rajpusht.R;
import in.co.rajpusht.rajpusht.model.Form2Model;

/**
 * Created by LENOVO on 17-Sep-18.
 */

public class FragmentCashSecondThird extends Fragment {

    OnCallbackReceivedSecondThird mOnCallbackSecondThird;

    private EditText ecc1_2, ecc1_3, ecc_2, ecc3_1;

    public FragmentCashSecondThird() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cash_fragment_second_third, container, false);

        ecc1_2 = (EditText)rootView.findViewById(R.id.ecc1_2);
        ecc1_3 = (EditText)rootView.findViewById(R.id.ecc1_3);
        ecc_2 = (EditText)rootView.findViewById(R.id.ecc_2);
        ecc3_1 = (EditText)rootView.findViewById(R.id.ecc3_1);

        //Setting to current date
        Calendar mCurrentDate = Calendar.getInstance();
        final int day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        final int month = mCurrentDate.get(Calendar.MONTH);
        final int year = mCurrentDate.get(Calendar.YEAR);


        ecc1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dpd = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {

                        int s=monthOfYear+1;
                        String a = dayOfMonth+"/"+s+"/"+year;
                        // editText.setText(""+a);
                        ecc1_2.setText(""+a);
                    }
                };

                Time date = new Time();
                DatePickerDialog d = new DatePickerDialog(getActivity(), dpd, year ,month, day);
                d.show();
            }
        });

        //In Edit Mode
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String inEditMode = preferences.getString("in_edit_form_2", "Err");
        if(inEditMode.equalsIgnoreCase("in_edit_form_2"))
        {
            populateFields();

        }





        return  rootView;
    }

    private void populateFields() {

        Form2Model form2Model = new Form2Model();
        ecc1_2.setText(form2Model.getLast_anc_date());
        //ecc1_3.
        ecc_2.setText(form2Model.getWeight());
        ecc3_1.setText(form2Model.getNon_compliance_reason());

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
                updateSecondThird(ecc1_2.getText().toString()
                        + "# " + ecc1_3.getText().toString()
                        + "# " + ecc_2.getText().toString()
                        + "# " + ecc3_1.getText().toString());

            } catch (Exception e) {
                //
            }
        }
    }

    public void updateSecondThird(String s) {
        mOnCallbackSecondThird.updateSecondThird(s);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnCallbackSecondThird = (OnCallbackReceivedSecondThird)context;
        }
        catch (ClassCastException e) {
            //
        }
    }

    @Override
    public void onDetach() {
        mOnCallbackSecondThird = null;
        super.onDetach();
    }

    public interface OnCallbackReceivedSecondThird {
        void updateSecondThird(String mData);
    }


}
