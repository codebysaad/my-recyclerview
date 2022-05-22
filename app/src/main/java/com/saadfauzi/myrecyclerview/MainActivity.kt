package com.saadfauzi.myrecyclerview

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.saadfauzi.myrecyclerview.databinding.ActivityMainBinding
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var listAndroid: ArrayList<AndroidModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        listAndroid.addAll(dataAndroid)

        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager =
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    GridLayoutManager(this@MainActivity, 2)
                } else {
                    LinearLayoutManager(this@MainActivity)
                }
            val adapterAndroid = RecyclerAdapterAndroid(listAndroid)
            adapter = adapterAndroid
            adapterAndroid.setOnItemClickListener(object :
                RecyclerAdapterAndroid.IOnItemClickCallback {
                override fun onItemClicked(data: AndroidModel) {
                    goToDetail(data)
                }

            })
        }

    }

    private fun goToDetail(data: AndroidModel) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA, data)
        startActivity(intent)
    }

    private val dataAndroid: ArrayList<AndroidModel>
        get() {
            val list: ArrayList<AndroidModel> = ArrayList()
            try {
                val obj = JSONObject(loadJsonFromAssets())
                val dataArray = obj.getJSONArray("android")
                for (i in 0 until dataArray.length()) {
                    val objAndroid = dataArray.getJSONObject(i)
                    val model = AndroidModel(
                        objAndroid.getString("name"),
                        objAndroid.getString("desc"),
                        objAndroid.getString("image")
                    )
                    list.add(model)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            return list
        }

    private fun loadJsonFromAssets(): String {
        val json: String?
        try {
            val inputStream = assets.open("android.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        } catch (e: IOException) {
            e.printStackTrace()
            return ""
        }
        Log.d("Json Buffer", json)
        return json
    }
}