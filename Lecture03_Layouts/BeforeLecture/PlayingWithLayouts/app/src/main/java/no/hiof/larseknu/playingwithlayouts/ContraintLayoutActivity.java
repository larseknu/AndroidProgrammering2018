package no.hiof.larseknu.playingwithlayouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ContraintLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_constraint_layout_basics);
        //setContentView(R.layout.activity_constraint_layout_chains);
        //setContentView(R.layout.activity_contraint_layout_view_gone);
    }
}
