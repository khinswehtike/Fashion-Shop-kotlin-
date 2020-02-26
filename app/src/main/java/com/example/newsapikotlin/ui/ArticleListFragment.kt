package com.example.newsapikotlin.ui


import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.newsapikotlin.R
import com.example.newsapikotlin.model.ArticalResult
import com.example.newsapikotlin.model.Article
import com.example.newsapikotlin.ui.adapter.ArticleListAdapter
import com.example.newsapikotlin.viewmodel.ArticleViewModel
import com.example.newsapikotlin.viewmodel.SelectedArticleViewModel
import kotlinx.android.synthetic.main.fragment_article_list.*

/**
 * A simple [Fragment] subclass.
 */
class ArticleListFragment : Fragment(),ArticleListAdapter.ClickListener {
    private lateinit var articleListAdapter:ArticleListAdapter
    private  lateinit var viewManager:RecyclerView.LayoutManager
    lateinit var articleViewModel: ArticleViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager=LinearLayoutManager(context)
        articleListAdapter= ArticleListAdapter()
        recyclerview.adapter=articleListAdapter
        recyclerview.layoutManager=viewManager
        observeViewModel()

        //'this' is clicked item
        articleListAdapter.setOnClickListener(this)
    }
    fun observeViewModel(){
         articleViewModel= ViewModelProviders
            .of(this)
            .get(ArticleViewModel::class.java)
        articleViewModel.getResults().observe(this, Observer<ArticalResult> { result->
            recyclerview.visibility=View.VISIBLE
            articleListAdapter.updateList(result.articles)

        })
        articleViewModel.getErrors().observe(this, Observer <Boolean>{ isError->
            if(isError) {
                txtError.visibility = View.VISIBLE
                recyclerview.visibility = View.GONE
            }else{
                txtError.visibility=View.GONE
            }
        })
        articleViewModel.getLoading().observe(this, Observer<Boolean> {isLoading->
            loadingView.visibility=(if(isLoading)
                    View.VISIBLE else View.INVISIBLE)
            if(isLoading){
                txtError.visibility=View.GONE
                recyclerview.visibility=View.GONE
            }
        })
    }

    override fun onResume() {
        super.onResume()
        articleViewModel.loadResults()
    }
    override fun onClick(article: Article) {
     if (!TextUtils.isEmpty(article.url)){
         val selectedArticleViewModel : SelectedArticleViewModel = ViewModelProviders
             .of(activity!!)
             .get(SelectedArticleViewModel::class.java)
         selectedArticleViewModel.selectedArticle(article)
         activity!!.supportFragmentManager
             .beginTransaction()
             .replace(R.id.screenContainer,DetailsFragment())
             .addToBackStack(null)
             .commit()
     }
    }

}
