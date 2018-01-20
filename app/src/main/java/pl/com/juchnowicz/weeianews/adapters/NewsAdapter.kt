package pl.com.juchnowicz.weeianews.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_card.view.*
import pl.com.juchnowicz.weeianews.R
import pl.com.juchnowicz.weeianews.fragments.NewsSelectedListener
import pl.com.juchnowicz.weeianews.models.APIResponse

class NewsAdapter(val listener: NewsSelectedListener, val context: Context) : RecyclerView.Adapter<NewsAdapter.ViewHolder>(){

    private var newsList: ArrayList<APIResponse.News> = ArrayList()

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = newsList[position]
        holder.title.text = news.title
        holder.content.text = news.content
        if(news.imageURL != null){
            Picasso.with(context).load(news.imageURL!!).into(holder.image);
            holder.image.visibility = View.VISIBLE;
        } else {
            holder.image.visibility = View.GONE;
        }
        holder.itemView.setOnClickListener {
            listener.newsSelected(news)
        }
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
        val image = newsView.news_card_image;
    }
}