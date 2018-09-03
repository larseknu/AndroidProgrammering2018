package no.hiof.larseknu.playingwithfragments.buttonhandlingexample;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import no.hiof.larseknu.playingwithfragments.R;

public class ButtonHandlingFragment extends Fragment {

    public ButtonHandlingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_button_handling, container, false);

        return view;
    }
}
