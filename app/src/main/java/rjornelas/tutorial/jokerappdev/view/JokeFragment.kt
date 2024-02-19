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
import rjornelas.tutorial.jokerappdev.R
import rjornelas.tutorial.jokerappdev.model.Joke
import rjornelas.tutorial.jokerappdev.presentation.JokerPresenter

class JokeFragment : Fragment() {

    companion object {
        const val CATEGORY_KEY = "category"
    }

    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView
    private lateinit var imageView: ImageView
    private lateinit var jokerPresenter: JokerPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        jokerPresenter = JokerPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_joke, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryName = arguments?.getString(CATEGORY_KEY)

        categoryName?.let {
            activity?.findViewById<Toolbar>(R.id.toolbar)?.title = categoryName
            progressBar = view.findViewById(R.id.progress_bar)
            textView = view.findViewById(R.id.tv_joke)
            jokerPresenter.findByCategory(categoryName)
            view.findViewById<FloatingActionButton>(R.id.fab_refresh).setOnClickListener{
                jokerPresenter.findByCategory(categoryName)
            }
        }
    }

    fun showJoke(joke: Joke){
        textView.text = joke.text
    }

    fun showProgress(){
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgress(){
        progressBar.visibility = View.GONE
    }

    fun showFailure(message: String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}