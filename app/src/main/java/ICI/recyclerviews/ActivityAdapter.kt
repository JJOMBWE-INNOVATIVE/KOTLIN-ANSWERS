package ICI.recyclerviews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ActivityAdapter(var context:Context, var list: ArrayList<ActivityModel>): RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder>() {


    class ActivityViewHolder(itemView: View):ViewHolder(itemView){

         var images = itemView.findViewById<ImageView>(R.id.image)
        var names = itemView.findViewById<TextView>(R.id.name)
        var time = itemView.findViewById<TextView>(R.id.time)
        var digit = itemView.findViewById<TextView>(R.id.digit)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.newlist,null,false)
        return ActivityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        holder.images.setImageResource(list[position].image)
        holder.names.text = list[position].activityName
        holder.time.text = list[position].activityTime
        holder.digit.text = list[position].activityDigit
    }

}