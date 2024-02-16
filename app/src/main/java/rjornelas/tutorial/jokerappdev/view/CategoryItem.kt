package rjornelas.tutorial.jokerappdev.view

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import rjornelas.tutorial.jokerappdev.R
import rjornelas.tutorial.jokerappdev.model.Category

class CategoryItem(private val category: Category) : Item<CategoryItem.CategoryViewHolder>() {

    class CategoryViewHolder(view: View) : GroupieViewHolder(view)

    override fun createViewHolder(itemView: View): CategoryViewHolder {
        return CategoryViewHolder(itemView)
    }

    override fun bind(viewHolder: CategoryViewHolder, position: Int) {
        viewHolder.itemView.findViewById<TextView>(R.id.tv_category).text = category.name
        viewHolder.itemView.findViewById<LinearLayout>(R.id.container_category).setBackgroundColor(category.bgColor.toInt())
    }

    override fun getLayout(): Int {
       return R.layout.item_category
    }
}