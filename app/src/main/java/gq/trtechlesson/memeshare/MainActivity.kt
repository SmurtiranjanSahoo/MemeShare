package gq.trtechlesson.memeshare

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {

    var currentImageUrl: String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadMeme()
    }

   private fun loadMeme(){

       ProgressBar.visibility = View.VISIBLE
      val url = "https://meme-api.herokuapp.com/gimme"

       val jsonObjectRequest = JsonObjectRequest(
           Request.Method.GET, url, null,
           { response ->

               currentImageUrl = response.getString("url")
               Glide.with(this).load(currentImageUrl).listener(object : RequestListener<Drawable>{
                   override fun onLoadFailed(
                       e: GlideException?,
                       model: Any?,
                       target: Target<Drawable>?,
                       isFirstResource: Boolean
                   ): Boolean {
                       ProgressBar.visibility = View.GONE
                       loadMeme()
                       return false
                   }

                   override fun onResourceReady(
                       resource: Drawable?,
                       model: Any?,
                       target: Target<Drawable>?,
                       dataSource: DataSource?,
                       isFirstResource: Boolean
                   ): Boolean {
                       ProgressBar.visibility = View.GONE
                       return false
                   }

               }).thumbnail(0.25f).into(memeImageView)

           },
           {
           }
       )
// Access the RequestQueue through your singleton class.
       MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    fun nextMeme(view: View) {
        loadMeme()
    }
    fun shareMeme(view: View) {

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "Checkout This Meme $currentImageUrl")
        val chooser = Intent.createChooser(intent, "Share this Meme Using ...")
        startActivity(chooser)
    }

    // memu bar initialization
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.actionbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // menu bar icon handling

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {

        R.id.menu_about -> {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
            true
        }
        R.id.menu_share -> {
            val appUrl = "https://github.com/SmurtiranjanSahoo/MemeShare"
            val intent = Intent(Intent.ACTION_SEND)
            intent.type ="text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "Checkout This Amazing Meme App $appUrl")
            val chooser = Intent.createChooser(intent, "Share this App Using ...")
            startActivity(chooser)
            true
        }
        else -> super.onOptionsItemSelected(item)

    }































}