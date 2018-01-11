package pl.com.juchnowicz.weeianews.fragments

import pl.com.juchnowicz.weeianews.models.APIResponse

interface Callbacks {
    fun showNewsList()
    fun showNews(news: APIResponse.News)
}

interface NewsSelectedListener{
    fun newsSelected(news: APIResponse.News)
}
