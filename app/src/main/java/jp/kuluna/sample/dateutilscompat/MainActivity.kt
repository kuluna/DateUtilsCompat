package jp.kuluna.sample.dateutilscompat

import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import jp.kuluna.dateutilscompat.DateUtilsCompat
import java.util.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val now = Date()
        val twoHoursLater = Calendar.getInstance().run {
            add(Calendar.HOUR_OF_DAY, 1)
            time
        }
        val tomorrow = Calendar.getInstance().run {
            add(Calendar.DAY_OF_MONTH, 1)
            time
        }
        val nextYear = Calendar.getInstance().run {
            add(Calendar.YEAR, 1)
            time
        }

        val formattedColonTime = DateUtilsCompat.formatDateRange(
            this, now.time, twoHoursLater.time,
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_TIME,
            DateUtilsCompat.COLON
        )
        val formattedKanjiTime = DateUtilsCompat.formatDateRange(
            this, now.time, twoHoursLater.time,
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_TIME,
            DateUtilsCompat.KANJI
        )

        Log.i(localClassName, formattedColonTime)
        Log.i(localClassName, formattedKanjiTime)

        val hourRangeString = DateUtilsCompat.formatDateRange(
            this, now.time, twoHoursLater.time,
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_TIME
        )
        val dayRangeString = DateUtilsCompat.formatDateRange(
            this, now.time, tomorrow.time,
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_TIME
        )
        val yearRangeString = DateUtilsCompat.formatDateRange(
            this, now.time, nextYear.time,
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_TIME
        )

        Log.i(localClassName, hourRangeString)
        Log.i(localClassName, dayRangeString)
        Log.i(localClassName, yearRangeString)


        val nowString = DateUtilsCompat.formatDateTime(
            this, now.time,
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_TIME
        )

        Log.i(localClassName, nowString)
    }
}
