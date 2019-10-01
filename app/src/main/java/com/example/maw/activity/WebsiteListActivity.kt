package com.example.maw.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.example.maw.R
import com.example.maw.adapter.WebsiteListAdapter
import com.example.maw.classes.MyApplication
import com.example.maw.classes.T
import com.example.pacermirror.database.TABLE_WEBSITES
import kotlinx.android.synthetic.main.activity_website_list.*

class WebsiteListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_website_list)

        fab_add.setOnClickListener {

            var i = Intent(this,AddNewWebsiteActivity::class.java)
            i.putExtra("flag","insert")
            startActivity(i)
        }
        getWebsiteData()

    }

    private fun getWebsiteData() {

        //get local sms details
        var SMS_INFO = TABLE_WEBSITES.selectWebsiteDetails()
        if(SMS_INFO.isEmpty())
        {
            T.t("Oops ! website details not found")
        }
        else
        {
            w_list_rv.layoutManager = LinearLayoutManager(MyApplication.context, LinearLayout.VERTICAL, false)
            val adapter = WebsiteListAdapter(SMS_INFO);
            w_list_rv.adapter = adapter
            adapter.notifyDataSetChanged()

        }
    }

    override fun onResume() {
        super.onResume()
        getWebsiteData()
    }
}
