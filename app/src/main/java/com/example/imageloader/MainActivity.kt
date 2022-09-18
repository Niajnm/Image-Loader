package com.example.imageloader
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.imageloader.Auth.LogInActivity
import com.example.imageloader.ImageUpoad.ImagePickActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        LoginButton_id.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    LogInActivity::class.java
                )
            )
        }
        text_input_end_icon.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    ImagePickActivity::class.java
                )
            )
        }
    }
}