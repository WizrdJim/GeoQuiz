package online.wzrd.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

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
        setContentView(R.layout.activity_main)
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

        question_text_view.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.count()
            updateQuestion()
        }

        updateQuestion()
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
}
