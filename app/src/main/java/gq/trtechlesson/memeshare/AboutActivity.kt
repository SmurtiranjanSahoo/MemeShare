package gq.trtechlesson.memeshare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        About.text = "This is a Meme App, where you can find awesome memes from Reddit and you can also share these memes with your friends."

//        Credit.text = "Thanks to this Awesome API, Which make this app Possible API Link : https://meme-api.herokuapp.com/gimme"
//        Credit.movementMethod = LinkMovementMethod.getInstance()
    }
}