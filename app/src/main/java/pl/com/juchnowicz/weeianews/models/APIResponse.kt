package pl.com.juchnowicz.weeianews.models

object APIResponse {
    data class News(val _id: String, val type: String, val date: String, val title: String, val url: String, val content: String, val imageURL:String?)
}
