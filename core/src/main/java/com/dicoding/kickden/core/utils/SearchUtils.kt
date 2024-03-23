package com.dicoding.kickden.core.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SearchUtils {
    fun getSearchQuery(keyword : String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM sneakers")
        if (keyword != "") {
            val escapedKeyword = "%${keyword.replace("'", "''")}%"
            simpleQuery.append(" WHERE name LIKE ? OR brand LIKE ?")
            return SimpleSQLiteQuery(simpleQuery.toString(), arrayOf(escapedKeyword, escapedKeyword))
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}