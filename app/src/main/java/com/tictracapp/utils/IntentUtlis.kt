package com.tictracapp.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.tictracappTest.R

object IntentUtils {

    private const val PHONE_INTENT = "tel:"

    fun sendEmail(act: Activity, mailTo: String, subject: String, body: String = ""){

        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(mailTo))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, body)
        intent.type = "message/rfc822"
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        act.startActivity(Intent.createChooser(intent, act.getString(R.string.email_chooser_title)))
    }

    fun callToNumber(act: Activity, phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse(PHONE_INTENT + phoneNumber))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        act.startActivity(intent)
    }
}
