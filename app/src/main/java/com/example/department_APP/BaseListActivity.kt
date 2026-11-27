package com.example.department_APP

import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.widget.SearchView
import com.example.department_APP.adapters.ItemAdapter
import com.example.department_APP.adapters.ListItem
import com.example.department_APP.database.DatabaseHelper
import com.example.department_APP.utils.AppPreferences

// Common logic of the faculty list and the department list:
// display the items, search, and switch between ListView and GridView
abstract class BaseListActivity : BaseActivity() {

    protected lateinit var database: DatabaseHelper
    private lateinit var preferences: AppPreferences

    private lateinit var searchView: SearchView
    private lateinit var toggleButton: ImageButton
    private lateinit var listView: ListView
    private lateinit var gridView: GridView

    private lateinit var listAdapter: ItemAdapter
    private lateinit var gridAdapter: ItemAdapter

    private var allItems: List<ListItem> = emptyList()

    // Each child screen says what to show and what a click does
    protected abstract fun loadItems(): List<ListItem>
    protected abstract fun onItemSelected(id: Int)
    protected open fun onItemLongPressed(id: Int) {}

    // Must be called after setContentView()
    protected fun setupList() {
        database = DatabaseHelper(this)
        preferences = AppPreferences(this)

        searchView = findViewById(R.id.searchView)
        toggleButton = findViewById(R.id.btnToggleView)
        listView = findViewById(R.id.lvItems)
        gridView = findViewById(R.id.gvItems)

        listAdapter = ItemAdapter(this, R.layout.item_list)
        gridAdapter = ItemAdapter(this, R.layout.item_grid)
        listView.adapter = listAdapter
        gridView.adapter = gridAdapter

        val click = AdapterView.OnItemClickListener { _, _, _, id ->
            onItemSelected(id.toInt())
        }
        val longClick = AdapterView.OnItemLongClickListener { _, _, _, id ->
            onItemLongPressed(id.toInt())
            true
        }
        listView.onItemClickListener = click
        gridView.onItemClickListener = click
        listView.onItemLongClickListener = longClick
        gridView.onItemLongClickListener = longClick

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText.orEmpty())
                return true
            }
        })

        toggleButton.setOnClickListener {
            preferences.isGridView = !preferences.isGridView
            applyDisplayStyle()
        }
    }

    // Reload here so that changes made in Settings are visible when we come back
    override fun onResume() {
        super.onResume()
        allItems = loadItems()
        filter(searchView.query.toString())
        applyDisplayStyle()
    }

    override fun onDestroy() {
        database.close()
        super.onDestroy()
    }

    private fun filter(query: String) {
        val wanted = normalize(query.trim())
        val result = allItems.filter { normalize(it.searchText).contains(wanted) }
        listAdapter.submitList(result)
        gridAdapter.submitList(result)
    }

    // Ignores upper case, and the hamza on the Arabic alef
    private fun normalize(text: String): String =
        text.lowercase()
            .replace('أ', 'ا')
            .replace('إ', 'ا')
            .replace('آ', 'ا')

    private fun applyDisplayStyle() {
        val grid = preferences.isGridView
        listView.visibility = if (grid) View.GONE else View.VISIBLE
        gridView.visibility = if (grid) View.VISIBLE else View.GONE
        // The icon shows the style you get by pressing the button
        toggleButton.setImageResource(if (grid) R.drawable.ic_list else R.drawable.ic_grid)
    }
}
