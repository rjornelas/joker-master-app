package rjornelas.tutorial.jokerappdev.data

import android.os.Handler
import android.os.Looper
import rjornelas.tutorial.jokerappdev.model.Category

class CategoryRemoteDataSource {
    fun findAllCategories(callBack: ListCategoryCallBack){
        Handler(Looper.getMainLooper()).postDelayed({
            val response = arrayListOf(
                Category("Categoria 1", 0xFFFF0000)
            )
            callBack.onSuccess(response)
            callBack.onComplete()
        }, 2000)
    }
}