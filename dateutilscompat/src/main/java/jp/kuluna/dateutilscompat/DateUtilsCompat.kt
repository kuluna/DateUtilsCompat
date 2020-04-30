package jp.kuluna.dateutilscompat

import android.content.Context
import android.text.format.DateUtils
import java.util.*

/**
 * Androidの日付ユーティリティクラスの[DateUtils]の一部処理を日本語環境のために置き換えるクラスです。
 */
enum class DateUtilsCompat {
    /**
     * 時刻の出力を漢字形式にします。
     *
     * - 11時00分〜13時00分
     * - 午前11時00分〜午後1時00分
     */
    KANJI,

    /**
     * 時刻の出力をコロン「:」形式にします。
     *
     * 11:00〜13:00
     * 午前11:00〜午後1:00
     */
    COLON;

    companion object {
        /**
         * Androidの言語設定に従って日付を文字列にフォーマットします。
         *
         * Androidの[DateUtils.formatDateTime]は[DateUtils.formatDateRange]でフォーマットされる結果が
         * 日本語のみ微妙に異なります。
         * [DateUtilsCompat.formatDateRange]を使う場合はこちらも併せて使うことを推奨します。
         *
         * - 4月29日 13時37分
         * - 4月29日 13:37
         *
         * と「時分」と「:」の2パターンのどちらかに揃えて出力します。
         * 日本語以外はオリジナルのDateUtilsと同じ動きをします。
         *
         * @param context Android [Context]
         * @param millis UTCミリ秒
         * @param flags [DateUtils]のフラグに合わせてフォーマットします
         * @param formatTimeType 時刻フォーマットを「時分」と「:」のどちらに揃えるか指定します
         * @return フォーマットされた文字列
         *
         * @see DateUtils.formatDateTime
         */
        fun formatDateTime(
            context: Context,
            millis: Long,
            flags: Int,
            formatTimeType: DateUtilsCompat = COLON
        ): String {
            val string = DateUtils.formatDateTime(context, millis, flags)
            return adjustForJapanese(string, formatTimeType)
        }

        /**
         * Androidの言語設定に従って2つの日付の範囲を文字列にフォーマットします。
         *
         * Androidの[DateUtils.formatDateRange]は日本語のみ出力結果が微妙に異なります。
         * - 同じ日付の場合は 4月29日 13時37分～15時37分
         * - 日付が異なる場合 4月29日 13:37～4月30日 13:37
         * - 年が異なる場合   2020年4月29日 13:37～2021年4月29日 13:37
         *
         * と「時分」と「:」の2パターンが出力されてしまいます。この関数はそのどちらかに揃えて出力します。
         * 日本語以外はオリジナルのDateUtilsと同じ動きをします。
         *
         * @param context Android [Context]
         * @param startMillis 開始時刻のUTCミリ秒
         * @param endMillis 終了時刻のUTCミリ秒
         * @param flags [DateUtils]のフラグに合わせてフォーマットします
         * @param formatTimeType 時刻フォーマットを「時分」と「:」のどちらに揃えるか指定します
         * @return フォーマットされた文字列
         *
         * @see DateUtils.formatDateRange
         */
        fun formatDateRange(
            context: Context,
            startMillis: Long,
            endMillis: Long,
            flags: Int,
            formatTimeType: DateUtilsCompat = COLON
        ): String {
            val string = DateUtils.formatDateRange(context, startMillis, endMillis, flags)
            return adjustForJapanese(string, formatTimeType)
        }

        private fun adjustForJapanese(formatted: String, formatTimeType: DateUtilsCompat): String {
            val locale = Locale.getDefault()
            return if (locale.language == Locale.JAPANESE.language) {
                when (formatTimeType) {
                    KANJI -> formatted.replace(Regex("(\\d{1,2}):(\\d{1,2})"), "$1時$2分")
                    COLON -> formatted.replace(Regex("(\\d{1,2})時(\\d{1,2})分"), "$1:$2")
                }
            } else {
                formatted
            }
        }
    }
}
