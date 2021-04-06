package com.carwilfer.carlos_ferreira_dr3_tp1.database

import com.carwilfer.carlos_ferreira_dr3_tp1.model.News
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class NewsFirestoreDao : NewsDao {
    private val collection = FirebaseFirestore.getInstance().collection("news")
    private val firebaseAuth = FirebaseAuth.getInstance()

    override  fun all(): Query {
        return collection.whereEqualTo("sourceId", firebaseAuth.currentUser.uid)
    }
    override fun read(key: String): Query {
        return collection.whereEqualTo("id", key!!)
    }
   /* override fun insertOrUpdate(news: News): Task<DocumentReference> {
        news.web_url = firebaseAuth.currentUser.uid
        return collection.add(news)
    }*/
    override  fun delete(news: News): Task<Void> {
        return collection.document(news.source!!).delete()
    }
    override fun edit(news: News): Task<Void> {
        return collection.document(news.source!!).set(news)
    }

}