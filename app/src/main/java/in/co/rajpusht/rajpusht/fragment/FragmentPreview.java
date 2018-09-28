package in.co.rajpusht.rajpusht.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.co.rajpusht.rajpusht.R;

/**
 * Created by LENOVO on 12-Sep-18.
 */

public class FragmentPreview extends Fragment {
    public FragmentPreview() {
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
        return inflater.inflate(R.layout.cash_fragment_preview, container, false);
    }
}
