package com.p1.loginlayout.recyclers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.p1.loginlayout.DB.ChampionsDBHelper
import com.p1.loginlayout.R
import com.p1.loginlayout.fragments.DetailFragment
import com.p1.loginlayout.model.Champion
// Adapter for the RecyclerView
class RecyclerViewAdapter(
    llistat: MutableList<Champion>, context: Context?, dbHelper: ChampionsDBHelper
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private val db = dbHelper
    private val championsData = llistat

    // onCreateViewHolder() is called when the RecyclerView needs a new ViewHolder of the given type to represent an item.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        )
    }

    // onBindViewHolder() is called by the RecyclerView to display the data at the specified position.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentChampion = championsData[position]
        holder.itemView.apply {
            findViewById<TextView>(R.id.itemName).text = currentChampion.name
            findViewById<TextView>(R.id.itemDmg).text =
                context.getString(R.string.champion_dmg, currentChampion.dmg)
            findViewById<TextView>(R.id.itemRelease).text =
                context.getString(R.string.champion_release, currentChampion.release)
            findViewById<ImageView>(R.id.itemImage).setImageResource(currentChampion.splash)

            setOnClickListener() {
                val activity = context as AppCompatActivity
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, DetailFragment(championsData[position]))
                    .addToBackStack(null).commit()
            }

            setOnLongClickListener() {
                val builder = AlertDialog.Builder(context)
                val whenDeleted = AlertDialog.Builder(context)
                builder.setTitle("Are you sure you want to delete ${championsData[position].name} from the database?")
                builder.setPositiveButton("Yes") { _, _ ->
                    db.deleteById(championsData[position].id!!)
                    whenDeleted.setTitle("${championsData[position].name} has been deleted")
                    championsData.removeAt(position)
                    whenDeleted.setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                        notifyItemRemoved(position)
                        notifyItemChanged(position, championsData.size)

                    }
                    whenDeleted.create().show()

                }
                builder.setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
                builder.create().show()
                true
            }
        }
    }

    // getItemCount() is called by the RecyclerView to get the size of the data set.
    override fun getItemCount(): Int {
        return championsData.size
    }

    fun removeAt(position: Int) {
        db.deleteById(championsData[position].id!!)
        championsData.removeAt(position)
        notifyItemRemoved(position)
        notifyItemChanged(position, championsData.size)

    }

    // ViewHolder is a class that holds the views for each item of the RecyclerView
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val champName: TextView = itemView.findViewById(R.id.itemName)
        val champDmg: TextView = itemView.findViewById(R.id.itemDmg)
        val champRelease: TextView = itemView.findViewById(R.id.itemRelease)
        val champImage: ImageView = itemView.findViewById(R.id.itemImage)


    }


}