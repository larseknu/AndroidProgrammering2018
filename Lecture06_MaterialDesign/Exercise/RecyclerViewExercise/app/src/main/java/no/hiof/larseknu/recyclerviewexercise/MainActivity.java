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

        // TODO MA 2: Set up an AnimalRecyclerAdapter and set it to the RecycleView

        // TODO MA 3: Set a LinearLayoutManager for the RecycleView
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
                // TODO MA 4: Change to a horizontal LinearLayout manager
                return true;
            case R.id.linearViewVertical:
                // TODO MA 5: Change to a vertical LinearLayout manager
                return true;
                // TODO MA 6: Add handling for the other items in the menu and change to the corresponding layout
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
