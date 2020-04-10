package com.example.controlsales.repository

import android.content.ContentValues
import android.content.Context
import com.example.controlsales.constants.ConstantsDB
import com.example.controlsales.database.DataBaseHelper
import com.example.controlsales.dto.LoginDTO
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
            result = db.insert(ConstantsDB.ADM.DATA_NAME, null, insertValues).toInt()
        } catch (e: Exception) {
            throw e
        }
        return result
    }

    fun selectAdm(email: String): Boolean {
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
            val cursor = db.query(
                ConstantsDB.ADM.DATA_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
            )
            result = cursor.count > 0
            cursor.close()
        } catch (e: Exception) {
            throw e
        }
        return result
    }

    fun authAdmin(mLoginDTO: LoginDTO): ArrayList<Adm> {
        val arrayListAdmin = ArrayList<Adm>()
        val db = mConnection.readableDatabase
        val projection = arrayOf(
            ConstantsDB.ADM.COLUMNS.ID,
            ConstantsDB.ADM.COLUMNS.NAME,
            ConstantsDB.ADM.COLUMNS.EMAIL,
            ConstantsDB.ADM.COLUMNS.PASSWORD
        )
        val selection = "${ConstantsDB.ADM.COLUMNS.EMAIL} = ? AND ${ConstantsDB.ADM.COLUMNS.PASSWORD} = ?"
        val selectionArgs = arrayOf(mLoginDTO.email, mLoginDTO.password)
        val cursor = db.query(
            ConstantsDB.ADM.DATA_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        while (cursor.moveToNext()) {
            val adm = Adm(
                id = cursor.getInt(cursor.getColumnIndex(ConstantsDB.ADM.COLUMNS.ID)),
                name = cursor.getString(cursor.getColumnIndex(ConstantsDB.ADM.COLUMNS.NAME)).toUpperCase(),
                email = cursor.getString(cursor.getColumnIndex(ConstantsDB.ADM.COLUMNS.EMAIL)).toUpperCase()
            )
            arrayListAdmin.add(adm)
        }
        cursor.close()
        return arrayListAdmin
    }

}