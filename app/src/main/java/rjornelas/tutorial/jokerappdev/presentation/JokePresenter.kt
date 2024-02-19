package rjornelas.tutorial.jokerappdev.presentation

import rjornelas.tutorial.jokerappdev.data.JokeCallBack
import rjornelas.tutorial.jokerappdev.data.JokeRemoteDataSource
import rjornelas.tutorial.jokerappdev.model.Joke
import rjornelas.tutorial.jokerappdev.view.JokeFragment

class JokePresenter(
    private val view: JokeFragment,
    private val dataSource: JokeRemoteDataSource = JokeRemoteDataSource()
) : JokeCallBack{

    override fun onSuccess(response: Joke) {
        view.showJoke(response)
    }

    override fun onError(error: String) {
        view.showFailure(error)
    }

    override fun onComplete() {
        view.hideProgress()
    }

    fun findByCategory(categoryName: String){
        view.showProgress()
        dataSource.findByCategory(categoryName, this)
    }

}