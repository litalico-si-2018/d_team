package jp.summerintern.sushi.sushi

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit


/*
間違い探しの画面
 */

class MainActivity : AppCompatActivity() {

    private val maxTime: Int = 30 //とりあえず30秒にしているだけ
    private var seconds: Int = maxTime
    private lateinit var sushiList: List<String>
    private var probs = ArrayList<Problem>()
    private var answers = HashMap<Int, Boolean>()
    private var stage = Stage(1, 0, 0)
    private var sushiFlow = SushiFlow(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val submitButton: Button = findViewById(R.id.submit_button)

        for (i in 0 until 3) {
            val v: View = findViewById(resources.getIdentifier("prob" + i, "id", packageName))

            val cb = v.findViewById<Button>(R.id.correct_button)
            cb.setOnClickListener {
                v.findViewById<TextView>(R.id.answer).setText("○")
                answers[i] = true
            }

            val icb = v.findViewById<Button>(R.id.incorrect_button)
            icb.setOnClickListener {
                v.findViewById<TextView>(R.id.answer).setText("×")
                answers[i] = false
            }
        }

//        probListView.setOnItemClickListener { adapter, view, position, id ->
//            val correct_button=view.findViewById<Button>(R.id.correct_button)
//            correct_button.setOnClickListener {
//                answers[]
//            }
//            val incorrect_button=view.findViewById<Button>(R.id.incorrect_button)
//        }

        val timeBar = findViewById<ProgressBar>(R.id.remaining_time_bar)
        timeBar.max = maxTime
        timeBar.progress = maxTime

        submitButton.setOnClickListener {
            val result = judge()
            showOneResult(result)
        }

        Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .timeInterval()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (seconds == 0) {
                        genProbs()
                        seconds = maxTime
                        timeBar.progress = maxTime
                    } else {
                        seconds--
                        timeBar.progress = seconds
                    }
                })

        sushiFlow = SushiFlow(this)
        genProbs()
    }

    private fun judge(): Boolean {
        for (i in 0 until probs.size - 1) {
            if (answers[i] != probs[i].ans_flag) {
                return false
            }
        }
        return true
    }

    private fun showOneResult(result: Boolean) {
        sushiFlow.eatSushi(result, stage)
        if (result) {
            stage.updateResults(1)
//            Toast.makeText(this, "正解！", Toast.LENGTH_SHORT).show()
            genProbs()
        } else {
            stage.updateResults(0)
//            Toast.makeText(this, "残念！", Toast.LENGTH_SHORT).show()
        }
    }

    private fun genProbs() {

        probs.clear()
        answers.clear()
        sushiFlow.resetSushi()

        for (i in 0 until 3) {
            val p = Problem()
            probs.add(p)

            val v: View = findViewById(resources.getIdentifier("prob" + i, "id", packageName))
            v.findViewById<TextView>(R.id.answer).setText("　")
            v.findViewById<TextView>(R.id.problem_sentence).setText(p.sentence)
        }

        sushiFlow.flowSushi()
    }
}
