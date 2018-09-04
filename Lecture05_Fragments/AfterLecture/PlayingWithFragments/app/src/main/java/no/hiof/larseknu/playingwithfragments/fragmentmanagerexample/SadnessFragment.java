package no.hiof.larseknu.playingwithfragments.fragmentmanagerexample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import no.hiof.larseknu.playingwithfragments.R;

public class SadnessFragment extends Fragment {
    private static final String TAG = "SadnessFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sadness, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated");

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart");

        super.onStart();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop");

        super.onStop();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause");

        super.onPause();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");

        super.onResume();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView");

        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");

        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach");

        super.onDetach();
    }
}
