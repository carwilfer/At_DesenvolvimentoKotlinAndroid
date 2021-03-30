package com.carwilfer.carlos_ferreira_dr3_tp1.database

import android.graphics.Bitmap
import com.carwilfer.carlos_ferreira_dr3_tp1.model.Vendedor
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.io.ByteArrayOutputStream
import java.io.File

object VendedorFirebaseDao {
    val firebaseAuth = FirebaseAuth.getInstance()
    private val collection = FirebaseFirestore.getInstance().collection("vendedores")

    private val storageReference = FirebaseStorage.getInstance().reference.child("Vendedor")

    fun cadastrarCredenciais(email: String, senha: String): Task<AuthResult> {
        return firebaseAuth.createUserWithEmailAndPassword(email, senha)
    }

    fun cadastrarPerfil(uid: String, nome: String, telefone: String): Task<Void> {
        return collection.document(uid).set(Vendedor(nome, telefone))
    }

    fun cadastrarImagem(uid: String, imagem: Bitmap): UploadTask {
        val outputStream = ByteArrayOutputStream()
        imagem.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        return storageReference.child("${uid}.jpeg").putBytes(outputStream.toByteArray())
    }

    fun verificarCredenciais(email: String, senha: String): Task<AuthResult> {
        return firebaseAuth.signInWithEmailAndPassword(email, senha)
    }

    fun encerrarSessao() {
        firebaseAuth.signOut()
    }

    fun consultarVendedores(): Task<DocumentSnapshot> {
        val firebaseUser = firebaseAuth.currentUser
        return collection                   // vendedores
                .document(firebaseUser!!.uid).get()
    }

    fun consultarImagem(uid: String, file: File): FileDownloadTask {
        return storageReference.child("${uid}.jpeg").getFile(file)
    }
}