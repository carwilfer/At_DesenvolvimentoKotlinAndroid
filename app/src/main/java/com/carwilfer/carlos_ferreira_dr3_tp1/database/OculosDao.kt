package com.carwilfer.carlos_ferreira_dr3_tp1.database

import androidx.room.*
import com.carwilfer.carlos_ferreira_dr3_tp1.model.Cliente
import com.carwilfer.carlos_ferreira_dr3_tp1.model.Oculos
import com.carwilfer.carlos_ferreira_dr3_tp1.model.OculosAndCliente
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

interface OculosDao {
    fun insert(oculos: Oculos): Long
    fun createOrUpdate(oculos: Oculos): Task<Void>
    fun read(id: String): Task<DocumentSnapshot>
    fun delete(oculos: Oculos): Task<Void>
    fun all(): CollectionReference
    fun allDocuments(): Task<QuerySnapshot>
    fun searchByArmacao(marcaArmacao: String): Task<QuerySnapshot>


    /*//insert
    @Insert
    suspend fun insert(oculos: Oculos): Long

    //update
    @Update
    suspend fun update(oculos: Oculos)

    //select
    @Transaction
    @Query("SELECT * FROM Oculos WHERE id = :key")
    suspend fun read(key: Long): OculosAndCliente

    @Query("SELECT * FROM Oculos WHERE id = :key")
    suspend fun readOculos(key: Long): Oculos

    //delete
    @Delete
    suspend fun delete(oculos: Oculos)

    //List
    @Query("SELECT * FROM Oculos")
    suspend fun all(): List<Oculos>*/

}