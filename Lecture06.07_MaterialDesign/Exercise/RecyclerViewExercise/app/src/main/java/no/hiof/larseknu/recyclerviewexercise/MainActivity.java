package no.hiof.larseknu.recyclerviewexercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import no.hiof.larseknu.recyclerviewexercise.adapter.AnimalRecyclerAdapter;
import no.hiof.larseknu.recyclerviewexercise.model.Animal;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpRecycleView();
    }

    private void setUpRecycleView() {
        // TODO MA 1: Get reference to the RecyclerView
        recyclerView = findViewById(R.id.animalRecycleView);

        // TODO MA 2: Set up an AnimalRecyclerAdapter and set it to the RecycleView
        recyclerView.setAdapter(new AnimalRecyclerAdapter(this, Animal.getData()));

        // TODO MA 3: Set a LinearLayoutManager for the RecycleView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_animal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.linearViewHorizontal:
                // MA 4: Change to a horizontal LinearLayout manager
                LinearLayoutManager linearLayoutManagerHorizontal = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(linearLayoutManagerHorizontal);
                return true;
            case R.id.linearViewVertical:
                // TODO MA 5: Change to a vertical LinearLayout manager
                LinearLayoutManager linearLayoutManagerVertical = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(linearLayoutManagerVertical);
                return true;
                // TODO MA 6: Add handling for the other items in the menu and change to the corresponding layout
            case R.id.gridView:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
                recyclerView.setLayoutManager(gridLayoutManager);
                return true;

            case R.id.staggeredViewVertical:
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(staggeredGridLayoutManager);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
