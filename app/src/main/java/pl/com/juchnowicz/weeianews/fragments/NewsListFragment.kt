package pl.com.juchnowicz.weeianews.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_news_list.*

import pl.com.juchnowicz.weeianews.R
import pl.com.juchnowicz.weeianews.adapters.NewsAdapter
import pl.com.juchnowicz.weeianews.models.APIResponse
import pl.com.juchnowicz.weeianews.services.APIService


class NewsListFragment : Fragment(), NewsSelectedListener, AdapterView.OnItemSelectedListener {

    var disposable: Disposable? = null
    var newsAdapter: NewsAdapter? = null
    var delegate: Callbacks? = null
    private var newsList: ArrayList<APIResponse.News> = ArrayList()

    val apiService by lazy {
        APIService.create()
    }

    var filterItems = arrayOf("All", "Info", "Reminder", "System")

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
                    newsList.clear()
                    newsList.addAll(result)
                    newsAdapter?.updateEvents(newsList)
                }, {error ->
                    System.out.println("Error" + error.localizedMessage)
                })
        initComboBox()
    }

    private fun initComboBox(){
        val arrayAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, filterItems)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner?.adapter = arrayAdapter
        spinner?.onItemSelectedListener = this
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

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when(p2){
            0 -> {newsAdapter?.updateEvents(newsList)}
            in 1..3 -> {newsAdapter?.updateEvents(newsList.filter { news -> news.type == p2 })}
        }
    }
}
