package com.example.department_APP.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.department_APP.R

// One row (ListView) or one cell (GridView): a faculty or a department
data class ListItem(
    val id: Int,
    val name: String,
    val description: String,
    val searchText: String
)

// The same adapter serves both views: only the layout changes
// (R.layout.item_list or R.layout.item_grid)
class ItemAdapter(
    private val context: Context,
    private val layoutId: Int
) : BaseAdapter() {

    private var items: List<ListItem> = emptyList()

    fun submitList(newItems: List<ListItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): ListItem = items[position]

    override fun getItemId(position: Int): Long = items[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView
            ?: LayoutInflater.from(context).inflate(layoutId, parent, false)
        val item = getItem(position)

        view.findViewById<TextView>(R.id.tvItemName).text = item.name
        // item_grid.xml has no description, so this can be null
        view.findViewById<TextView?>(R.id.tvItemDescription)?.text = item.description

        return view
    }
}
