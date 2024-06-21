package com.example.mynews2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mynews2.models.Article

@Database(
    version = 1,
    entities=[Article::class]
)

@TypeConverters(Converters::class)


abstract class ArticleDatabase : RoomDatabase() {
    abstract fun getArticleDao(): ArticleDao

    companion object {
        fun getArticleDao(context: Context): ArticleDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            )
                .build()
        }
    }
}
