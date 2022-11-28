package com.p1.loginlayout.DB

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.p1.loginlayout.DB.ChampionsContract.COLUMN_NAME_DMG
import com.p1.loginlayout.DB.ChampionsContract.COLUMN_NAME_NAME
import com.p1.loginlayout.DB.ChampionsContract.COLUMN_NAME_RELEASE
import com.p1.loginlayout.DB.ChampionsContract.COLUMN_NAME_ROLE
import com.p1.loginlayout.DB.ChampionsContract.COLUMN_NAME_SPLASH
import com.p1.loginlayout.DB.ChampionsContract.SQL_CREATE_ENTRIES
import com.p1.loginlayout.DB.ChampionsContract.SQL_DELETE_ENTRIES
import com.p1.loginlayout.DB.ChampionsContract.TABLE_NAME
import com.p1.loginlayout.model.Champion

// ChampionsDBHelper is the helper class for the database
class ChampionsDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)

    }

    // onUpgrade is called when the database version is changed
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 8
        const val DATABASE_NAME = "champions.db"
    }

    // Function to insert a champion into the database
    fun insertChamp(c: Champion) {
        val values = ContentValues()
        values.put(COLUMN_NAME_NAME, c.name)
        values.put(COLUMN_NAME_DMG, c.dmg)
        values.put(COLUMN_NAME_RELEASE, c.release)
        values.put(COLUMN_NAME_SPLASH, c.splash)
        values.put(COLUMN_NAME_ROLE, c.role)

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)

    }

    // Function to get all champions from the database
    @SuppressLint("Range")
    fun listChamps(): MutableList<Champion> {
        val champs = mutableListOf<Champion>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        if (cursor.moveToFirst()) {
            do {
                val champ = Champion(
                    cursor.getInt(cursor.getColumnIndex("ID")),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME_NAME)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DMG)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME_RELEASE)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_SPLASH)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ROLE))
                )
                champs.add(champ)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return champs
    }

    // Function to delete all champions from the database
    fun deleteAll() {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME")
    }

    fun deleteById(id: Int) {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME WHERE ID = $id")
    }


}