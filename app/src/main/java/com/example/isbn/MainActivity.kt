package com.example.isbn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import com.google.gson.JsonObject
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.lang.StringBuilder


class MainActivity : AppCompatActivity()
{
    lateinit var textView: TextView

    private val client = OkHttpClient()

    var key = "72b7ac6198d35bb4ae94b6f7eb6a9dfa"

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        run("https://moly.hu/api/books.json?q=%22Ernest Hemingway&key="+key)
    }

    fun run(url: String)
    {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback
        {
            override fun onFailure(call: Call, e: IOException)
            {

            }

            override fun onResponse(call: Call, response: Response)
            {
                var resp = response.body()?.string()
                var obj = JSONObject(resp)
                var arr = obj.getJSONArray("books")
                val builder = StringBuilder()

                for (i in 0 until arr.length())
                {
                    var o = arr.getJSONObject(i)

                    builder.append(o.getString("title")).append('\n')
                }

                textView.text = builder.toString()

            }

        })
    }
}
