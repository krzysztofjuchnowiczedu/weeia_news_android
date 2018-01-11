package pl.com.juchnowicz.weeianews.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_news_list.*

import pl.com.juchnowicz.weeianews.R
import pl.com.juchnowicz.weeianews.adapters.NewsAdapter
import pl.com.juchnowicz.weeianews.models.APIResponse
import pl.com.juchnowicz.weeianews.services.APIService


class NewsListFragment : Fragment(), NewsSelectedListener {

    var disposable: Disposable? = null
    var newsAdapter: NewsAdapter? = null
    var delegate: Callbacks? = null

    val apiService by lazy {
        APIService.create()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onResume() {
        super.onResume()
        initializeNewsList()
        disposable = apiService.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result ->
                    newsAdapter?.updateEvents(result)
                }, {error ->
                    System.out.println("Error" + error.localizedMessage)
                })
    }

    fun initializeNewsList(){
        newsAdapter = NewsAdapter(this)
        news_recycler_view?.setHasFixedSize(true)
        news_recycler_view?.layoutManager = LinearLayoutManager(this.context)
        news_recycler_view?.adapter = newsAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    override fun newsSelected(news: APIResponse.News) {
        delegate?.showNews(news)
    }
}
