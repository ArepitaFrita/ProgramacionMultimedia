package com.p1.loginlayout.DB

// ChampionsContract is the contract for the database
object ChampionsContract {
        val TABLE_NAME = "champions"
        val COLUMN_NAME_NAME = "name"
        val COLUMN_NAME_DMG = "dmg"
        val COLUMN_NAME_RELEASE = "release"
        val COLUMN_NAME_SPLASH = "splashart"
        val COLUMN_NAME_ROLE = "role"

        val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${TABLE_NAME} (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${COLUMN_NAME_NAME} TEXT," +
                    "${COLUMN_NAME_DMG} TEXT," +
                    "${COLUMN_NAME_RELEASE} TEXT, " +
                    "${COLUMN_NAME_SPLASH} INTEGER," +
                    "${COLUMN_NAME_ROLE} TEXT)"

        val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${TABLE_NAME}"
}
