package com.lkorasik.habitclients

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView


class HabitRecycleViewAdapter(private val data: List<HabitDTO>, private val context: Context) :
    BaseAdapter() {
    private var lInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(p0: Int): Any {
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            view = lInflater.inflate(R.layout.row_item, parent, false)
        }

        val item = data[position]

        (view!!.findViewById<View>(R.id.habit_name) as TextView).text = item.title
        (view!!.findViewById<View>(R.id.habit_description) as TextView).text = item.description
        (view!!.findViewById<View>(R.id.habit_priority) as TextView).text = item.priority.value
        (view!!.findViewById<View>(R.id.habit_periodicity) as TextView).text = item.repeats.toString()

        return view
    }
}