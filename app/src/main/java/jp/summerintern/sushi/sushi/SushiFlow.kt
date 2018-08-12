package jp.summerintern.sushi.sushi

import android.app.Activity
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class SushiFlow(val activity: Activity) {
    private var counter: Float = 0.0F
    private var t: Float = 0.0F
    private val handDuration: Int = 2000
    private val sushiDuration: Long = 10L     // 寿司の一回の移動時間。色々試す。
    private lateinit var sushiMoveAnimation: Animation
    private lateinit var godhandAppearAnimation: TranslateAnimation
    private lateinit var godhandDisappearAnimation: TranslateAnimation
    private lateinit var sushiImageView: ImageView

    init {
        Observable.interval(100L, TimeUnit.MILLISECONDS)
                .timeInterval()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    counter += 0.1F
                })
    }

    val sushi: String = "たまご"

    fun flowSushi() {
        startSushiTranslate()
    }

    fun eatSushi(result_flag: Boolean, stage: Stage) {
        startGodhandTranslate(calcHandX(), result_flag, stage) // DoubleをFloatへ変換する必要あり
    }

    fun resetSushi() {
        // flowSushiで行われているアニメーションをキャンセルする
        sushiMoveAnimation = AnimationUtils.loadAnimation(activity, R.anim.sushi_horizontal_move)
        sushiMoveAnimation.reset()
        counter = 0.0F
    }

    fun calcHandX(): Float {
        t = counter
        val d = handDuration * 0.001

        // vは寿司の速度
        val v = 100 / sushiDuration //sushiDurationは秒（ミリ秒でない）．100はパーセンテージ．

        val x = v * (t + d)
        return x.toFloat()
    }

    private fun changePlateNum(n: Int) {
        val plates = activity.findViewById<ImageView>(R.id.plate_images)

        if (n == 0) {
            plates.visibility = View.INVISIBLE
        } else {
            plates.visibility = View.VISIBLE
            val imageRes = activity.resources.getIdentifier("plates_" + n, "drawable", null)
            plates.setImageResource(imageRes)
        }
    }

    private fun startSushiTranslate() {

        sushiMoveAnimation = AnimationUtils.loadAnimation(activity, R.anim.sushi_horizontal_move);

        sushiImageView = activity.findViewById(R.id.sushi_flow)


//        val set = AnimationSet(true)

        sushiImageView.startAnimation(sushiMoveAnimation)

//        set.addAnimation(sushiMoveAnimation)
//        sushiMoveAnimation.setAnimationListener(object : Animation.AnimationListener {
//            override fun onAnimationEnd(animation: Animation) {
//                imageView.startAnimation(sushiMoveAnimation)
//            }
//        })

//        imageView.startAnimation(set)

    }

    private fun startGodhandTranslate(startX: Float, result_flag: Boolean, stage: Stage) {

        val imageView = activity.findViewById<ImageView>(R.id.godhand)
        imageView.visibility = View.VISIBLE
        // 設定を切り替え可能
        var startX = startX
        val set = AnimationSet(true)

        // TranslateAnimation(int fromXType, float fromXValue, int toXType, float toXValue, int fromYType, float fromYValue, int toYType, float toYValue)
        godhandAppearAnimation = TranslateAnimation(
                Animation.ABSOLUTE, startX,
                Animation.ABSOLUTE, startX,
                Animation.ABSOLUTE, 200.0f,
                Animation.ABSOLUTE, -1400.0f
        )
        // animation時間 msec
        godhandAppearAnimation.setDuration(1000)
        // 繰り返し回数
        godhandAppearAnimation.setRepeatCount(0)
        // animationが終わったそのまま表示にする
        godhandAppearAnimation.setFillAfter(true)

        godhandDisappearAnimation = TranslateAnimation(
                Animation.ABSOLUTE, startX,
                Animation.ABSOLUTE, startX,
                Animation.ABSOLUTE, -1400.0f,
                Animation.ABSOLUTE, 200.0f
        )
        // animation時間 msec
        godhandDisappearAnimation.setDuration(2000)
        // 繰り返し回数
        godhandDisappearAnimation.setRepeatCount(0)
        // animationが終わったそのまま表示にする
        godhandDisappearAnimation.setFillAfter(true)

        set.addAnimation(godhandAppearAnimation)
        godhandAppearAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onAnimationStart(p0: Animation?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onAnimationEnd(animation: Animation) {
                printFeedbackAnswer(result_flag)

                if (result_flag) {
                    resetSushi()
                    changePlateNum(stage.numCorrect)
                }
                godhandDisappearAnimation.setAnimationListener(object :Animation.AnimationListener{
                    override fun onAnimationEnd(p0: Animation?) {
                        deleteFeedbackAnswer()
                    }

                    override fun onAnimationRepeat(p0: Animation?) {
//                        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onAnimationStart(p0: Animation?) {
//                        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }
                })

                imageView.startAnimation(godhandDisappearAnimation)
                imageView.visibility = View.GONE
            }
        })

//        imageView.startAnimation(godhandAppearAnimation)
        imageView.startAnimation(set)
    }

    private fun printFeedbackAnswer(result_flag: Boolean) {
        val imageView = activity.findViewById<ImageView>(R.id.answer_feedback)
        if (result_flag) {
//            imageView.setImageResource(R.drawable.correct_circle)

            imageView.visibility = View.VISIBLE
        } else {
//            imageView.setImageResource(R.drawable.incorrect_cross)
//            imageView.visibility = View.VISIBLE
        }
    }

    private fun deleteFeedbackAnswer() {
        val imageView = activity.findViewById<ImageView>(R.id.answer_feedback)
        imageView.visibility = View.GONE
    }
}