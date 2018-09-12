package no.hiof.larseknu.playingwithmaterialdesign

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*
import no.hiof.larseknu.playingwithmaterialdesign.adapter.LandscapeRecycleAdapter
import no.hiof.larseknu.playingwithmaterialdesign.model.Landscape

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar as Toolbar?)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        recyclerView.adapter = LandscapeRecycleAdapter(Landscape.getData(), this)

        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
