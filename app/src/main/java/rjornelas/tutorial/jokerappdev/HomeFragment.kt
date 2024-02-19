package rjornelas.tutorial.jokerappdev

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupieAdapter
import rjornelas.tutorial.jokerappdev.data.CategoryRemoteDataSource
import rjornelas.tutorial.jokerappdev.model.Category
import rjornelas.tutorial.jokerappdev.presentation.HomePresenter
import rjornelas.tutorial.jokerappdev.view.CategoryItem
import rjornelas.tutorial.jokerappdev.view.JokeFragment.Companion.CATEGORY_KEY

class HomeFragment : Fragment() {

    private lateinit var presenter: HomePresenter
    private val adapter = GroupieAdapter()
    private lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataSource = CategoryRemoteDataSource()
        presenter = HomePresenter(this, dataSource)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progress_bar)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_main)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        if(adapter.itemCount == 0){
            presenter.findAllCategories()
        }

        recyclerView.adapter = adapter

        adapter.setOnItemClickListener{
                item, _ ->
            val bundle = Bundle()
            val categoryName = (item as CategoryItem).category.name
            bundle.putString(CATEGORY_KEY, categoryName)
            findNavController().navigate(R.id.action_nav_home_to_nav_joke, bundle)
        }
    }

    fun showCategories(response: List<Category>) {
        val categories = response.map { CategoryItem(it) }
        adapter.addAll(categories)
        adapter.notifyDataSetChanged()
    }

    fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    fun showError(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }
}