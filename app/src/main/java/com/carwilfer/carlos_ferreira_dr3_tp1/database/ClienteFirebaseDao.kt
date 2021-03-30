package com.carwilfer.carlos_ferreira_dr3_tp1.database

import android.graphics.Bitmap
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.io.ByteArrayOutputStream
import java.io.File

object ClienteFirebaseDao {

    val firebaseAuth = FirebaseAuth.getInstance()
    private val collection = FirebaseFirestore
        .getInstance()
        .collection("clientes")

    private val storageReference = FirebaseStorage
        .getInstance()
        .reference
        .child("Clientes")

    fun cadastrarImagem(uid: String, imagem: Bitmap): UploadTask {
        val outputStream = ByteArrayOutputStream()
        imagem.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        return storageReference.child("${uid}.jpeg")
            .putBytes(outputStream.toByteArray())
    }

    fun encerrarSessao() {
        firebaseAuth.signOut()
    }

    fun consultarCliente(): Task<DocumentSnapshot> {
        val firebaseUser = firebaseAuth.currentUser
        return collection                   // usuarios
            .document(firebaseUser!!.uid)
            .get()
    }

    fun consultarImagem(uid: String, file: File): FileDownloadTask {
        return storageReference
            .child("${uid}.jpeg")
            .getFile(file)
    }

}