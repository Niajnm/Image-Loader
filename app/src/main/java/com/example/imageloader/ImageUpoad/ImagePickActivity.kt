package com.example.imageloader.ImageUpoad

import android.Manifest
import android.R.attr
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imageloader.R
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.image_item_layout.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONException
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


class ImagePickActivity : AppCompatActivity(), View.OnClickListener {
    var recyclerView: RecyclerView? = null
    var textView: TextView? = null
    var button: Button? = null
    var list: ArrayList<Uri>? = null
    var adaptor: RecyclerAdaptor? = null
    var colum = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_upload)
        list = ArrayList()
        recyclerView = findViewById(R.id.recyclerid)
        textView = findViewById(R.id.textView)
        button = findViewById(R.id.button)
        adaptor = RecyclerAdaptor(list!!)
        recyclerView?.layoutManager = GridLayoutManager(this, 4)
        recyclerView?.adapter = adaptor
        button?.setOnClickListener(this)
        if (ActivityCompat.checkSelfPermission(
                this, colum[0]
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this, colum[1]
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(colum, 123)
            }
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button -> openGalley()
        }
    }

    private fun openGalley() {
        val intent = Intent()
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 123)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123 && resultCode == RESULT_OK) {
            if (data!!.clipData != null) {
                val x = data.clipData!!.itemCount
                for (i in 0 until x) {
                    list!!.add(data.clipData!!.getItemAt(i).uri)
                }
                adaptor!!.notifyDataSetChanged()
                textView!!.text = "Image(" + list!!.size + ")"
            } else if (data.data != null) {
                val imgurl = data.data!!.path
                list!!.add(Uri.parse(imgurl))
            }

//            val selectedImage: Uri = attr.data.clipData.
//            val selectedFilePath: String = FilePath.getPath(this, selectedImage)
//            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
//            val cursor: Cursor =
//                contentResolver.query(selectedImage, filePathColumn, null, null, null)!!
//            cursor.moveToFirst()
//            val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
//            var imagePath = cursor.getString(columnIndex)
////                str1.setText(mediaPath);
//            // Set the Image in ImageView for Previewing the Media
//            //                str1.setText(mediaPath);
//            // Set the Image in ImageView for Previewing the Media
//            imageView.setImageBitmap(BitmapFactory.decodeFile(selectedFilePath))
//            cursor.close()
            // uploadImage()
        }

    }


}

