package com.example.controlsales.repository

import android.content.ContentValues
import android.content.Context
import com.example.controlsales.constants.ConstantsDB
import com.example.controlsales.database.DataBaseHelper
import com.example.controlsales.entities.Adm

class AdmRepository private constructor(context: Context) {

    private var mConnection: DataBaseHelper = DataBaseHelper(context)

    companion object {
        fun getInstance(context: Context): AdmRepository {
            if (INSTANCE == null) {
                INSTANCE = AdmRepository(context)
            }
            return INSTANCE as AdmRepository
        }

        private var INSTANCE: AdmRepository? = null
    }

    fun insertAdm(adm: Adm): Int {
        var result = 0
        try {
            val db = mConnection.writableDatabase
            val insertValues = ContentValues()
            insertValues.put(ConstantsDB.ADM.COLUMNS.NAME, adm.name)
            insertValues.put(ConstantsDB.ADM.COLUMNS.EMAIL, adm.email)
            insertValues.put(ConstantsDB.ADM.COLUMNS.PASSWORD, adm.password)
            result = db.insert(ConstantsDB.ADM.DATANAME, null, insertValues).toInt()
        } catch (e: Exception) {
            throw e
        }
        return result
    }

    fun selectAdm(email: String): Boolean {
        var listAdm = ArrayList<Adm>()
        var result = false
        try {
            val db = mConnection.readableDatabase
            val projection = arrayOf(
                ConstantsDB.ADM.COLUMNS.ID,
                ConstantsDB.ADM.COLUMNS.NAME,
                ConstantsDB.ADM.COLUMNS.EMAIL,
                ConstantsDB.ADM.COLUMNS.PASSWORD
            )
            val selection = "${ConstantsDB.ADM.COLUMNS.EMAIL} = ?"
            val selectionArgs = arrayOf(email)
            var cursor = db.query(
                ConstantsDB.ADM.DATANAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
            )

            result = cursor.count > 0

        } catch (e: Exception) {
            throw e
        }
        return result
    }

}