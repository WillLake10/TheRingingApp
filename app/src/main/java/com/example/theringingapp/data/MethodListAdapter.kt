package com.example.theringingapp.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.theringingapp.R
import com.example.theringingapp.data.dto.Method
import com.example.theringingapp.getNumOfBellsName
import java.util.*
import kotlin.collections.ArrayList

class MethodListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<MethodListAdapter.MethodViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var methods = emptyList<Method>() // Cached copy of methods
    var methodsFilterList = emptyList<Method>()

    inner class MethodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val methodItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MethodViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return MethodViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MethodViewHolder, position: Int) {
        val current = methods[position]
        holder.methodItemView.text = current.proper + " " + getNumOfBellsName(current.numOfBells)
    }

    internal fun setMethods(methods: List<Method>) {
        this.methods = methods
        notifyDataSetChanged()
    }

    fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    methodsFilterList = methods
                } else {
                    val resultList = ArrayList<Method>()
                    for (row in methods) {
                        if (row.proper.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    methodsFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = methodsFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                methodsFilterList = results?.values as ArrayList<Method>
                notifyDataSetChanged()
            }

        }
    }

    override fun getItemCount() = methods.size
}