package jp.summerintern.sushi.sushi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var time: Date
    private lateinit var sushiList: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun judge(val probs: List<Problem>)

    fun showOneResult()

    fun genProbs()

    fun updateResults()
}
