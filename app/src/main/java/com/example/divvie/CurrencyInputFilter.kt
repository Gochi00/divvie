package com.example.divvie

import android.text.Spanned
import android.text.InputFilter
import kotlin.math.round


class CurrencyInputFilter(private val decimalDigits: Int = 2) : InputFilter {

    override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int): CharSequence? {
        var dotPos = -1
        val len = dest.length
        for (i in 0 until len) {
            if (dest[i] == '.') {
                dotPos = i
                break
            }
        }
        if (dotPos >= 0) {
            if (source == ".") { return "" }
            if (dend <= dotPos) { return null }
            if (len - dotPos > decimalDigits) { return "" }
        } else if (source == "." && (len - dend) > decimalDigits) { return "" }
        return null
    }

    fun clean(numString: String): String {
        var string = numString
        var dotPos = -1
        val len = string.length - 1
        for (i in string.indices) {
            if (string[i] == '.') {
                dotPos = i
            }
        }
        if (string.length > 1 && string[0] == '0' && string[1] != '.') {
            string = string.removeRange(0, 1)
        }
        if (string.length == 0) {
            string = "0"
        }
        if (string[0] == '.') {
            string = "0" + string
        }
        if (dotPos >= 0) {
            return when (len - dotPos) {
                0 -> string + "00"
                1 -> string + "0"
                2 -> string
                else -> throw Exception("---------------- more than 2 decimals -------------------")
            }
        }
        return string + ".00"
    }

    fun roundAndClean(num: Double): String {
        return clean((round(num * 100) / 100).toString())
    }
}