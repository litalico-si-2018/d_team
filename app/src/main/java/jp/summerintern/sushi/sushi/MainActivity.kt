package jp.summerintern.sushi.sushi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var time: Date
    private lateinit var sushiList: List<String>
    private lateinit var ansList:List<Int>
    private lateinit var probs:List<String>
    private  var isTrue:Int = 0
    private  lateinit var prob:String
    private  var level:Int = 0
    private  var submitList =  arrayOfNulls<Int>(level)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun judge():Boolean {


        if (ansList == submitList ){
            return true

        }
        else{
            return false
        }



    }

    fun showOneResult(){




    }



    fun genProbs(level: Int) {

        for (i in 0..level) {
            isTrue = Random().nextInt(1)
            if (isTrue == 0){
                val problem = Problem("aaa")
                prob = problem.genSentence(true)
                probs.plus(prob)
                ansList.plus(0)
            }

            else{
                val problem = Problem("aaa")
                prob = problem.genSentence(false)
                probs.plus(prob)
                ansList.plus(1)

            }


        }


    }

    fun updateResults()
}
