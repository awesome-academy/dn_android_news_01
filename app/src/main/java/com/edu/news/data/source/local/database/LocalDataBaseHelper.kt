package com.edu.news.data.source.local.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.edu.news.data.model.Category
import com.edu.news.data.model.CategoryType
import com.edu.news.data.source.OnFetchDataListener
import com.edu.news.data.source.local.OnPostDataListener

class LocalDataBaseHelper internal constructor(context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_CATEGORIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_CATEGORIES)
        onCreate(db)
    }

    fun saveCategories(
        listener: OnPostDataListener<Exception>,
        categoryTypes: MutableList<CategoryType>
    ) {
        try {
            val db = this@LocalDataBaseHelper.writableDatabase
            for (i in categoryTypes.indices) {
                val values = ContentValues().apply {
                    put(COLUMN_NAME, categoryTypes[i].nameType)
                    put(COLUMN_POSITION, i)
                    put(COLUMN_ENABLE, 1)
                }
                db.insert(TABLE_NAME, null, values)
            }
            db.close()
        } catch (e: SQLiteException) {
            listener.onError(e)
        }
    }

    fun getCategory(listener: OnFetchDataListener<MutableList<Category>>) {
        try {
            val db = this@LocalDataBaseHelper.readableDatabase
            val projection = arrayOf(COLUMN_NAME, COLUMN_POSITION, COLUMN_ENABLE)
            val selection = "$COLUMN_ENABLE = ?"
            val selectionArgs = arrayOf("1")
            val sortOrder = "$COLUMN_POSITION ASC"
            val cursor = db.query(
                TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
            )
            val categories = mutableListOf<Category>()
            with(cursor) {
                while (moveToNext()) {
                    val type = getString(getColumnIndex(COLUMN_NAME))
                    val position = getInt(getColumnIndex(COLUMN_POSITION))
                    val isEnable = getInt(getColumnIndex(COLUMN_ENABLE)) == 1
                    val categoryType = CategoryType.values().first { type == it.nameType }
                    categories.add(Category(categoryType, position, isEnable))
                }
            }
            cursor.close()
            listener.onSuccess(categories)
        } catch (e: SQLiteException) {
            listener.onError(e)
        }
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "news_01.db"
        const val TABLE_NAME = "category"
        const val COLUMN_NAME = "title"
        const val COLUMN_POSITION = "position"
        const val COLUMN_ENABLE = "enable"
        const val SQL_CREATE_CATEGORIES =
            "CREATE TABLE $TABLE_NAME (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "$COLUMN_NAME TEXT," +
                    "$COLUMN_POSITION INTEGER," +
                    "$COLUMN_ENABLE INTEGER)"
        const val SQL_DELETE_CATEGORIES = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}
