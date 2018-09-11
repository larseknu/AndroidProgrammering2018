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
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setAdapter(new AnimalRecyclerAdapter(this, Animal.getData()));

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
                LinearLayoutManager linearLayoutManagerHorizontal = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(linearLayoutManagerHorizontal);
                return true;
            case R.id.linearViewVertical:
                LinearLayoutManager linearLayoutManagerVertical = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(linearLayoutManagerVertical);
                return true;
            case R.id.gridView:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
                recyclerView.setLayoutManager(gridLayoutManager);
                return true;
            case R.id.staggeredViewHorizontal:
                StaggeredGridLayoutManager staggeredGridLayoutManagerHorizontal = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(staggeredGridLayoutManagerHorizontal);
                return true;
            case R.id.staggeredViewVertical:
                StaggeredGridLayoutManager staggeredGridLayoutManagerVertical = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(staggeredGridLayoutManagerVertical);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
