package com.saadfauzi.myrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saadfauzi.myrecyclerview.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private lateinit var dataAndroid: AndroidModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        dataAndroid = intent.getParcelableExtra(EXTRA_DATA)!!

        binding.apply {
            nameDetail.text = dataAndroid.name
            descDetail.text = dataAndroid.desc
            val imageAndroid = resources.getIdentifier(dataAndroid.image, null, applicationContext.packageName)
            imageDetail.setImageResource(imageAndroid)
        }

    }

    companion object {
        const val EXTRA_DATA = "data_android"
    }
}