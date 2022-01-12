package org.fh.helpachild.utils

import android.app.DatePickerDialog
import android.view.View
import android.widget.DatePicker
import java.text.SimpleDateFormat
import java.util.*

class utilFunctions {


    fun convertDate(date: String): Calendar {
        val cal = Calendar.getInstance()
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        cal.time = sdf.parse(date)

        return cal;
    }

    fun convertDate(date: Calendar): String {

        val format = SimpleDateFormat("dd/MM/yyy")
        return format.format(date)
    }


    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun getCurrentDateTime(): Calendar {
        return Calendar.getInstance()
    }






}