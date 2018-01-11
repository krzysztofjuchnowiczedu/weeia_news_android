package pl.com.juchnowicz.weeianews.models

object APIResponse {
    data class News(val _id: String, val date: Long, val title: String, val url: String, val content: String)
}
