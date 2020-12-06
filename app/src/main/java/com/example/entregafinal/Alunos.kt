package com.example.entregafinal

import android.graphics.Bitmap
import kotlinx.android.synthetic.main.activity_cadastro.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.toast

data class Aluno (
        val id: Int,
        val nome: String,
        val media: Double,
        val notaB1: Double,
        val notaB2: Double,
        val notaB3: Double,
        val notaB4: Double,
        val foto: Bitmap? = null)
