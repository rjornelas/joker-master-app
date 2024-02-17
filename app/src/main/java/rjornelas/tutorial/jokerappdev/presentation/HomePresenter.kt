package rjornelas.tutorial.jokerappdev.presentation

import android.os.Handler
import android.os.Looper
import rjornelas.tutorial.jokerappdev.HomeFragment
import rjornelas.tutorial.jokerappdev.model.Category
import rjornelas.tutorial.jokerappdev.view.CategoryItem

class HomePresenter(
    private val view: HomeFragment
) {

    fun findAllCategories() {
        view.showProgress()
        fakeRequest()
    }

    private fun onSuccess(response: List<Category>) {
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

    private fun onComplete(){
        view.hideProgress()
    }

    private fun fakeRequest() {
        Handler(Looper.getMainLooper()).postDelayed({
            val response = arrayListOf(
                Category("Categoria 1", 0xFFFF0000)
            )
            onSuccess(response)
            onComplete()
        }, 2000)
    }
}