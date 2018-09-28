package in.co.rajpusht.rajpusht.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.Time;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import in.co.rajpusht.rajpusht.R;
import in.co.rajpusht.rajpusht.model.Form1Model;
import pl.polak.clicknumberpicker.ClickNumberPickerView;

/**
 * Created by LENOVO on 12-Sep-18.
 */

public class FragmentPersonal extends Fragment{

    String TAG = getClass().getSimpleName();

    ImageButton imageButtonP5, imageButtonP10, imageButtonP11;
    EditText eP1, eP2, eP3, eP4, eP5, eP6, eP7, eP10, eP11, eP12, eP13;
    TextInputLayout ePh1;
    Spinner spinnerP8, spinnerP9;
    private String string_spinnerP8, string_spinnerp9;
    ArrayAdapter<String> dataAdapterP9;
    ArrayAdapter<String> dataAdapter8;

    OnCallbackReceivedPersonal mCallbackPersonal;

    public FragmentPersonal() {
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
        View rootView =  inflater.inflate(R.layout.cash_fragment_personal, container, false);

        //Binding all Views
        eP1 = (EditText)rootView.findViewById(R.id.eP1);
        eP2 = (EditText)rootView.findViewById(R.id.eP2);
        eP3 = (EditText)rootView.findViewById(R.id.eP3);
        eP4 = (EditText)rootView.findViewById(R.id.eP4);
        eP5 = (EditText)rootView.findViewById(R.id.eP5);
        eP6 = (EditText)rootView.findViewById(R.id.eP6);
        eP7 = (EditText)rootView.findViewById(R.id.eP7);
        spinnerP8 = (Spinner) rootView.findViewById(R.id.spinnerP8);
        spinnerP9 = (Spinner) rootView.findViewById(R.id.spinnerP9);
        eP10 = (EditText)rootView.findViewById(R.id.eP10);
        eP11 = (EditText) rootView.findViewById(R.id.eP11);
        eP12 = (EditText) rootView.findViewById(R.id.eP12);
        eP13 = (EditText) rootView.findViewById(R.id.eP13);










        imageButtonP5 = (ImageButton)rootView.findViewById(R.id.imageButtonP5);
        imageButtonP10 = (ImageButton)rootView.findViewById(R.id.imageButtonP10);
        imageButtonP11 = (ImageButton)rootView.findViewById(R.id.imageButtonP11);

        //Setting to current date
        Calendar mCurrentDate = Calendar.getInstance();
        final int day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        final int month = mCurrentDate.get(Calendar.MONTH);
        final int year = mCurrentDate.get(Calendar.YEAR);


        imageButtonP5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dpd = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {

                        int s = monthOfYear+1;
                        String a = dayOfMonth+"/"+s+"/"+year;
                       // editText.setText(""+a);
                        eP5.setText(""+a);
                    }
                };

