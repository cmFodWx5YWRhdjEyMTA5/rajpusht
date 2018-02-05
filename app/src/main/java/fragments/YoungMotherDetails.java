package fragments;

import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import in.co.rajpusht.rajpusht.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link YoungMotherDetails.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link YoungMotherDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class YoungMotherDetails extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    TextView date;
    private int mYear, mMonth, mDay;
    Spinner placeofDelivery,sexOfchild,wasChildBorn,birthBreast,firstyellowFeed;
    EditText nameOfChild,birthWeightChild;

    private OnFragmentInteractionListener mListener;

    public YoungMotherDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment YoungMotherDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static YoungMotherDetails newInstance(String param1, String param2) {
        YoungMotherDetails fragment = new YoungMotherDetails();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
                View view=inflater.inflate(R.layout.fragment_young_mother_details, container, false);

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
}
