package rjornelas.tutorial.jokerappdev.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import rjornelas.tutorial.jokerappdev.R
import rjornelas.tutorial.jokerappdev.model.Joke
import rjornelas.tutorial.jokerappdev.presentation.JokeDayPresenter
import rjornelas.tutorial.jokerappdev.presentation.JokePresenter

class JokeDayFragment : Fragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView
    private lateinit var imageView: ImageView
    private lateinit var jokePresenter: JokeDayPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        jokePresenter = JokeDayPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_joke_day, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.findViewById<Toolbar>(R.id.toolbar)?.title = getString(R.string.menu_joke_day)
        progressBar = view.findViewById(R.id.progress_bar)
        textView = view.findViewById(R.id.tv_joke)
        imageView = view.findViewById(R.id.img_joke)
        jokePresenter.findRandom()
    }

    fun showJoke(joke: Joke) {
        textView.text = joke.text
        Picasso.get().load(joke.iconUrl).into(imageView)
    }

    fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    fun showFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}