package com.maikkkko1.essential_libs.common

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.CalendarContract
import com.maikkkko1.essential_libs.common.entity.ScheduleItemAndroidCalendar
import java.util.*

class CommonManager(private val activity: Activity) {
    fun scheduleItemOnAndroidCalendar(scheduleItemAndroidCalendar: ScheduleItemAndroidCalendar) {
        val startDate = Calendar.getInstance().apply {
            scheduleItemAndroidCalendar.startDate.let {
                set(it.year, it.month, it.day, it.hours, it.minutes)
            }
        }

        val endDate = Calendar.getInstance().apply {
            scheduleItemAndroidCalendar.endDate.let {
                set(it.year, it.month, it.day, it.hours, it.minutes)
            }
        }

        val calendarIntent = Intent(Intent.ACTION_INSERT).apply {
            data = CalendarContract.Events.CONTENT_URI
            putExtra(CalendarContract.Events.TITLE, scheduleItemAndroidCalendar.eventTitle)
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startDate.timeInMillis)
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endDate.timeInMillis)
        }

        activity.startActivity(calendarIntent)
    }

    fun openWebView(webUrl: String) {
        val webIntent = Intent(activity, WebViewActivity::class.java).apply {
            putExtra("targetUrl", webUrl)
        }

        activity.startActivity(webIntent)
    }

    fun callPhoneNumber(number: String) {
        activity.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:${number}")))
    }

    fun openGoogleMapsDirectionForCoordinates(lat: Double, lng: Double) {
        val gmmIntentUri = Uri.parse("google.navigation:q=${lat},${lng}&mode=d")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri).apply {
            setPackage("com.google.android.apps.maps")
        }

        activity.startActivity(mapIntent)
    }
}