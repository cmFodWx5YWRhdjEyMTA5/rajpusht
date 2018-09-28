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
import android.widget.TextView;

import java.util.Calendar;

import in.co.rajpusht.rajpusht.R;
import in.co.rajpusht.rajpusht.model.Form2Model;
import in.co.rajpusht.rajpusht.model.Form3Model;

/**
 * Created by LENOVO on 17-Sep-18.
 */

public class FragmentCashThird extends Fragment {

    OnCallbackReceivedThird mOnCallbackThird;

    private EditText ecc1_2, ecc1_3, ecc_2, ecc3_1;

    public FragmentCashThird() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cash_fragment_second_third, container, false);

        TextView thirdTextview = (TextView)rootView.findViewById(R.id.secondThirdTextView);
        thirdTextview.setText("Third Installment");

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
                DatePickerDialog d = new DatePickerDialog(getActivity(), dpd, year , month, day);
                d.show();
            }
        });


        //In Edit Mode
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String inEditMode = preferences.getString("in_edit_form_3", "Err");
        if(inEditMode.equalsIgnoreCase("in_edit_form_3"))
        {
            populateFields();

        }

        return  rootView;
    }

    private void populateFields() {

        Form3Model form3Model = new Form3Model();
        ecc1_2.setText(form3Model.getLast_anc_date());
        //ecc1_3.
        ecc_2.setText(form3Model.getWeight());
        ecc3_1.setText(form3Model.getNon_compliance_reason());

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
                updateThird(ecc1_2.getText().toString()
                        + "# " + ecc1_3.getText().toString()
                        + "# " + ecc_2.getText().toString()
                        + "# " + ecc3_1.getText().toString());

            } catch (Exception e) {
                //
            }
        }
    }

    public void updateThird(String s) {
        mOnCallbackThird.updateThird(s);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnCallbackThird = (OnCallbackReceivedThird)context;
        }
        catch (ClassCastException e) {
            //
        }
    }

    @Override
    public void onDetach() {
        mOnCallbackThird = null;
        super.onDetach();
    }

    public interface OnCallbackReceivedThird {
        void updateThird(String mData);
    }


}
