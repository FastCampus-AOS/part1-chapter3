package fastcampus.aos.part1.part1_chapter3

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import fastcampus.aos.part1.part1_chapter3.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 혈액형 스피너
        binding.bloodTypeSpinner.adapter = ArrayAdapter.createFromResource(
            this@EditActivity,
            R.array.blood_types,
            android.R.layout.simple_list_item_1
        )

        // 캘린더 다이얼로그
        binding.birthdateLayer.setOnClickListener {
            DatePickerDialog(
                this@EditActivity,
                { _, year, month, dayOfMonth -> binding.birthdateValueTextView.text = "$year-${month.inc()}-$dayOfMonth" },
                2000, 1, 1
            ).show()
        }

        // 체크박스
        binding.cautionCheckBox.setOnCheckedChangeListener { _, isChecked ->
            binding.cautionEditText.isVisible = isChecked
        }

        binding.cautionEditText.isVisible = binding.cautionCheckBox.isChecked

        binding.saveButton.setOnClickListener {
            saveData()
            finish()
        }
    }

    private fun saveData() {
        with(getSharedPreferences(USER_INFORMATION, MODE_PRIVATE).edit()) {
            putString(NAME, binding.nameEditText.text.toString())
            putString(BLOOD_TYPE, getBloodType())
            putString(FIRST_RESPONDER, binding.firstResponderEditText.text.toString())
            putString(BIRTHDATE, binding.birthdateValueTextView.text.toString())
            putString(CAUTION, getCaution())
            apply()
        }
        Toast.makeText(this@EditActivity, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun getBloodType(): String {
        val bloodAlphabet = binding.bloodTypeSpinner.selectedItem.toString()
        val bloodSign = if(binding.bloodTypePlus.isChecked) "+" else "-"
        return "$bloodAlphabet$bloodSign"
    }

    private fun getCaution() : String {
        return if (binding.cautionCheckBox.isChecked) binding.cautionEditText.text.toString() else ""
    }
}