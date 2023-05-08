package ge.myprofile.mp.utils.keyBoardAnimation

import android.util.Log
import android.view.View
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsCompat


class InsetsWithKeyboardAnimationCallback(private val view: View) : WindowInsetsAnimationCompat.Callback(DISPATCH_MODE_STOP) {

    override fun onProgress(insets: WindowInsetsCompat, runningAnimations: MutableList<WindowInsetsAnimationCompat>): WindowInsetsCompat {
        val imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime())
        Log.d("log", "imeInsets  $imeInsets")
        val systemInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        Log.d("log", "systemInsets  $systemInsets")

        val keyboardHeight = insets!!.getInsets(WindowInsetsCompat.Type.ime()).bottom
        Log.d("log", "keyboardHeight  $keyboardHeight")


        // We calculate the difference of the ime() and systemBars() insets
        val diff = Insets.subtract(imeInsets, systemInsets).let {
            Insets.max(it, Insets.NONE)
        }
        Log.d("log", "diff  $diff")


        // The results from the diff insets contain the values and we apply them as a translation to the view
        view.translationX = (diff.left - diff.right).toFloat()
        Log.d("log", "translationX  ${view.translationX}")
        view.translationY = (diff.top - diff.bottom).toFloat() + 604F
        Log.d("log", "translationY  ${diff.top} ${diff.bottom} ${diff.top - diff.bottom}  ${view.translationY }  $keyboardHeight  ")

        return insets
    }

    override fun onEnd(animation: WindowInsetsAnimationCompat) {
        // We reset the translation values after the animation has finished
        view.translationX = 0f
        view.translationY = 0f
    }
}
