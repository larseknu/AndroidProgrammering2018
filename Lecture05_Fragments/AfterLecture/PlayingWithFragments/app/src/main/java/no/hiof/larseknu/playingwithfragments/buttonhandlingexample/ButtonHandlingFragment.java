package no.hiof.larseknu.playingwithfragments.buttonhandlingexample;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import no.hiof.larseknu.playingwithfragments.R;

public class ButtonHandlingFragment extends Fragment {
    private int clickCount = 0;
    private TextView textViewClickCountNumber = null;

    public ButtonHandlingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_button_handling, container, false);

        textViewClickCountNumber = view.findViewById(R.id.textViewClickCountNumber);
        Button clickMeButton = view.findViewById(R.id.clickMeButton);

        clickMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textViewClickCountNumber != null)
                    textViewClickCountNumber.setText(String.format(Locale.ENGLISH, "%d", ++clickCount));
            }
        });

        return view;
    }
}
