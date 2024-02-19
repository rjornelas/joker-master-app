package rjornelas.tutorial.jokerappdev.presentation

import rjornelas.tutorial.jokerappdev.data.JokeCallBack
import rjornelas.tutorial.jokerappdev.data.JokeDayRemoteDataSource
import rjornelas.tutorial.jokerappdev.data.JokeRemoteDataSource
import rjornelas.tutorial.jokerappdev.model.Joke
import rjornelas.tutorial.jokerappdev.view.JokeDayFragment
import rjornelas.tutorial.jokerappdev.view.JokeFragment

class JokeDayPresenter(
    private val view: JokeDayFragment,
    private val dataSource: JokeDayRemoteDataSource = JokeDayRemoteDataSource()
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

    fun findRandom(){
        view.showProgress()
        dataSource.findRandom(this)
    }

}