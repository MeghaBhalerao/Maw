package com.example.pacermirror.database

import android.content.ContentValues
import com.example.maw.classes.MyApplication
import sn.sns.hotmixplant.pojo.WebsiteInfo


class TABLE_WEBSITES
{
    companion object
    {
        val TABLE_NAME : String = "table_sms_history"

        val ID : String = "id"
        val W_DETAILS : String = "w_details"
        val W_URL : String = "w_url"


        var CREATE_NEW_TABLE = ("CREATE TABLE " + TABLE_NAME
                + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + W_DETAILS + " TEXT, "
                + W_URL + " TEXT)")


        //function for insert new message details
        fun addNewWebsite(w_details: String,
                          w_url: String )
        {

            val db = MyApplication.db!!.getWritableDatabase()

            val values = ContentValues()
            values.put(W_DETAILS, w_details)
            values.put(W_URL, w_url)

            db.insert(TABLE_NAME, null, values)

        }

        fun updateWebsite(id : String, w_details:  String, w_url: String)
        {
            try
            {
                val db = MyApplication.db!!.getReadableDatabase()
                val deleteQuery = "UPDATE $TABLE_NAME SET $W_DETAILS = '"+w_details+"',$W_URL = '"+w_url+"' WHERE $ID = '"+id+"'"
                db.execSQL(deleteQuery)

            } catch (e: Exception) {

            }

        }
        fun selectWebsiteDetails(): ArrayList<WebsiteInfo> {

            var SMS_DATA = ArrayList<WebsiteInfo>()

            try
            {
                val db = MyApplication.db!!.getReadableDatabase()
                val uQuery = "SELECT * FROM $TABLE_NAME"
                var cursor = db.rawQuery(uQuery, null)

                if(cursor!!.count > 0)
                {
                    while (cursor!!.moveToNext())
                    {
                        var ID = cursor.getString(cursor.getColumnIndex(ID))
                        var W_DETAILS = cursor.getString(cursor.getColumnIndex(W_DETAILS))
                        var W_URL = cursor.getString(cursor.getColumnIndex(W_URL))

                        SMS_DATA.add(WebsiteInfo(ID,W_DETAILS,W_URL))
                    }
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }

            return SMS_DATA!!
        }
        /*//function for select prescription id

        fun updateKey(key : String, boardNumber : String, presc_id: String)
        {
            try
            {
                val db = MyApplication.db!!.getReadableDatabase()
                val deleteQuery = "UPDATE $TABLE_NAME SET $KEY_DATA = '"+key+"' WHERE $BOARD_NUMBER = '"+boardNumber+"' AND $PRESCRIPTION_ID = '"+presc_id+"'"
                db.execSQL(deleteQuery)

            } catch (e: Exception) {

            }

        }

        //function for check dulplicate board id
        fun checkDuplicateBoardId(boardNumber: String,presc_id: String): String {
            var status = "2"
            var cursor: Cursor? = null

            try
            {
                val db = MyApplication.db!!.getReadableDatabase()
                val uQuery = "SELECT COUNT(*) AS  '"+RETURN_COUNT+"' FROM $TABLE_NAME WHERE $BOARD_NUMBER = '"+boardNumber+"' AND $PRESCRIPTION_ID = '"+presc_id+"'"
                cursor = db.rawQuery(uQuery, null)

                if (cursor!!.moveToNext())
                {
                    val counterCheck = cursor.getString(cursor.getColumnIndex(RETURN_COUNT))

                    val ddd = Integer.valueOf(counterCheck)

                    if (ddd > 0)
                    {
                        status = "1"
                    }
                    else
                    {
                        status = "0"
                    }
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }

            return status
        }
        //function for select board id
        fun getKey(boardNumber: String): String {
            var keyData : String? = null
            var cursor: Cursor? = null

            try
            {
                val db = MyApplication.db!!.getReadableDatabase()
                val uQuery = "SELECT $KEY_DATA FROM $TABLE_NAME WHERE $BOARD_NUMBER = '"+boardNumber+"'"
                cursor = db.rawQuery(uQuery, null)

                if (cursor!!.moveToNext())
                {
                    keyData = cursor.getString(cursor.getColumnIndex(KEY_DATA))


                }


            } catch (e: Exception) {
                e.printStackTrace()
            }

            return keyData!!
        }*/


    }
}