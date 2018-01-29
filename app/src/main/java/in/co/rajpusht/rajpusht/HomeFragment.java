package in.co.rajpusht.rajpusht;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        Button pw = (Button) v.findViewById(R.id.pww1);
        pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PregnantWomenForm.class);
                i.putExtra("button","PW1");
                i.putExtra("name","Bimala Agarwal");
                startActivity(i);
            }
        });

        Button pw2 = (Button) v.findViewById(R.id.pw2);
        pw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getActivity(), PregnantWomenForm.class);
                i2.putExtra("button","PW2");
                i2.putExtra("name","Sonam Agarwal");
                startActivity(i2);
            }
        });

        return v;
    }

}
