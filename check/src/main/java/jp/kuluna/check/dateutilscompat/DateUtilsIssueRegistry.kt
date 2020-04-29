package jp.kuluna.check.dateutilscompat

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

@Suppress("unused")
class DateUtilsIssueRegistry : IssueRegistry() {
    override val api: Int = CURRENT_API
    override val issues: List<Issue> = listOf(DateUtilsLint.ISSUE)
}
