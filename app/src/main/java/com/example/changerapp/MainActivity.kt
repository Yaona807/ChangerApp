package com.example.changerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeButton.setOnClickListener(this)
    }
    override fun onClick(view:View){
        if(yearEdit.text.toString().isNotEmpty() && yearEdit.text.toString().toInt() > 0) checkSpinner()
    }

    /* スピナーで選択されているものをチェックする関数 */
    private fun checkSpinner(){
        when {
            spinner.selectedItemId.toInt() == 0 -> {//西暦を選択していた場合
                changeYear()
            }
            spinner.selectedItemId.toInt() == 1 -> {//大正を選択していた場合
                changeEra(1)
            }
            spinner.selectedItemId.toInt() == 2 -> {//昭和を選択していた場合
                changeEra(2)
            }
            spinner.selectedItemId.toInt() == 3 -> {//平成を選択していた場合
                changeEra(3)
            }
            spinner.selectedItemId.toInt() == 4 -> {//令和を選択していた場合
                changeEra(4)
            }
        }
    }

    /* 西暦を元号へ変換する関数 */
    private fun changeYear(){
        val year:Int = yearEdit.text.toString().toInt()
        when {
            year in 1912..1925 -> {
                changeText.text = calcYear(year, 1912)
            }
            year in 1926..1988 -> {
                changeText.text = calcYear(year,1926)
            }
            year in 1989..2018 -> {
                changeText.text = calcYear(year,1989)
            }
            year >= 2019 -> {
                changeText.text = calcYear(year,2019)
            }
            else -> changeText.text = "error"//1912以上でない場合
        }
    }

    /* 西暦から元号の年を計算する関数 */
    private fun calcYear(year:Int, base:Int):String{
        val changeYear:Int = year - base + 1 //元号の年を計算
        return when(base){
            1912 -> {
                "大正"+changeYear.toString()+"年"
            }
            1926 -> {
                "昭和"+changeYear.toString()+"年"
            }
            1989 -> {
                "平成"+changeYear.toString()+"年"
            }
            else -> {
                "令和"+changeYear.toString()+"年"
            }
        }
    }

    /* 元号から西暦へ変換する関数 */
    private fun changeEra(num:Int){
        val eraCheck = arrayOf(14,63,30,0)
        var year:Int = 1911
        for(i in 1 until num){
            year += eraCheck[i-1]
        }
        year += yearEdit.text.toString().toInt()
        changeText.text = "西暦" + year + "年"
    }
}