package fastcampus.aos.part1.part1_chapter3

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import fastcampus.aos.part1.part1_chapter3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goInputActivityButton.setOnClickListener {
            val intent = Intent(this@MainActivity, EditActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        getDataUiUpdate()
    }

    private fun getDataUiUpdate() {
        with(getSharedPreferences(USER_INFORMATION, MODE_PRIVATE)) {
            binding.nameValueTextView.text = getString(NAME, "미정")
            binding.birthdateValueTextView.text = getString(BIRTHDATE, "미정")
            binding.bloodTypeValueTextView.text = getString(BLOOD_TYPE, "미정")
            binding.firstResponderValueTextView.text = getString(FIRST_RESPONDER, "미정")
            val caution = getString(CAUTION, "")
            binding.cautionValueTextView.isVisible = caution.isNullOrEmpty().not()
            binding.cautionTextView.isVisible = caution.isNullOrEmpty().not()
            binding.cautionValueTextView.text = if (caution.isNullOrEmpty().not()) caution else ""
        }
    }
}