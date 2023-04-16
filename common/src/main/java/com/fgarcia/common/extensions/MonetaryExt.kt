package com.fgarcia.common.extensions

import android.icu.text.CompactDecimalFormat
import android.icu.text.CompactDecimalFormat.CompactStyle.SHORT
import java.util.Locale.getDefault

fun Long.formatCompactDecimal(): String {
    val format = CompactDecimalFormat.getInstance(getDefault(), SHORT)
    return format.format(this)
}