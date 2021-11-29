package com.example.isbn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.lang.StringBuilder

class MainActivity : AppCompatActivity()
{
    lateinit var textView: TextView
    var Auth_Boo: String? = null
    var Radio_Butt: String? = null

    private val client = OkHttpClient()

    lateinit var lv : ListView

    var key = "72b7ac6198d35bb4ae94b6f7eb6a9dfa"

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Auth_Boo = intent.getStringExtra("Auth_Boo")
        Radio_Butt = intent.getStringExtra("Radio_Butt")

        //textView = findViewById(R.id.textView)

        lv = findViewById(R.id.listView)

        run("https://moly.hu/api/books.json?q=%22" + Auth_Boo + "&key=" +key)
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

                var array_list = ArrayList<String>()
                val arrayAdapter: ArrayAdapter<*>

                if(Radio_Butt == "Könyv")
                {
                    for (i in 0 until arr.length())
                    {
                        var o = arr.getJSONObject(i)

                        if(o.getString("title") == Auth_Boo)
                        {
                            //var str = "A \"" + Auth_Boo + "\" írója: "
                            //builder.append(str).append(o.getString("author")).append('\n')
                        }
                    }
                }
                else if (Radio_Butt == "Szerző")
                {
                    for (i in 0 until arr.length())
                    {
                        var o = arr.getJSONObject(i)

                        if(o.getString("author") == Auth_Boo)
                        {
                            //builder.append(o.getString("title")).append('\n')
                            array_list.add(o.getString("title"))
                        }
                    }
                }

                arrayAdapter = ArrayAdapter(
                    this@MainActivity,
                    android.R.layout.simple_list_item_1,
                    array_list
                )


                runOnUiThread{
                    lv.adapter = arrayAdapter
                }



                //textView.text = builder.toString()
            }

        })
    }
}
