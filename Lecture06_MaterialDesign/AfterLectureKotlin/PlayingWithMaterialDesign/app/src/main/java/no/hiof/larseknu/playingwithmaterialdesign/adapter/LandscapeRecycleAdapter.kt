package no.hiof.larseknu.playingwithmaterialdesign.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.landscape_list_item.view.*
import no.hiof.larseknu.playingwithmaterialdesign.R
import no.hiof.larseknu.playingwithmaterialdesign.model.Landscape

class LandscapeRecycleAdapter(private val landscapeList : ArrayList<Landscape>, context: Context) : RecyclerView.Adapter<LandscapeViewHolder>() {
    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): LandscapeViewHolder {
        val view: View = layoutInflater.inflate(R.layout.landscape_list_item, parent, false)

        return LandscapeViewHolder(view)
    }

    override fun getItemCount(): Int {
       return landscapeList.size
    }

    override fun onBindViewHolder(viewHolder: LandscapeViewHolder, position: Int) {
        val landscape: Landscape = landscapeList[position]

        viewHolder.setLandscape(landscape)
    }

}

class LandscapeViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    private val landscapeImageView: ImageView = view.thumbnailImageView
    private val titleTextView: TextView = view.titleTextView
    private val descTextView: TextView = view.descriptionTextView

    fun setLandscape(landscape: Landscape) {
        landscapeImageView.setImageResource(landscape.imageID)
        titleTextView.text = landscape.title
        descTextView.text = landscape.description
    }
}