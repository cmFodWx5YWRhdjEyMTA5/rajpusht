package in.co.rajpusht.rajpusht.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import in.co.rajpusht.rajpusht.R;
import in.co.rajpusht.rajpusht.model.Form1Model;

/**
 * Created by LENOVO on 12-Sep-18.
 */

public class FragmentPregnancy extends Fragment {


    public static final String TAG = FragmentPregnancy.class.getSimpleName();
    OnCallbackReceivedPregnancy mCallbackPregnancy;

    ImageButton imageButtonPg1, imageButtonPg2_2;
    EditText epg1, epg2_2, epg3, epg4, epg5;


    public FragmentPregnancy() {
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
        View rootView =  inflater.inflate(R.layout.cash_fragment_pregnancy, container, false);

        //Binding Views
        epg1 = (EditText)rootView.findViewById(R.id.epg1);
        epg2_2 = (EditText)rootView.findViewById(R.id.epg2_2);
        epg3 = (EditText)rootView.findViewById(R.id.epg3);
        epg4 = (EditText)rootView.findViewById(R.id.epg4);
        epg5 = (EditText)rootView.findViewById(R.id.epg5);


        imageButtonPg1 = (ImageButton)rootView.findViewById(R.id.imageButtonPg1);
        imageButtonPg2_2 = (ImageButton)rootView.findViewById(R.id.imageButtonPg2_2);


        //Setting to current date
        Calendar mCurrentDate = Calendar.getInstance();
        final int day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        final int month = mCurrentDate.get(Calendar.MONTH);
        final int year = mCurrentDate.get(Calendar.YEAR);

        imageButtonPg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dpd = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {

                        int s=monthOfYear+1;
                        String a = dayOfMonth+"/"+s+"/"+year;
                        // editText.setText(""+a);
                        epg1.setText(""+a);
                    }
                };

                Time date = new Time();
                DatePickerDialog d = new DatePickerDialog(getActivity(), dpd, year , month, day);
                d.show();
            }
        });

        epg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dpd = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {

                        int s=monthOfYear+1;
                        String a = dayOfMonth+"/"+s+"/"+year;
                        // editText.setText(""+a);
                        epg1.setText(""+a);
                    }
                };

                Time date = new Time();
                DatePickerDialog d = new DatePickerDialog(getActivity(), dpd, year , month, day);
                d.show();
            }
        });


        imageButtonPg2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dpd = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {

                        int s=monthOfYear+1;
                        String a = dayOfMonth+"/"+s+"/"+year;
                        // editText.setText(""+a);
                        epg2_2.setText(""+a);
                    }
                };

                Time date = new Time();
                DatePickerDialog d = new DatePickerDialog(getActivity(), dpd,year , month, day);
                d.show();
            }
        });
        epg2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dpd = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {

                        int s=monthOfYear+1;
                        String a = dayOfMonth+"/"+s+"/"+year;
                        // editText.setText(""+a);
                        epg2_2.setText(""+a);
                    }
                };

                Time date = new Time();
                DatePickerDialog d = new DatePickerDialog(getActivity(), dpd, year , month, day);
                d.show();
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

        epg1.setText(form1Model.getLast_menstrual_period());
        epg2_2.setText(form1Model.getLast_anc_detail());
        epg3.setText(form1Model.getWeight());
        epg4.setText(form1Model.getHeight());
        epg5.setText(form1Model.getPlace_of_anc());




    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            Log.d(TAG, "isVisible");
        } else {
            try {
                Log.d(TAG, "notVisible");
                updatePregnancy(epg1.getText().toString()
                        + "# " + epg2_2.getText().toString()
                        + "# " + epg3.getText().toString()
                        + "# " + epg4.getText().toString()
                        + "# " + epg5.getText().toString()
                );

            } catch (Exception e)
            {
                //
            }
        }
    }

    private void updatePregnancy(String s) {
        mCallbackPregnancy.updatePregnancy(s);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallbackPregnancy =
                    (FragmentPregnancy.OnCallbackReceivedPregnancy) context;
        } catch (Exception e) {
            //
        }
    }

    @Override
    public void onDetach() {
        mCallbackPregnancy = null;
        super.onDetach();
    }

    public interface OnCallbackReceivedPregnancy {
        void updatePregnancy(String mData);
    }
}
