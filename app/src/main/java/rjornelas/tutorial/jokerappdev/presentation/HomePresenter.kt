package rjornelas.tutorial.jokerappdev.presentation

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

    override fun onSuccess(response: List<Category>) {
//        -> Modo mais "manual"
//        val categories = mutableListOf<CategoryItem>()
//
//        for (category in response) {
//            categories.add(CategoryItem(category))
//        }
//        view.showCategories(categories)

//        -> Utilizando map
//        val categories = response.map {
//            category -> CategoryItem(category)
//        }
//        view.showCategories(categories)

        val categories = response.map {CategoryItem(it)}
        view.showCategories(categories)
    }

    override fun onComplete() {
        view.hideProgress()
    }
}