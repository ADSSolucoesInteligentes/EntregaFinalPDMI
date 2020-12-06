package com.example.entregafinal

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_cadastro.txtNomeAluno
import kotlinx.android.synthetic.main.activity_cadastro.txtNotaB1
import kotlinx.android.synthetic.main.activity_cadastro.txtNotaB2
import kotlinx.android.synthetic.main.activity_cadastro.txtNotaB3
import kotlinx.android.synthetic.main.activity_cadastro.txtNotaB4
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_view_item.*
import org.jetbrains.anko.toast
import org.jetbrains.anko.db.insert

//import kotlinx.android.synthetic.main.activity_cadastro.*
///import kotlinx.android.synthetic.main.list_view_item.*


class CadastroActivity : AppCompatActivity() {

    val COD_IMAGE = 101
    var imageBitMap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        btnInserir.setOnClickListener {
            val aluno = txtNomeAluno.text.toString()
            val notaB1 = txtNotaB1.text.toString()
            val notaB2 = txtNotaB2.text.toString()
            val notaB3 = txtNotaB3.text.toString()
            val notaB4 = txtNotaB4.text.toString()
            val mediaAluno: Double


            mediaAluno = media(notaB1.toDouble(), notaB2.toDouble(), notaB3.toDouble(), notaB4.toDouble())



            if (aluno.isNotEmpty() && notaB1.isNotEmpty() && notaB2.isNotEmpty() && notaB3.isNotEmpty() && notaB4.isNotEmpty()) {

                database.use {
                    val idAluno = insert(
                        "MediasAlunos",
                        "nome" to aluno,
                        "media" to mediaAluno,
                        "nota1" to notaB1,
                        "nota2" to notaB1,
                        "nota3" to notaB3,
                        "nota4" to notaB4,
                        "fotoAluno" to imageBitMap?.toBYteArray()
                    )

                    if (idAluno != -1L) {
                        toast("Notas inseridas com sucesso")
                        txtNomeAluno.text.clear()
                        txtNotaB1.text.clear()
                        txtNotaB4.text.clear()
                        txtNotaB2.text.clear()
                        txtNotaB3.text.clear()
                    } else{
                        toast("Erro ao inserir no banco de dados")
                    }
                }


            } else {
                txtNomeAluno.error =
                    if (txtNomeAluno.text.isEmpty()) "Preencha o nome do Aluno" else null
                txtNotaB1.error = if (txtNotaB1.text.isEmpty()) "Preencha a nota da B1" else null
                txtNotaB2.error = if (txtNotaB2.text.isEmpty()) "Preencha a nota da B2" else null
                txtNotaB3.error = if (txtNotaB3.text.isEmpty()) "Preencha a nota da B3" else null
                txtNotaB4.error = if (txtNotaB4.text.isEmpty()) "Preencha a nota da B4" else null
            }

        }

        btnEditar.setOnClickListener {


            val aluno = txtNomeAluno.text.toString()
            val notaB1 = txtNotaB1.text.toString()
            val notaB2 = txtNotaB2.text.toString()
            val notaB3 = txtNotaB3.text.toString()
            val notaB4 = txtNotaB4.text.toString()
            val mediaAluno:Double
            mediaAluno = media(notaB1.toDouble(), notaB2.toDouble(), notaB3.toDouble(), notaB4.toDouble())

            if (aluno.isNotEmpty() && notaB1.isNotEmpty() && notaB2.isNotEmpty() && notaB3.isNotEmpty() && notaB4.isNotEmpty()) {

                database.use {
                    val idProduto = insert(
                            "MediasAluno",
                            "nome" to aluno,
                            "media" to mediaAluno,
                            "nota1" to notaB1,
                            "nota2" to notaB1,
                            "nota3" to notaB3,
                            "nota4" to notaB4,
                            "fotoAluno" to imageBitMap?.toBYteArray()
                    )

                    if (idProduto != -1L) {
                        toast("Notas inseridas com sucesso")
                        txtNomeAluno.text.clear()
                        txtNotaB1.text.clear()
                        txtNotaB4.text.clear()
                        txtNotaB2.text.clear()
                        txtNotaB3.text.clear()
                    } else{
                        toast("Erro ao inserir no banco de dados")
                    }
                }


            } else {
                txtNomeAluno.error =
                        if (txtNomeAluno.text.isEmpty()) "Preencha o nome do Aluno" else null
                txtNotaB1.error = if (txtNotaB1.text.isEmpty()) "Preencha a nota da B1" else null
                txtNotaB2.error = if (txtNotaB2.text.isEmpty()) "Preencha a nota da B2" else null
                txtNotaB3.error = if (txtNotaB3.text.isEmpty()) "Preencha a nota da B3" else null
                txtNotaB4.error = if (txtNotaB4.text.isEmpty()) "Preencha a nota da B4" else null
            }

        }

        imgAlunoFoto.setOnClickListener {
            abrirGaleria()
        }
    }

    fun abrirGaleria() {
        //definindo a ação de conteudo
        val intent = Intent(Intent.ACTION_GET_CONTENT)

        //definindo filtro para imagens
        intent.type = "image/*"

        //inicializando a activity com resultado
        startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), COD_IMAGE)

    }
}

fun media(nota1: Double, nota2: Double, nota3: Double, nota4: Double): Double {
    var med: Double
    med = (nota1 + nota2 + nota3 + nota4)/4
    return med
    }


