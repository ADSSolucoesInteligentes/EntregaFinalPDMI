package com.example.entregafinal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class AlunosAdapter (contexto: Context) : ArrayAdapter<Aluno>(contexto, 0)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup):
            View {
        val v: View
        if(convertView != null){
            v = convertView
        }
        else{
            v = LayoutInflater.from(context).inflate(R.layout.list_view_item, parent, false)
        }
        val item = getItem(position)
        val txtNomeAluno = v.findViewById<TextView>(R.id.txtNomeAluno)
        val imgProduto = v.findViewById<ImageView>(R.id.imgAlunoFoto)
        val txtMedia = v.findViewById<TextView>(R.id.txtMedia)
        val txtNotaB1 = v.findViewById<TextView>(R.id.txtNotaB1)
        val txtNotaB2 = v.findViewById<TextView>(R.id.txtNotaB2)
        val txtNotaB3 = v.findViewById<TextView>(R.id.txtNotaB3)
        val txtNotaB4 = v.findViewById<TextView>(R.id.txtNotaB4)

        txtNomeAluno.text = item?.nome
        txtMedia.text = item?.media.toString()
        txtNotaB1.text = item?.notaB1.toString()
        txtNotaB2.text = item?.notaB2.toString()
        txtNotaB3.text = item?.notaB3.toString()
        txtNotaB4.text = item?.notaB4.toString()

        if (item?.foto != null) {
            imgProduto.setImageBitmap(item.foto)
        }
        return v
    }
}