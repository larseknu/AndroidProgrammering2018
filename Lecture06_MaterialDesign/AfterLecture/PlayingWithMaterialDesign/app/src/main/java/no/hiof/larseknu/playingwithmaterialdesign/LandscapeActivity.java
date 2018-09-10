package no.hiof.larseknu.playingwithmaterialdesign;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import no.hiof.larseknu.playingwithmaterialdesign.adapter.LandscapeRecyclerAdapter;
import no.hiof.larseknu.playingwithmaterialdesign.model.Landscape;

public class LandscapeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landscape);

        setUpRecycleView();
    }

    private void setUpRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.landscapeRecycleView);


        recyclerView.setAdapter(new LandscapeRecyclerAdapter(this, Landscape.getData()));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


}
