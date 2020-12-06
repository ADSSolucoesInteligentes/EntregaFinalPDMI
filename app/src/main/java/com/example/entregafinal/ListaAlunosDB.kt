package com.example.entregafinal

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*


// Acesso a propriedade pelo contexto
val Context.database: ListaAlunosDatabase
    get() = ListaAlunosDatabase.getInstance(getApplicationContext())


class ListaAlunosDatabase(context: Context) : ManagedSQLiteOpenHelper(
        ctx = context,
        name = "listaCompras.db", version = 1
) {

    //singleton da classe
    companion object {
        private var instance: ListaAlunosDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): ListaAlunosDatabase {
            if (instance == null) {
                instance = ListaAlunosDatabase(ctx.getApplicationContext())
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        //criacao de tabelas

        db.createTable("Alunos", true,
                "id" to INTEGER + PRIMARY_KEY + UNIQUE,
                "nome" to TEXT,
                "media" to REAL,
                "nota1" to REAL,
                "nota2" to REAL,
                "nota3" to REAL,
                "nota4" to REAL,
                "foto" to BLOB)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //atualizando do banco de dados
    }

}