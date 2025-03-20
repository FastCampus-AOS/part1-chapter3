package fastcampus.aos.part1.part1_chapter3

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.widget.ArrayAdapter
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
            val listener = OnDateSetListener { _, year, month, dayOfMonth ->
                binding.birthdateValueTextView.text = "$year-${month.inc()}-$dayOfMonth"
            }
            DatePickerDialog(
                this@EditActivity,
                listener,
                2000, 1, 1
            ).show()
        }

        // 체크박스
        binding.cautionCheckBox.setOnCheckedChangeListener { _, isChecked ->
            binding.cautionEditText.isVisible = isChecked
        }

        binding.cautionEditText.isVisible = binding.cautionCheckBox.isChecked
    }
}