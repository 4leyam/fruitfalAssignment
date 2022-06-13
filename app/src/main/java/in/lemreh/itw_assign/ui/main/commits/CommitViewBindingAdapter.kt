package `in`.lemreh.itw_assign.ui.main.commits

import `in`.lemreh.itw_assign.AppUtil
import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

class CommitViewBindingAdapter {

    companion object {

        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        @JvmStatic
        @BindingAdapter("toIST")
        fun toIST(text : TextView? , stringDate : String?) {

            if(text != null && stringDate != null) {

                val tmp = stringDate.split("T")
                val sd = tmp[0]+" "+tmp[1].substring(IntRange(0 , tmp[1].length-2))+" z"
                Log.e(AppUtil.TAG, "toIST: $sd")
                val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                dateFormat.timeZone = TimeZone.getTimeZone("IST")
                val date: Date?
                try {
                    date = dateFormat.parse(sd)
                    if(date == null) {
                        text.text = "Failed to parse! to IST    [original: $stringDate]"
                    } else {
                        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        text.text = "${simpleDateFormat.format(date)}      [original: $stringDate]"
                    }

                } catch (e: Exception) {
                    text.text = "Failed to parse! to IST    [original: $stringDate]"
                }

            }


        }

    }

}