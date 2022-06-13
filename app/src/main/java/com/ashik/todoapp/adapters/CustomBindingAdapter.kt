package com.ashik.todoapp.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ashik.todoapp.R
import com.ashik.todoapp.utils.getFormattedDateTime
import com.ashik.todoapp.utils.priority_high
import com.ashik.todoapp.utils.priority_normal

@BindingAdapter("app:setTodoPriorityIcon")
fun setPriorityIcon(iv: ImageView, priority: String) {
    val icon = when (priority) {
        priority_high -> R.drawable.ic_red_star_24
        priority_normal -> R.drawable.ic_blue_stars_24
        else -> R.drawable.ic_grey_stars_24
    }
    iv.setImageResource(icon)
}

@BindingAdapter("app:setDate")
fun setFormattedDate(tv: TextView, date: Long) {
    tv.text = getFormattedDateTime(date, "dd/MM/yyyy")
}

@BindingAdapter("app:setTime")
fun setFormattedTime(tv: TextView, date: Long) {
    tv.text = getFormattedDateTime(date, "hh:mm a")
}