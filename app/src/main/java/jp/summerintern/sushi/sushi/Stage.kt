package jp.summerintern.sushi.sushi

import android.app.Activity
import android.widget.TextView

class Stage(val level: Int, var numProbs: Int, var numCorrect: Int) {

    fun updateResults( numCorrect: Int) {
        this.numProbs += 1
        this.numCorrect += numCorrect
    }

}