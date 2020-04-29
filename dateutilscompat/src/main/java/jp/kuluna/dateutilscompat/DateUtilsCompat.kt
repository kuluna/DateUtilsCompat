package jp.kuluna.dateutilscompat

import android.content.Context
import android.text.format.DateUtils
import java.util.*

enum class DateUtilsCompat {
    KANJI,
    COLON;

    companion object {
        fun formatDateRange(
            context: Context,
            startMillis: Long,
            endMillis: Long,
            flags: Int,
            formatTimeType: DateUtilsCompat = COLON
        ): String {
            var string = DateUtils.formatDateRange(context, startMillis, endMillis, flags)
            val locale = Locale.getDefault()
            if (locale.language ==  Locale.JAPANESE.language) {
                string = when (formatTimeType) {
                    KANJI -> string.replace(Regex("(\\d{1,2}):(\\d{1,2})"), "$1時$2分")
                    COLON -> string.replace(Regex("(\\d{1,2})時(\\d{1,2})分"), "$1:$2")
                }
            }
            return string
        }
    }
}
