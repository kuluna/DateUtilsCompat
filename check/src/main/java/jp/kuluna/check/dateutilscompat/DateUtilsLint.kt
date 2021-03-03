package jp.kuluna.check.dateutilscompat

import com.android.tools.lint.detector.api.*
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression

class DateUtilsLint : Detector(), SourceCodeScanner {
    override fun getApplicableMethodNames(): List<String> =
        listOf("formatDateTime", "formatDateRange")

    override fun visitMethodCall(context: JavaContext, node: UCallExpression, method: PsiMethod) {
        super.visitMethodCall(context, node, method)
        if (context.evaluator.isMemberInClass(method, "android.text.format.DateUtils")) {
            context.report(
                issue = ISSUE,
                scope = node,
                location = context.getLocation(node),
                message = "代わりにDateUtilsCompatを使ってください。"
            )
        }
    }

    companion object {
        @JvmField
        val ISSUE: Issue = Issue
            .create(
                id = "DateUtilsCompat",
                briefDescription = "DateUtilsのformatDateRangeは日本語の時に表記揺れがあります。",
                explanation = """
                    DateUtilsのformatDateRangeは日本語の時に表記揺れがあります。
                    同じ日付の時は「12時00分〜14時00分」と表示されますが、日付が違う時は「12:00〜14:00」と表示されます。
                """.trimIndent(),
                category = Category.CORRECTNESS,
                severity = Severity.WARNING,
                implementation = Implementation(
                    DateUtilsLint::class.java,
                    Scope.JAVA_FILE_SCOPE
                )
            )
    }
}
