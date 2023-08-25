package ICI.recyclerviews.data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ICI.recyclerviews.R
import android.content.Intent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.logging.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)


       val scope = CoroutineScope(Dispatchers.Main)
        scope.launch {
            delay( 3500)
            startActivity(Intent(this@SplashActivity,MainActivity::class.java))
            finish()
        }

       // setContentView(R.layout.activity_splash)

    }


}