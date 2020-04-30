DateUtilsCompat
---

[![Download](https://api.bintray.com/packages/kuluna/maven/dateutilscompat/images/download.svg) ](https://bintray.com/kuluna/maven/dateutilscompat/_latestVersion)

Androidの言語設定に従って2つの日付の範囲を文字列にフォーマットします。  
Androidの[DateUtils.formatDateRange]は日本語のみ出力結果が微妙に異なります。

- 同じ日付の場合は 4月29日 13時37分～15時37分
- 日付が異なる場合 4月29日 13:37～4月30日 13:37
- 年が異なる場合 2020年4月29日 13:37～2021年4月29日 13:37

と「時分」と「:」の2パターンが出力されてしまいます。DateUtilsCompatはそのどちらかに揃えて出力します。日本語以外はオリジナルのDateUtilsと同じ動きをします。

# Install
```
implementation 'jp.kuluna:dateutilscompat:1.0.0'
```
# Usage

```kotlin
// DateUtils.formatDateTime(context, start, DateUtils.FORMAT_SHOW_DATE. or DateUtils.FORMAT_SHOW_TIME)
// 4月29日 13時37分

DateUtilsCompat.formatDateTime(context, start, DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_TIME)
// 4月29日 13:37

// DateUtils.formatDateRange(context, start, end, DateUtils.FORMAT_SHOW_DATE. or DateUtils.FORMAT_SHOW_TIME)
// 4月29日 13時37分～15時37分
// 4月29日 13:37～4月30日 13:37
// 2020年4月29日 13:37～2021年4月29日 13:37

DateUtilsCompat.formatDateRange(context, start, end, DateUtils.FORMAT_SHOW_DATE. or DateUtils.FORMAT_SHOW_TIME)
// 4月29日 13:37～15:37
// 4月29日 13:37～4月30日 13:37
// 2020年4月29日 13:37～2021年4月29日 13:37
```

## Custom
```kotlin
DateUtilsCompat.formatDateTime(context, start, DateUtils.FORMAT_SHOW_DATE. or DateUtils.FORMAT_SHOW_TIME, DateUtilsCompat.COLON)
// 4月29日 13:37

DateUtilsCompat.formatDateTime(context, start, DateUtils.FORMAT_SHOW_DATE. or DateUtils.FORMAT_SHOW_TIME, DateUtilsCompat.KANJI)
// 4月29日 13時37分
```

# License
MIT License.
