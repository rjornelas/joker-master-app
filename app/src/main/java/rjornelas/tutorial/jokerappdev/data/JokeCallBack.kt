package rjornelas.tutorial.jokerappdev.data

import rjornelas.tutorial.jokerappdev.model.Joke

interface JokeCallBack {
    fun onSuccess(response: Joke)
    fun onError(error: String)
    fun onComplete()
}