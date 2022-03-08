package uz.coder.personallibrary

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import uz.coder.personallibrary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(binding.root)
        loadData()
        onClick()


    }


    private fun loadData() {
        arrayAdapter =
            ArrayAdapter(this, R.layout.simple_list_item_1, GetAllCountryList.getCategory())
        binding.spinner1.setAdapter(arrayAdapter)
    }

    private fun isNotBlank() = (binding.et1.isNotBlank() && binding.et2.isNotEmpty()
            && binding.et3.isNotEmpty() && binding.et4.isNotBlank()
            && binding.et5.isNotBlank() && binding.et6.isNotBlank()
            && binding.et7.isNotBlank()) && binding.spinner1.itemSelected()


    private fun onClick() {
        binding.apply {
            clear.setOnClickListener {
                spinner1.isSelected(false)
                et1.text?.clear()
                et2.clear()
                et3.clear()
                et4.text?.clear()
                et5.text?.clear()
                et6.text?.clear()
                et7.text!!.clear()
                et8.text?.clear()
            }
            sumbit.setOnClickListener {

                if (isNotBlank() && check()) {
                    Toast.makeText(
                        this@MainActivity,
                        "Registration was successful!!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
    }


    private fun check(): Boolean {
        binding.apply {
            return et4.correctAndSetError() &&
                    et5.correctAndSetError() &&
                    et1.correctAndSetError() &&
                    et6.correctAndSetError() &&
                    et2.textAndSetError() &&
                    et3.textAndSetError() &&
                    et7.correctAndSetError() &&
                    et8.correctAndSetError() &&
                    spinner1.itemSelected()
        }
    }

}