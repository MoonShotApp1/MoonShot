package com.dartmouth.moonshot

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_individual_coin.*

class SocialsActivity: AppCompatActivity() {
    private lateinit var announcementTV: TextView
    private lateinit var chatTV: TextView
    private lateinit var facebookTV: TextView
    private lateinit var forumTV: TextView
    private lateinit var twitterTV: TextView

    private lateinit var closeButton: Button

    private var announcement: String? = null
    private var chat: String? = null
    private var facebook: String? = null
    private var forum: String? = null
    private var twitter: String? = null

    companion object {
        val ANNOUNCEMENT_KEY = "announcement"
        val CHAT_KEY = "chat"
        val FACEBOOK_KEY = "facebook"
        val FORUM_KEY = "forum"
        val TWITTER_KEY = "key"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_socials)

        announcementTV = findViewById(R.id.text_announcement)
        chatTV = findViewById(R.id.text_chat)
        facebookTV = findViewById(R.id.text_facebook)
        forumTV = findViewById(R.id.text_forum)
        twitterTV = findViewById(R.id.text_twitter)

        closeButton = findViewById(R.id.button_close)
        closeButton.setOnClickListener(){
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        announcement = intent.getStringExtra(ANNOUNCEMENT_KEY)
        chat = intent.getStringExtra(CHAT_KEY)
        facebook = intent.getStringExtra(FACEBOOK_KEY)
        forum = intent.getStringExtra(FORUM_KEY)
        twitter = intent.getStringExtra(TWITTER_KEY)

        if(announcement != null && announcement != ""){
            announcementTV.text = "Coin's announcements"
            announcementTV.setOnClickListener(){
                showWebpage(announcement)
            }
        }

        if(chat != null && chat != ""){
            chatTV.text = "Coin's Discord"
            chatTV.setOnClickListener(){
                showWebpage(chat)
            }
        }

        if(facebook != null && facebook != ""){
            facebookTV.text = "Coin's Facebook"
            facebookTV.setOnClickListener(){
                val url = "https://www.facebook.com/" + facebook
                showWebpage(url)
            }
        }

        if(forum != null && forum != ""){
            forumTV.text = "Coin's official forum"
            forumTV.setOnClickListener(){
                showWebpage(forum)
            }
        }

        if(twitter != null && twitter != ""){
            twitterTV.text = "Coin's twitter"
            twitterTV.setOnClickListener(){
                val url = "https://twitter.com/" + twitter
                showWebpage(url)
            }
        }
    }

    fun showWebpage(url: String?){
        var finalUrl = url
        if(url!!.subSequence(0,5) == "http:"){
            val end = url!!.substring(5)
            finalUrl = "https:" + end
        }
        val intent = Intent(this, CoinWebpageActivity::class.java)
        // Hardcode
        intent.putExtra(CoinWebpageActivity.URL_KEY, finalUrl)
        this.startActivity(intent)
    }
}