package com.example.tiki

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {

    var arrayKey: ArrayList<String> = ArrayList()
    lateinit var keyAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val urlJSON = "https://raw.githubusercontent.com/tikivn/android-home-test/v2/keywords.json"
        ReadJSON().execute(urlJSON)

        rvKeyword.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) as RecyclerView.LayoutManager?
        keyAdapter = CustomAdapter(arrayKey, this)

        rvKeyword.adapter = keyAdapter

    }

    inner class ReadJSON : AsyncTask<String, Void, String>(){
        override fun doInBackground(vararg params: String?): String {

            var content: StringBuilder = StringBuilder()
            val url: URL = URL(params[0])
            val urlConnection: HttpsURLConnection = url.openConnection() as HttpsURLConnection
            val inputStreamReader: InputStreamReader = InputStreamReader(urlConnection.inputStream)
            val bufferedReader: BufferedReader = BufferedReader(inputStreamReader as Reader?)

            var line: String = ""
            try {
                do {
                    line = bufferedReader.readLine()
                    if(line != null){
                        content.append(line)
                    }
                }while (line != null)

                bufferedReader.close()

            }catch ( e: Exception){
                Log.d("AAA", e.toString())
            }
            return content.toString()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            val listType = object : TypeToken<List<String>>() {}.type
            arrayKey = Gson().fromJson(result,listType)
            keyAdapter.updateData(arrayKey)
        }

    }
}
