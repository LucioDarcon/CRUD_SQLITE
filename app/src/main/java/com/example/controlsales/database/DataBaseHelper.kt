package com.example.controlsales.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.controlsales.constants.ConstantsDB

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context,
    DATA_NAME, null,
    DATA_VERSION
) {

    companion object {
        private const val DATA_NAME = "control_sales.db"
        private const val DATA_VERSION = 1
    }

    private val createTableAdmin = """ CREATE TABLE ${ConstantsDB.ADM.DATANAME} (
        ${ConstantsDB.ADM.COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
        ${ConstantsDB.ADM.COLUMNS.NAME} TEXT,
        ${ConstantsDB.ADM.COLUMNS.EMAIL} TEXT,
        ${ConstantsDB.ADM.COLUMNS.PASSWORD} TEXT
    );"""


    private val dropTableAdmin = "DROP TABLE IF EXISTS  ${ConstantsDB.ADM.DATANAME}"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createTableAdmin)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        ADMIN
        db.execSQL(dropTableAdmin)
        db.execSQL(createTableAdmin)
    }

}