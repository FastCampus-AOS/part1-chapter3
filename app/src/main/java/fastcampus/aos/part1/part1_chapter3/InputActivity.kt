package fastcampus.aos.part1.part1_chapter3

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class InputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        val message = intent.getStringExtra("intentMessage") ?: "intent 값 없음"
        Log.d("InputActivity", message)
    }
}