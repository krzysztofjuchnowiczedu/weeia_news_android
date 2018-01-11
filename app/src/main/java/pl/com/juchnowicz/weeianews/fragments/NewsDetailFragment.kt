package pl.com.juchnowicz.weeianews.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_news_detail.*

import pl.com.juchnowicz.weeianews.R
import pl.com.juchnowicz.weeianews.models.APIResponse


class NewsDetailFragment : Fragment() {

    var newsToDisplay: APIResponse.News? = null
    var delegate: Callbacks? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_news_detail, container, false)
    }

    override fun onResume() {
        super.onResume()
        if (newsToDisplay != null){
            news_detail_title?.text = newsToDisplay!!.title
            news_detail_content?.text = newsToDisplay!!.content
        }
    }

    fun setNews(news: APIResponse.News){
        newsToDisplay = news
    }

}
