package rjornelas.tutorial.jokerappdev.data

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRemoteDataSource {
    fun findAllCategories(callBack: ListCategoryCallBack) {
        HTTPClient.retrofit()
            .create(ChuckNorrisAPI::class.java)
            .findAllCategories(HTTPClient.API_KEY)
            .enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>,
                    response: Response<List<String>>
                ) {
                    if(response.isSuccessful){
                        val categories = response.body()
                        callBack.onSuccess(categories ?: emptyList())
                    }else{
                        val error = response.errorBody()?.string()
                        callBack.onError(error ?: "Unknown error")
                    }
                    callBack.onComplete()
                }
                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    callBack.onError(t.message ?: "Internal error")
                    callBack.onComplete()
                }
            })
    }
}