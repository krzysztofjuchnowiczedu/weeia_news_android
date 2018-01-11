package pl.com.juchnowicz.weeianews

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.fragment_news_detail.*
import pl.com.juchnowicz.weeianews.fragments.NewsDetailFragment
import pl.com.juchnowicz.weeianews.fragments.NewsListFragment
import pl.com.juchnowicz.weeianews.fragments.Callbacks
import pl.com.juchnowicz.weeianews.models.APIResponse

class MainActivity : AppCompatActivity(), Callbacks {


    private val newsListFragment = NewsListFragment()
    private val newsDetailFragment = NewsDetailFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        newsDetailFragment.delegate = this
        newsListFragment.delegate = this
        showFragment(newsListFragment)
    }

    private fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun showNewsList() {
        showFragment(newsListFragment)
    }

    override fun showNews(news: APIResponse.News) {
        newsDetailFragment.setNews(news)
        showFragment(newsDetailFragment)
    }
}
