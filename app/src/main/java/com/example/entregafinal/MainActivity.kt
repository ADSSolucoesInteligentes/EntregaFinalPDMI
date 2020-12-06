package com.example.entregafinal


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

import    org.jetbrains.anko.startActivity
import java.text.NumberFormat
import java.util.*

import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val AlunosAdapter = AlunosAdapter(this)
        val listViewAluno = findViewById<ListView>(R.id.listViewAlunos)

        listViewAluno.adapter = AlunosAdapter

        btnAdicionar.setOnClickListener {
            //val intent = Intent(this, CadastroActivity::class.java)
            //startActivity(intent)

            startActivity<CadastroActivity>()
        }

        listViewAlunos.setOnItemClickListener { adapterView: AdapterView<*>, view, position: Int, id ->
            val item = AlunosAdapter.getItem(position)
            AlunosAdapter.remove(item)
        }
    }

    override fun onResume() {
        super.onResume()

        val adapter = listViewAlunos.adapter as AlunosAdapter
        database.use {

            select("Alunos").exec {

                val parser = rowParser { id: Int,
                                         nome: String,
                                         media: Double,
                                         nota1: Double,
                                         nota2: Double,
                                         nota3: Double,
                                         nota4: Double,
                                         fotoAluno: ByteArray? ->
                    //Colunas do banco de dados
                    Aluno (id, nome, media, nota1, nota2, nota3, nota4, fotoAluno?.toBitmap())
                }


                var listaAluno = parseList(parser)

                //limpado os dados da lista e carregando as novas informações
                adapter.clear()
                adapter.addAll(listaAluno)


                }


            }
        }
}




