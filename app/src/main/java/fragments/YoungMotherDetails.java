package fragments;

import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import in.co.rajpusht.rajpusht.R;



public class YoungMotherDetails extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    TextView date;
    private int mYear, mMonth, mDay;
    Spinner placeofDelivery,sexOfchild,wasChildBorn,birthBreast,firstyellowFeed;
    EditText nameOfChild,birthWeightChild;

    private OnFragmentInteractionListener mListener;
    RelativeLayout c1, c2, c3, c4, c5, c6, c7, c8, c9;
    ImageView ch1, ch2, ch3, ch4, ch5, ch6, ch7, ch8, ch9, ch1c, ch2c, ch3c, ch4c, ch5c, ch6c, ch7c, ch8c, ch9c ;
    Button addchild;

    public YoungMotherDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
                View view=inflater.inflate(R.layout.fragment_young_mother_details, container, false);

        c1 = (RelativeLayout) view.findViewById(R.id.c1);
        c2 = (RelativeLayout) view.findViewById(R.id.c2);
        c3 = (RelativeLayout) view.findViewById(R.id.c3);
        c4 = (RelativeLayout) view.findViewById(R.id.c4);
        c5 = (RelativeLayout) view.findViewById(R.id.c5);
        c6 = (RelativeLayout) view.findViewById(R.id.c6);
        c7 = (RelativeLayout) view.findViewById(R.id.c7);
        c8 = (RelativeLayout) view.findViewById(R.id.c8);
        c9 = (RelativeLayout) view.findViewById(R.id.c9);

        ch1 = (ImageView) view.findViewById(R.id.ch1);
        ch2 = (ImageView) view.findViewById(R.id.ch2);
        ch3 = (ImageView) view.findViewById(R.id.ch3);
        ch4 = (ImageView) view.findViewById(R.id.ch4);
        ch5 = (ImageView) view.findViewById(R.id.ch5);
        ch6 = (ImageView) view.findViewById(R.id.ch6);
        ch7 = (ImageView) view.findViewById(R.id.ch7);
        ch8 = (ImageView) view.findViewById(R.id.ch8);
        ch9 = (ImageView) view.findViewById(R.id.ch9);

        ch1c = (ImageView) view.findViewById(R.id.ch1c);
        ch2c = (ImageView) view.findViewById(R.id.ch2c);
        ch3c = (ImageView) view.findViewById(R.id.ch3c);
        ch4c = (ImageView) view.findViewById(R.id.ch4c);
        ch5c = (ImageView) view.findViewById(R.id.ch5c);
        ch6c = (ImageView) view.findViewById(R.id.ch6c);
        ch7c = (ImageView) view.findViewById(R.id.ch7c);
        ch8c = (ImageView) view.findViewById(R.id.ch8c);
        ch9c = (ImageView) view.findViewById(R.id.ch9c);

        ch1.setOnClickListener(this);
        ch2.setOnClickListener(this);
        ch3.setOnClickListener(this);
        ch4.setOnClickListener(this);
        ch5.setOnClickListener(this);
        ch6.setOnClickListener(this);
        ch7.setOnClickListener(this);
        ch8.setOnClickListener(this);
        ch9.setOnClickListener(this);

        addchild = (Button) view.findViewById(R.id.addchild);
        addchild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addChild();
            }
        });

        date = (TextView) view.findViewById(R.id.date);
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
//                dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dpd.show();
            }
        });

        placeofDelivery = (Spinner) view .findViewById(R.id.placeofDelivery);

        List<String> listplaceofDelivery = new ArrayList<String>();
        listplaceofDelivery.add("--Select Options--");

        listplaceofDelivery.add("Hospital/ PHC/CHC/ Private clinic");
        listplaceofDelivery.add("Home");

        ArrayAdapter<String> dataAdapterdecsionVisitDoctorSpinner = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listplaceofDelivery);
        dataAdapterdecsionVisitDoctorSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        placeofDelivery.setAdapter(dataAdapterdecsionVisitDoctorSpinner);


        sexOfchild= (Spinner) view.findViewById(R.id.sexOfchild);
        List<String> listsexOfchild = new ArrayList<String>();
        listsexOfchild.add("--Select Options--");

        listsexOfchild.add("Male");
        listsexOfchild.add("Female");
        listsexOfchild.add("Intersexed");

        ArrayAdapter<String> dataAdaptersexOfchild = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listsexOfchild);
        dataAdaptersexOfchild.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sexOfchild.setAdapter(dataAdaptersexOfchild);


        wasChildBorn= (Spinner) view.findViewById(R.id.wasChildBorn);

        List<String> listwasChildBorn = new ArrayList<String>();
        listwasChildBorn.add("--Select Options--");

        listwasChildBorn.add("Yes");
        listwasChildBorn.add("No");
        listwasChildBorn.add("Intersexed");

        ArrayAdapter<String> dataAdapterwasChildBorn = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listwasChildBorn);
        dataAdapterwasChildBorn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wasChildBorn.setAdapter(dataAdapterwasChildBorn);


        birthBreast= (Spinner) view.findViewById(R.id.birthBreast);

        List<String> listbirthBreast = new ArrayList<String>();

        listbirthBreast.add("--Select Options--");
        listbirthBreast.add("Yes");
        listbirthBreast.add("No");
        listbirthBreast.add("Intersexed");

        ArrayAdapter<String> dataAdapterbirthBreast = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listbirthBreast);
        dataAdapterbirthBreast.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        birthBreast.setAdapter(dataAdapterbirthBreast);


        firstyellowFeed= (Spinner) view.findViewById(R.id.firstyellowFeed);

        List<String> listfirstyellowFeed = new ArrayList<String>();

        listfirstyellowFeed.add("--Select Options--");
        listfirstyellowFeed.add("Yes");
        listfirstyellowFeed.add("No");
        listfirstyellowFeed.add("Intersexed");

        ArrayAdapter<String> dataAdapterfirstyellowFeed = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listfirstyellowFeed);
        dataAdapterfirstyellowFeed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        firstyellowFeed.setAdapter(dataAdapterfirstyellowFeed);






        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ch1:
                hideShow();
                ch1c.setVisibility(View.VISIBLE);
                break;
            case R.id.ch2:
                hideShow();
                ch2c.setVisibility(View.VISIBLE);
                break;
            case R.id.ch3:
                hideShow();
                ch3c.setVisibility(View.VISIBLE);
                break;
            case R.id.ch4:
                hideShow();
                ch4c.setVisibility(View.VISIBLE);
                break;
            case R.id.ch5:
                hideShow();
                ch5c.setVisibility(View.VISIBLE);
                break;
            case R.id.ch6:
                hideShow();
                ch6c.setVisibility(View.VISIBLE);
                break;
            case R.id.ch7:
                hideShow();
                ch7c.setVisibility(View.VISIBLE);
                break;
            case R.id.ch8:
                hideShow();
                ch8c.setVisibility(View.VISIBLE);
                break;
            case R.id.ch9:
                hideShow();
                ch9c.setVisibility(View.VISIBLE);
                break;

        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void addChild(){

        if(c1.getVisibility()==View.VISIBLE && c2.getVisibility()==View.VISIBLE && c3.getVisibility()==View.VISIBLE
                && c4.getVisibility()==View.VISIBLE && c5.getVisibility()==View.VISIBLE && c6.getVisibility()==View.VISIBLE
                && c7.getVisibility()==View.VISIBLE && c8.getVisibility()==View.VISIBLE && c9.getVisibility()==View.VISIBLE){
            Toast.makeText(getActivity(), "Cannot add more child", Toast.LENGTH_SHORT).show();
        }
        else if(c1.getVisibility()==View.VISIBLE && c2.getVisibility()==View.VISIBLE && c3.getVisibility()==View.VISIBLE
                && c4.getVisibility()==View.VISIBLE && c5.getVisibility()==View.VISIBLE && c6.getVisibility()==View.VISIBLE
                && c7.getVisibility()==View.VISIBLE && c8.getVisibility()==View.VISIBLE){
            c9.setVisibility(View.VISIBLE);
            aChi();
            ch9c.setVisibility(View.VISIBLE);
        }
        else if(c1.getVisibility()==View.VISIBLE && c2.getVisibility()==View.VISIBLE && c3.getVisibility()==View.VISIBLE
                && c4.getVisibility()==View.VISIBLE && c5.getVisibility()==View.VISIBLE && c6.getVisibility()==View.VISIBLE
                && c7.getVisibility()==View.VISIBLE){
            c8.setVisibility(View.VISIBLE);
            aChi();
            ch8c.setVisibility(View.VISIBLE);
        }
        else if(c1.getVisibility()==View.VISIBLE && c2.getVisibility()==View.VISIBLE && c3.getVisibility()==View.VISIBLE
                && c4.getVisibility()==View.VISIBLE && c5.getVisibility()==View.VISIBLE && c6.getVisibility()==View.VISIBLE){
            c7.setVisibility(View.VISIBLE);
            aChi();
            ch7c.setVisibility(View.VISIBLE);
        }
        else if(c1.getVisibility()==View.VISIBLE && c2.getVisibility()==View.VISIBLE && c3.getVisibility()==View.VISIBLE
                && c4.getVisibility()==View.VISIBLE && c5.getVisibility()==View.VISIBLE){
            c6.setVisibility(View.VISIBLE);
            aChi();
            ch6c.setVisibility(View.VISIBLE);
        }
        else if(c1.getVisibility()==View.VISIBLE && c2.getVisibility()==View.VISIBLE && c3.getVisibility()==View.VISIBLE
                && c4.getVisibility()==View.VISIBLE){
            c5.setVisibility(View.VISIBLE);
            aChi();
            ch5c.setVisibility(View.VISIBLE);
        }
        else if(c1.getVisibility()==View.VISIBLE && c2.getVisibility()==View.VISIBLE && c3.getVisibility()==View.VISIBLE){
            c4.setVisibility(View.VISIBLE);
            aChi();
            ch4c.setVisibility(View.VISIBLE);
        }
        else  if(c1.getVisibility()==View.VISIBLE && c2.getVisibility()==View.VISIBLE){
            c3.setVisibility(View.VISIBLE);
            aChi();
            ch3c.setVisibility(View.VISIBLE);
        }
        else if(c1.getVisibility()==View.VISIBLE){
            c2.setVisibility(View.VISIBLE);
            aChi();
            ch2c.setVisibility(View.VISIBLE);
        }

    }

    public void hideShow(){

        ch1.setVisibility(View.VISIBLE);
        ch1c.setVisibility(View.GONE);
        ch2.setVisibility(View.VISIBLE);
        ch2c.setVisibility(View.GONE);
        ch3.setVisibility(View.VISIBLE);
        ch3c.setVisibility(View.GONE);
        ch4.setVisibility(View.VISIBLE);
        ch4c.setVisibility(View.GONE);
        ch5.setVisibility(View.VISIBLE);
        ch5c.setVisibility(View.GONE);
        ch6.setVisibility(View.VISIBLE);
        ch6c.setVisibility(View.GONE);
        ch7.setVisibility(View.VISIBLE);
        ch7c.setVisibility(View.GONE);
        ch8.setVisibility(View.VISIBLE);
        ch8c.setVisibility(View.GONE);
        ch9.setVisibility(View.VISIBLE);
        ch9c.setVisibility(View.GONE);

    }
    public void aChi(){

        ch1.setVisibility(View.VISIBLE);
        ch1c.setVisibility(View.GONE);
        ch2.setVisibility(View.VISIBLE);
        ch2c.setVisibility(View.GONE);
        ch3.setVisibility(View.VISIBLE);
        ch3c.setVisibility(View.GONE);
        ch4.setVisibility(View.VISIBLE);
        ch4c.setVisibility(View.GONE);
        ch5.setVisibility(View.VISIBLE);
        ch5c.setVisibility(View.GONE);
        ch6.setVisibility(View.VISIBLE);
        ch6c.setVisibility(View.GONE);
        ch7.setVisibility(View.VISIBLE);
        ch7c.setVisibility(View.GONE);
        ch8.setVisibility(View.VISIBLE);
        ch8c.setVisibility(View.GONE);
        ch9.setVisibility(View.VISIBLE);
        ch9c.setVisibility(View.GONE);


    }


}
