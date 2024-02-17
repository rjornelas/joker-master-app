package rjornelas.tutorial.jokerappdev.presentation

import android.widget.Toast
import rjornelas.tutorial.jokerappdev.HomeFragment
import rjornelas.tutorial.jokerappdev.data.CategoryRemoteDataSource
import rjornelas.tutorial.jokerappdev.data.ListCategoryCallBack
import rjornelas.tutorial.jokerappdev.model.Category
import rjornelas.tutorial.jokerappdev.view.CategoryItem

class HomePresenter(
    private val view: HomeFragment,
    private val dataSource: CategoryRemoteDataSource
): ListCategoryCallBack {

    fun findAllCategories() {
        view.showProgress()
        dataSource.findAllCategories(this)
    }

    override fun onSuccess(response: List<String>) {
        val categories = response.map {Category(it, 0xFFFF0000)}
        view.showCategories(categories)
    }

    override fun onError(error: String) {
        view.showError(error)
    }

    override fun onComplete() {
        view.hideProgress()
    }
}