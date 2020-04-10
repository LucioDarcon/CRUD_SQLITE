package com.example.controlsales.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.controlsales.constants.ConstantsDB

class DataBaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATA_NAME, null,
    DATA_VERSION
) {

    companion object {
        private const val DATA_NAME = "control_sales.db"
        private const val DATA_VERSION = 1
    }

    private val createTableAdmin = """ CREATE TABLE ${ConstantsDB.ADM.DATA_NAME} (
        ${ConstantsDB.ADM.COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
        ${ConstantsDB.ADM.COLUMNS.NAME} TEXT,
        ${ConstantsDB.ADM.COLUMNS.EMAIL} TEXT,
        ${ConstantsDB.ADM.COLUMNS.PASSWORD} TEXT
    );"""


    private val createTableCustomer = """ CREATE TABLE ${ConstantsDB.CUSTOMER.DATA_NAME} (
        ${ConstantsDB.CUSTOMER.COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
        ${ConstantsDB.CUSTOMER.COLUMNS.ID_ADM} INTEGER,
        ${ConstantsDB.CUSTOMER.COLUMNS.NAME} TEXT,
        ${ConstantsDB.CUSTOMER.COLUMNS.EMAIL} TEXT,
        ${ConstantsDB.CUSTOMER.COLUMNS.TELEPHONE} TEXT,
        ${ConstantsDB.CUSTOMER.COLUMNS.CPF} TEXT,
        FOREIGN KEY (${ConstantsDB.CUSTOMER.COLUMNS.ID_ADM}) REFERENCES ${ConstantsDB.ADM.DATA_NAME} (${ConstantsDB.ADM.COLUMNS.ID})
        );"""

    private val dropTableAdmin = "DROP TABLE IF EXISTS  ${ConstantsDB.ADM.DATA_NAME}"
    private val dropTableCustomer = "DROP TABLE IF EXISTS  ${ConstantsDB.CUSTOMER.DATA_NAME}"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createTableAdmin)
        db.execSQL(createTableCustomer)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        ADMIN
        db.execSQL(dropTableAdmin)
        db.execSQL(dropTableCustomer)
        db.execSQL(createTableAdmin)
        db.execSQL(createTableCustomer)
    }

}