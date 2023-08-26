package ICI.recyclerviews.data

import ICI.recyclerviews.R
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ActivityAdapter(var context:Context, var list: ArrayList<ActivityModel>): RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder>() {


    class ActivityViewHolder(itemView: View):ViewHolder(itemView){

         var images = itemView.findViewById<ImageView>(R.id.DescriptionImage)
        var names = itemView.findViewById<TextView>(R.id.name)
        var time = itemView.findViewById<TextView>(R.id.time)
        var digit = itemView.findViewById<TextView>(R.id.digit)
        val myCard = itemView.findViewById<CardView>(R.id.Card)
        val myFloat = itemView.findViewById<ImageView>(R.id.Float)


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

        holder.myCard.setOnClickListener {
            val intent = (Intent(context,DetailsActivity::class.java))
            intent.putExtra("activityImage",list[position].image)
            intent.putExtra("activityName",list[position].activityName)
            intent.putExtra("activityTime",list[position].activityTime)
            intent.putExtra("activityDigit",list[position].activityDigit)
            intent.putExtra("activityDescription",list[position].activityDescription)
            intent.putExtra("activityStatus",list[position].activityStatus)
            //intent.putExtra("activityStatus",list[position].activityStatus)
            intent.putExtra("activityStatus",list[position].activityStatus)
            context.startActivity(intent)
        }

        holder.myFloat.setOnClickListener {
            val floatIntent = (Intent(context,FloatAction::class.java))
            context.startActivity(floatIntent)
        }

    }

}