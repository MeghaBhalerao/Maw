package com.example.maw.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.maw.R
import com.example.maw.classes.T
import com.example.pacermirror.database.TABLE_WEBSITES
import kotlinx.android.synthetic.main.activity_main.*

class AddNewWebsiteActivity : AppCompatActivity() {


    var flag = ""
    var id = ""
    var w_details = ""
    var w_url = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        getIntentData()

        fab_add.setOnClickListener {

            validateFields()
        }
    }

    private fun getIntentData() {

        var bundle = intent.extras

        if(bundle != null)
        {
            flag = bundle.getString("flag")

            if(flag.equals("update"))
            {
                title_tv.setText("Update website")

                id = bundle.getString(TABLE_WEBSITES.ID)
                w_details = bundle.getString(TABLE_WEBSITES.W_DETAILS)
                w_url = bundle.getString(TABLE_WEBSITES.W_URL)

                w_details_edt.setText(w_details)
                w_url_edt.setText(w_url)

            }

        }
    }

    private fun validateFields() {


        if(!T.editextEmpty(w_details_edt,"Enter website details"))
        {
            return
        }
        if(!T.editextEmpty(w_url_edt,"Enter website url"))
        {
            return
        }
        if(flag.equals("update"))
        {
            TABLE_WEBSITES.updateWebsite(
                id,
                w_details_edt.text.toString(),
                w_url_edt.text.toString())
            T.t("Website info updated")
        }
        else
        {
            //insert data into local table
            TABLE_WEBSITES.addNewWebsite(
                w_details_edt.text.toString(),
                w_url_edt.text.toString())
            //set field empty
            w_details_edt.setText("")
            w_url_edt.setText("")
            T.t("Website info added")
        }


    }
}
