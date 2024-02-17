package rjornelas.tutorial.jokerappdev.presentation

import android.graphics.Color
import android.widget.Toast
import rjornelas.tutorial.jokerappdev.HomeFragment
import rjornelas.tutorial.jokerappdev.data.CategoryRemoteDataSource
import rjornelas.tutorial.jokerappdev.data.ListCategoryCallBack
import rjornelas.tutorial.jokerappdev.model.Category
import rjornelas.tutorial.jokerappdev.view.CategoryItem

class HomePresenter(
    private val view: HomeFragment,
    private val dataSource: CategoryRemoteDataSource
) : ListCategoryCallBack {

    fun findAllCategories() {
        view.showProgress()
        dataSource.findAllCategories(this)
    }

    override fun onSuccess(response: List<String>) {
        val start = 40
        val end = 190
        val diff = (end - start) / response.size

        val categories = response.mapIndexed { index, name ->
            val hsv = floatArrayOf(
                start + (diff * index).toFloat(),
                100.0f,
                100.0f
            )
            Category(name, Color.HSVToColor(hsv).toLong())
        }

        view.showCategories(categories)
    }

    override fun onError(error: String) {
        view.showError(error)
    }

    override fun onComplete() {
        view.hideProgress()
    }
}