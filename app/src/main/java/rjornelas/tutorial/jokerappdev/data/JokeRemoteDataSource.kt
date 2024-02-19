package rjornelas.tutorial.jokerappdev.data

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rjornelas.tutorial.jokerappdev.model.Joke
import java.lang.RuntimeException

class JokeRemoteDataSource {
    fun findByCategory(categoryName: String, callBack: JokeCallBack) {
        HTTPClient.retrofit()
            .create(ChuckNorrisAPI::class.java)
            .findBy(categoryName)
            .enqueue(object : Callback<Joke> {
                override fun onResponse(
                    call: Call<Joke>,
                    response: Response<Joke>
                ) {
                    if(response.isSuccessful){
                        val joke = response.body()
                        callBack.onSuccess(joke ?: throw RuntimeException("Joke not found"))
                    }else{
                        val error = response.errorBody()?.string()
                        callBack.onError(error ?: "Unknown error")
                    }
                    callBack.onComplete()
                }
                override fun onFailure(call: Call<Joke>, t: Throwable) {
                    callBack.onError(t.message ?: "Internal error")
                    callBack.onComplete()
                }
            })
    }
}