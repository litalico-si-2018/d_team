package jp.summerintern.sushi.sushi

import java.util.*

class Problem() {

    private var level:Int = 1
    private var leftNumber:Int = 0
    private var rightNumber:Int = 0
    private var count = 0
    var ans_flag:Boolean = false
    var sentence = ""

    init {
        count+=1
        var prob = Problem()
        this.ans_flag = (count%4 != Random().nextInt(4))
        prob.genSentence(this.ans_flag)

    }

    fun genSentence(ans_flag: Boolean) {

        leftNumber = Random().nextInt(8) + 1
        rightNumber = Random().nextInt(8) + 1

        if (ans_flag == true) {
            sentence = "${leftNumber} + ${rightNumber} = ${leftNumber + rightNumber}"
        }
        else {
            sentence = "${leftNumber} - ${rightNumber} = ${leftNumber + rightNumber}"
        }
        this.sentence = sentence
    }
}
