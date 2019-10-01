package com.example.maw.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.maw.R
import com.example.maw.activity.AddNewWebsiteActivity
import com.example.maw.activity.WebviewActivity
import com.example.maw.classes.T
import com.example.pacermirror.database.TABLE_WEBSITES
import sn.sns.hotmixplant.pojo.WebsiteInfo

class WebsiteListAdapter (val userList: ArrayList<WebsiteInfo>) : RecyclerView.Adapter<WebsiteListAdapter.ViewHolder>()
{
    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.website_row, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bindItems(userList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int
    {
        return userList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {

        fun bindItems(user: WebsiteInfo)
        {
            val details_tv = itemView.findViewById(R.id.details_tv) as TextView
            val url_tv = itemView.findViewById(R.id.url_tv) as TextView
            val updateWebsite_tv = itemView.findViewById(R.id.updateWebsite_tv) as Button

            details_tv.setText(user.w_details)
            url_tv.setText(user.w_url)

            itemView.setOnClickListener {

                if(T.isNetworkAvailable())
                {
                    var i = Intent(itemView.context, WebviewActivity::class.java)
                    i.putExtra("url",user.w_url)
                    itemView.context.startActivity(i)
                }
                else
                {
                    T.t("Oops ! no internet connection")
                }

            }
            updateWebsite_tv.setOnClickListener {

                var i = Intent(itemView.context, AddNewWebsiteActivity::class.java)
                i.putExtra("flag","update")
                i.putExtra(TABLE_WEBSITES.ID,user.id)
                i.putExtra(TABLE_WEBSITES.W_DETAILS,user.w_details)
                i.putExtra(TABLE_WEBSITES.W_URL,user.w_url)
                itemView.context.startActivity(i)
            }

        }



    }
}