package online.wzrd.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.util.Log

val questionBank : List<Question> = listOf(
    Question(R.string.question_australia, true),
    Question(R.string.question_oceans, true),
    Question(R.string.question_mideast, false),
    Question(R.string.question_africa, false),
    Question(R.string.question_americas, true),
    Question(R.string.question_asia, true)
)

var currentIndex = 0


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle) called")
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0)
        }

        true_button.setOnClickListener {
            checkAnswer(true)
        }

        false_button.setOnClickListener {
            checkAnswer(false)
        }

        next_button.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.count()
            updateQuestion()
        }

        previous_button.setOnClickListener {
            currentIndex = when(currentIndex){
                0 -> 6
                else -> (currentIndex - 1)
            }

            updateQuestion()
        }

        question_text_view.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.count()
            updateQuestion()
        }

        cheat_button.setOnClickListener {
            // Start Cheat Activity
        }

        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX, currentIndex)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion() {
        val question = questionBank[currentIndex].textResId
        question_text_view.text = getString(question)
    }

    private fun checkAnswer(userPressedTrue : Boolean) {
        val answerIsTrue = questionBank[currentIndex].isAnswerTrue

        val messageResId = when (userPressedTrue == answerIsTrue) {
            true ->  R.string.correct_toast
            false ->  R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()
    }

    private val TAG = "QuizActivity"
    private val KEY_INDEX = "index"

}
