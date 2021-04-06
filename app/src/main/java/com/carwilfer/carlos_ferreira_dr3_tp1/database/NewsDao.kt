package com.carwilfer.carlos_ferreira_dr3_tp1.database

import com.carwilfer.carlos_ferreira_dr3_tp1.model.News
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query

interface NewsDao {
    //fun insertOrUpdate(news: News) : Task<DocumentReference>
    fun delete(news: News) : Task<Void>
    fun all(): Query
    fun read(key: String): Query
    fun edit(news: News): Task<Void>
}