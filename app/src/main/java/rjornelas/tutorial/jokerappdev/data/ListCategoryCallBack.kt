package rjornelas.tutorial.jokerappdev.data

import rjornelas.tutorial.jokerappdev.model.Category

interface ListCategoryCallBack {
    fun onSuccess(response: List<String>)

    fun onError(error: String)
    fun onComplete()
}