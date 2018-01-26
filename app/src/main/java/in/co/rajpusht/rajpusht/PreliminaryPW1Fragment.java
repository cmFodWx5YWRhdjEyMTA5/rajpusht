package in.co.rajpusht.rajpusht;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PreliminaryPW1Fragment extends Fragment {

    TextView date;
    private int mYear, mMonth, mDay;
    Spinner spinner1, spinner2;

    public PreliminaryPW1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_preliminary_pw1, container, false);

        date = (TextView) v.findViewById(R.id.date);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                date.setError(null);
                final Calendar calender = Calendar.getInstance();
                mYear = calender.get(Calendar.YEAR);
                mMonth = calender.get(Calendar.MONTH);
                mDay = calender.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dpd.show();
            }
        });

        spinner2 = (Spinner) v.findViewById(R.id.pw1_amount);
        List<String> list1 = new ArrayList<String>();
        list1.add("--Select Options--");
        list1.add("Less than Rs. 500 per month");
        list1.add("Rs. 500- 1,000 per month");
        list1.add("Rs. 1000 – 2000 per month");
        list1.add("Rs. 2000 – 4000 per month");
        list1.add("Rs. 4000 – 5,000 per month");
        list1.add("Rs. 5,000 and above");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter1);


        spinner1 = (Spinner) v.findViewById(R.id.pw1_yesno);
        List<String> list = new ArrayList<String>();
        list.add("--Select Options--");
        list.add("Yes");
        list.add("No");
        list.add("Can't Remember");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);


        return v;
    }

}
