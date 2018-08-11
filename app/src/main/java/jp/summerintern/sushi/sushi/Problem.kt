package jp.summerintern.sushi.sushi

import java.util.*

class Problem(var sentence: String) {



    private var leftNumber:Int = 0
    private var rightNumber:Int = 0


    fun genSentence(ans_flag: Boolean): String {

        leftNumber = Random().nextInt(8) + 1
        rightNumber = Random().nextInt(8) + 1

        if (ans_flag == true){

            sentence = "${leftNumber} + ${rightNumber} = ${leftNumber + rightNumber}"

        }
        else {
            sentence = "${leftNumber} - ${rightNumber} = ${leftNumber + rightNumber}"
        }

        return sentence

    }


}