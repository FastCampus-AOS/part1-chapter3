package fastcampus.aos.part1.part1_chapter3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import fastcampus.aos.part1.part1_chapter3.databinding.ActivityMainBinding
import androidx.core.net.toUri

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

        binding.deleteButton.setOnClickListener {
            deleteData()
        }

        binding.firstResponderLayer.setOnClickListener {
            with(Intent(Intent.ACTION_DIAL)) {
                val phoneNumber = binding.firstResponderValueTextView.text.toString().replace("-", "")
                data = "tel:$phoneNumber".toUri()
                startActivity(this)
            }
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

    private fun deleteData() {
        with(getSharedPreferences(USER_INFORMATION, MODE_PRIVATE).edit()) {
            clear()
            apply()
            getDataUiUpdate()
        }
        Toast.makeText(this@MainActivity, "초기화를 완료했습니다.", Toast.LENGTH_SHORT).show()
    }
}