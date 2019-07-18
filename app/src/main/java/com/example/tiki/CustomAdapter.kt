package com.example.tiki

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item.view.*
import java.util.*
import kotlin.collections.ArrayList

class CustomAdapter(var items: ArrayList<String>, var context: Context) :
    RecyclerView.Adapter<KeywordViewHolder>() {


    fun Hamtachchuoi(chuoivao: String): String? {
        var arr: List<String> = chuoivao.split(" ")

        println("so phan tu" + arr.size)

        var a = arr[0].length
        var b: Int
        var tong: Int = arr[0].length
        var c: Int
        var duong: Int = 0
        var am: Int = 0
        var vitriduong = 0
        var vitriam = 0
        var dong1: String? = null
        var dong2: String? = null
        var ghepchuoi: String? = null

        if(arr.size > 1) {
            for (i in 1..arr.size) {
                tong += arr[i].length
            }
            for (j in 1..arr.size) {
                a += arr[j].length
                b = tong - a
                c = b - a
                if (c > 0) {
                    duong = c
                    vitriduong = j
                } else {
                    am = c
                    vitriam = j
                    break
                }
            }
            if (Math.abs(duong) < Math.abs(am)) {

                for (h in 0..vitriduong) {
                    dong1 += arr[h] + " "
                }

                for (k in vitriduong..arr.size) {
                    dong2 += arr[k] + " "
                }

                ghepchuoi = dong1 + "\n" + dong2

            } else {
                for (l in 0..vitriam) {
                    dong1 += arr[l] + " "
                }
                for (m in vitriam..arr.size) {
                    dong2 += arr[m] + " "
                }
                ghepchuoi = dong1 + "\n" + dong2
            }
        }
        else{
            ghepchuoi = chuoivao
        }
        return ghepchuoi
    }


    fun updateData(items: ArrayList<String>) {
        this.items = items
        this.notifyDataSetChanged()
    }


    private fun getRandomColor(): Int {
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

    override fun onBindViewHolder(keywordViewHolder: KeywordViewHolder, position: Int) {

        keywordViewHolder.nameKeyword.text = Hamtachchuoi(items[position])
        keywordViewHolder.background.setBackgroundColor(getRandomColor())
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): KeywordViewHolder {
        return KeywordViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class KeywordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var background = view.bg
    var nameKeyword = view.tvName
}