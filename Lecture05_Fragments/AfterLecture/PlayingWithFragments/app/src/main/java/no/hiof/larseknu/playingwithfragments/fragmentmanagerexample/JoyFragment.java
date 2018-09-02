package no.hiof.larseknu.playingwithfragments.fragmentmanagerexample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import no.hiof.larseknu.playingwithfragments.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class JoyFragment extends Fragment {


    public JoyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_joy, container, false);
    }

}
