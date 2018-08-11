package jp.summerintern.sushi.sushi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val stage = Stage(1, 1, 1)

        var numProbs: TextView = findViewById(R.id.num_probs)
        numProbs.text = "ながれたおすし" + stage.numProbs.toString()

        var numCorrect: TextView = findViewById(R.id.num_correct)
        numCorrect.text = "たべたおすし" + stage.numCorrect.toString()

//        val myListView = findViewById(R.id.numProbs)
//        Log.d("SUSHI", numProbs?.toString())
    }


}