                Time date = new Time();
                DatePickerDialog d = new DatePickerDialog(getActivity(), dpd, year ,month, day);
                d.show();

            }

        });
        eP5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dpd = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {

                        int s = monthOfYear+1;
                        String a = dayOfMonth+"/"+s+"/"+year;
                        // editText.setText(""+a);
                        eP5.setText(""+a);
                    }
                };

                Time date = new Time();
                DatePickerDialog d = new DatePickerDialog(getActivity(), dpd, year ,month, day);
                d.show();
            }
        });

        List<String> categories8 = new ArrayList<String>();
        categories8.add("Select Caste:");
        categories8.add("ST");
        categories8.add("SC");
        categories8.add("OBC");
        categories8.add("Others");
        dataAdapter8 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories8);
        dataAdapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerP8.setAdapter(dataAdapter8);
        spinnerP8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                string_spinnerP8 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        List<String> categories = new ArrayList<String>();
        categories.add("Choose Option");
        categories.add("YES");
        categories.add("NO");
        dataAdapterP9 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);
        dataAdapterP9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerP9.setAdapter(dataAdapterP9);

        spinnerP9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                string_spinnerp9 = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });


        imageButtonP10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dpd = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {

                        int s=monthOfYear+1;
                        String a = dayOfMonth+"/"+s+"/"+year;
                        // editText.setText(""+a);
                        eP10.setText(""+a);
                    }
                };

                Time date = new Time();
                DatePickerDialog d = new DatePickerDialog(getActivity(), dpd, year ,month, day);
                d.show();

            }

        });
        eP10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dpd = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {

                        int s=monthOfYear+1;
                        String a = dayOfMonth+"/"+s+"/"+year;
                        // editText.setText(""+a);
                        eP10.setText(""+a);
                    }
                };

                Time date = new Time();
                DatePickerDialog d = new DatePickerDialog(getActivity(), dpd,year ,month, day);
                d.show();

            }
        });



        imageButtonP11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                       // eReminderTime.setText( selectedHour + ":" + selectedMinute);
                        eP11.setText( selectedHour+ ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
        eP11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        // eReminderTime.setText( selectedHour + ":" + selectedMinute);
                        eP11.setText( selectedHour+ ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
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



        return  rootView;
    }

    private void fillDataInEditMode() {

        //Filling data to fields
        Form1Model form1Model = new Form1Model();

        eP1.setText(form1Model.getBeneficiary_name());
        eP2.setText(form1Model.getName());
        eP3.setText(form1Model.getAge());
        eP4.setText(form1Model.getMobile_number());
        eP5.setText(form1Model.getDate_of_registtration());
        eP6.setText(form1Model.getBhamashah_number());
        eP7.setText(form1Model.getLiving_children_count());

//       Log.d("FragmentPersonal", form1Model.getBeneficiary_name());



        switch (form1Model.getCaste())
        {
            case "ST":
                spinnerP8.setSelection(1);
                break;
            case "SC":
                spinnerP8.setSelection(2);
                break;
            case "OBC":
                spinnerP8.setSelection(3);
                break;
            case "Others":
                spinnerP8.setSelection(4);
                break;
            default:
                spinnerP8.setSelection(0);
                break;
        }

        switch(form1Model.getIs_bpl())
        {
            case "YES":
                spinnerP9.setSelection(1);
                break;
            case "NO":
                spinnerP9.setSelection(2);
                break;
            default:
                spinnerP9.setSelection(0);
                break;

        }

        String date_time_stamp = form1Model.getDate_time_stamp();

//        String [] date_time = date_time_stamp.split("#");
//        String date_stamp = date_time[0];
//        String time_stamp = date_time[1];
//
//        eP10.setText(date_stamp);
//        eP11.setText(time_stamp);
        eP12.setText(form1Model.getAadhar_card_number());
        eP12.setEnabled(false);
        eP13.setText(form1Model.getAadhar_enrolment_number());


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

                updatePersonal(eP1.getText().toString()
                        + "# " + eP2.getText().toString()
                        + "# " + eP3.getText().toString()
                        + "# " + eP4.getText().toString()
                        + "# " + eP5.getText().toString()
                        + "# " + eP6.getText().toString()
                        + "# " + eP7.getText().toString()
                        + "# " + string_spinnerP8
                        + "# " + string_spinnerp9
                        + "# " + eP10.getText().toString()
                        + "# " + eP11.getText().toString()
                        + "# " + eP12.getText().toString()
                        + "# " + eP13.getText().toString()
                );  // sending data to activity

            }catch (Exception e)
            {
                //
            }

        }
    }



    private void updatePersonal(String s) {
        mCallbackPersonal.updatePersonal(s);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallbackPersonal = (OnCallbackReceivedPersonal) context;
        }
        catch ( ClassCastException e)
        {
            //
        }
    }

    @Override
    public void onDetach() {
        mCallbackPersonal = null;
        super.onDetach();
    }

    public interface OnCallbackReceivedPersonal {
        void updatePersonal(String mData);
   }





}
