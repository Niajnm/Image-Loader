package com.example.imageloader
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.imageloader.Auth.LogInActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        testbutton.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    LogInActivity::class.java
                )
            )
        }
    }
}