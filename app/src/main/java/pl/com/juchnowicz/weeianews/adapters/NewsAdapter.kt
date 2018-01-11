package pl.com.juchnowicz.weeianews.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.news_card.view.*
import pl.com.juchnowicz.weeianews.R
import pl.com.juchnowicz.weeianews.models.APIResponse

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>(){

    private var newsList: ArrayList<APIResponse.News> = ArrayList()

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = newsList[position]
        holder.title.text = news.title
        holder.content.text = news.content
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.news_card, parent, false))
    }

    fun updateEvents(newsList: List<APIResponse.News>){
        this.newsList.clear()
        this.newsList.addAll(newsList)
        notifyDataSetChanged()
    }
    inner class ViewHolder(newsView: View) : RecyclerView.ViewHolder(newsView){
        val title = newsView.news_card_title
        val content = newsView.news_card_content
    }
}