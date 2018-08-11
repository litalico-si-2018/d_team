package jp.summerintern.sushi.sushi

import android.content.Context
import android.widget.ListView
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter

class ProbsListView @JvmOverloads constructor(context: Context, resource: Int, items :List<Problem>)
    : ArrayAdapter<Problem>(context, resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return super.getView(position, convertView, parent)
    }
}