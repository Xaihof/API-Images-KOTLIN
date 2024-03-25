package com.xoksis.apiimages.sqlite_classes

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ImageDatabaseHelper(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null,
    DATABASE_VERSION
) {

    companion object {

        private const val DATABASE_NAME = "image.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "allImages"
        private const val COLUMN_ID = "id"
        private const val COLUMN_IMAGE_LINK = "imageLink"
        private const val COLUMN_CATEGORY = "categoryId"

    }

    override fun onCreate(db: SQLiteDatabase?) {

        val createTabQuery =
            "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_IMAGE_LINK TEXT, $COLUMN_CATEGORY TEXT)"
        db?.execSQL(createTabQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertImage(image: Image) {

        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_IMAGE_LINK, image.imageLink)
            put(COLUMN_CATEGORY, image.catID)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllImages(): List<Image> {

        val imageList = mutableListOf<Image>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val imageLink = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE_LINK))
            val category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY))

            val image = Image(id, imageLink, category)

            imageList.add(image)
        }
        cursor.close()
        db.close()
        return imageList
    }

    fun deleteImage(imageId: Int) {

        val db = writableDatabase
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(imageId.toString())
        db.delete(TABLE_NAME, whereClause, whereArgs)
        db.close()
    }
}