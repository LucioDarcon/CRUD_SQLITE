package com.example.controlsales.repository

import android.content.ContentValues
import android.content.Context
import com.example.controlsales.constants.ConstantsDB
import com.example.controlsales.database.DataBaseHelper
import com.example.controlsales.entities.Customer
import com.example.controlsales.util.SecurityPreferences

class CustomerRepository private constructor(context: Context) {

    private val mConnection = DataBaseHelper(context)
    private val mSecurityPreferences = SecurityPreferences(context)

    companion object {
        fun getInstance(context: Context): CustomerRepository {
            if (INSTANCE == null) {
                INSTANCE = CustomerRepository(context)
            }
            return INSTANCE as CustomerRepository
        }

        private var INSTANCE: CustomerRepository? = null
    }

    fun insertCustomer(mCustomer: Customer): Int {
        var result = 0
        val db = mConnection.writableDatabase
        val contentValues = ContentValues()
        val idAdm = mSecurityPreferences.getStoredString("idAdm")
        contentValues.put(ConstantsDB.CUSTOMER.COLUMNS.NAME, mCustomer.name)
        contentValues.put(ConstantsDB.CUSTOMER.COLUMNS.EMAIL, mCustomer.email)
        contentValues.put(ConstantsDB.CUSTOMER.COLUMNS.TELEPHONE, mCustomer.telephone)
        contentValues.put(ConstantsDB.CUSTOMER.COLUMNS.CPF, mCustomer.cpf)
        contentValues.put(ConstantsDB.CUSTOMER.COLUMNS.IMAGE, mCustomer.image)
        contentValues.put(ConstantsDB.CUSTOMER.COLUMNS.ID_ADM, idAdm?.toInt())
        result = db.insert(ConstantsDB.CUSTOMER.DATA_NAME, null, contentValues).toInt()
        return result
    }

    fun getAllCustomer(): ArrayList<Customer> {
        val arrayListCustomer = ArrayList<Customer>()
        val db = mConnection.readableDatabase
        val projection = arrayOf(
            ConstantsDB.CUSTOMER.COLUMNS.ID,
            ConstantsDB.CUSTOMER.COLUMNS.NAME,
            ConstantsDB.CUSTOMER.COLUMNS.EMAIL,
            ConstantsDB.CUSTOMER.COLUMNS.TELEPHONE,
            ConstantsDB.CUSTOMER.COLUMNS.CPF,
            ConstantsDB.CUSTOMER.COLUMNS.IMAGE
        )
        val idAdm = mSecurityPreferences.getStoredString("idAdm")?.toInt()
        val selection = "${ConstantsDB.CUSTOMER.COLUMNS.ID_ADM} = ?"
        val selectionArgs = arrayOf(idAdm.toString())
        val orderBy = ConstantsDB.CUSTOMER.COLUMNS.ID
        val cursor = db.query(ConstantsDB.CUSTOMER.DATA_NAME, projection, selection, selectionArgs, null, null, orderBy)

        while(cursor.moveToNext()){
            val customer = Customer(
                id = cursor.getInt(cursor.getColumnIndex(ConstantsDB.CUSTOMER.COLUMNS.ID)),
                name = cursor.getString(cursor.getColumnIndex(ConstantsDB.CUSTOMER.COLUMNS.NAME)),
                email = cursor.getString(cursor.getColumnIndex(ConstantsDB.CUSTOMER.COLUMNS.EMAIL)),
                telephone = cursor.getString(cursor.getColumnIndex(ConstantsDB.CUSTOMER.COLUMNS.TELEPHONE)),
                cpf = cursor.getString(cursor.getColumnIndex(ConstantsDB.CUSTOMER.COLUMNS.CPF)),
                image = cursor.getString(cursor.getColumnIndex(ConstantsDB.CUSTOMER.COLUMNS.IMAGE))
            )
            arrayListCustomer.add(customer)
        }
        cursor.close()
        return arrayListCustomer
    }

    fun alterCustomer(mCustomer: Customer): Int{
        val db = mConnection.writableDatabase
        val selection = "${ConstantsDB.CUSTOMER.COLUMNS.ID} = ?"
        val selectionArgs = arrayOf(mCustomer.id.toString())
        val contentValues = ContentValues()
        contentValues.put(ConstantsDB.CUSTOMER.COLUMNS.NAME, mCustomer.name)
        contentValues.put(ConstantsDB.CUSTOMER.COLUMNS.EMAIL, mCustomer.email)
        contentValues.put(ConstantsDB.CUSTOMER.COLUMNS.TELEPHONE, mCustomer.telephone)
        contentValues.put(ConstantsDB.CUSTOMER.COLUMNS.CPF, mCustomer.cpf)
        contentValues.put(ConstantsDB.CUSTOMER.COLUMNS.IMAGE, mCustomer.image)

        return db.update(ConstantsDB.CUSTOMER.DATA_NAME, contentValues,selection, selectionArgs).toInt()
    }

    fun getCustomer(id: String): Customer{
        val db = mConnection.writableDatabase
        val projection = arrayOf(
            ConstantsDB.CUSTOMER.COLUMNS.NAME,
            ConstantsDB.CUSTOMER.COLUMNS.EMAIL,
            ConstantsDB.CUSTOMER.COLUMNS.TELEPHONE,
            ConstantsDB.CUSTOMER.COLUMNS.CPF,
            ConstantsDB.CUSTOMER.COLUMNS.IMAGE
        )
        val selection = "${ConstantsDB.CUSTOMER.COLUMNS.ID} = ?"
        val selectionArgs = arrayOf(id)
        val orderBy = ConstantsDB.CUSTOMER.COLUMNS.ID
        val cursor = db.query(ConstantsDB.CUSTOMER.DATA_NAME, projection, selection, selectionArgs, null, null, orderBy)
        var customer = Customer()
        while (cursor.moveToNext()){
            customer =
                Customer(name = cursor.getString(cursor.getColumnIndex(ConstantsDB.CUSTOMER.COLUMNS.NAME)),
                email = cursor.getString(cursor.getColumnIndex(ConstantsDB.CUSTOMER.COLUMNS.EMAIL)),
                telephone = cursor.getString(cursor.getColumnIndex(ConstantsDB.CUSTOMER.COLUMNS.TELEPHONE)),
                cpf = cursor.getString(cursor.getColumnIndex(ConstantsDB.CUSTOMER.COLUMNS.CPF)),
                image = cursor.getString(cursor.getColumnIndex(ConstantsDB.CUSTOMER.COLUMNS.IMAGE)))
        }
        return customer
    }

    fun deleteCustomer(id: String): Int{
        val db = mConnection.writableDatabase
        val selection = "${ConstantsDB.CUSTOMER.COLUMNS.ID} = ?"
        val selectionArgs = arrayOf(id)
        return db.delete(ConstantsDB.CUSTOMER.DATA_NAME, selection, selectionArgs)
    }

}
