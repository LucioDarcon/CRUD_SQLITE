package com.example.controlsales.repository

import android.content.ContentValues
import android.content.Context
import com.example.controlsales.constants.ConstantsDB
import com.example.controlsales.database.DataBaseHelper
import com.example.controlsales.entities.Adm

class AdmRepository private constructor(context: Context) {

    private var mConnection: DataBaseHelper = DataBaseHelper(context)

    object RESULT {
        var result = 0
    }

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
        try {
            val db = mConnection.writableDatabase
            val insertValues = ContentValues()
            insertValues.put(ConstantsDB.ADM.COLUMNS.NAME, adm.name)
            insertValues.put(ConstantsDB.ADM.COLUMNS.EMAIL, adm.email)
            insertValues.put(ConstantsDB.ADM.COLUMNS.PASSWORD, adm.password)
            RESULT.result = db.insert(ConstantsDB.ADM.DATANAME, null, insertValues).toInt()
        } catch (e: Exception) {
             throw e
        }finally {
            return RESULT.result
        }
    }

}