package uz.coder.personallibrary

import android.content.Context
import android.content.res.TypedArray
import android.text.InputFilter
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class ValidatorEditText(context: Context, attributeSet: AttributeSet?) :
    AppCompatEditText(context, attributeSet) {

    private var typedArray: TypedArray =
        context.obtainStyledAttributes(attributeSet, R.styleable.ValidatorEditText)
    private val format: Int = typedArray.getInt(R.styleable.ValidatorEditText_typeInput,0)

    init {
        when (format) {
            0 -> {
                inputType = InputType.TYPE_CLASS_TEXT
                hint = "Enter text"
            }
            1 -> {
                inputType = InputType.TYPE_TEXT_VARIATION_PHONETIC
                hint = "Phone number"
            }
            2 -> {
                inputType = InputType.TYPE_CLASS_NUMBER
                keyListener = DigitsKeyListener.getInstance("0123456789.")
                hint = "Ip address"
            }
            3 -> {
                inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                transformationMethod = PasswordTransformationMethod.getInstance()
                hint = "Password"
            }
            4 -> {
                inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                hint = "Email address"
            }
            5 -> {
                inputType = InputType.TYPE_CLASS_NUMBER
                hint = "Zip Code"
            }
            6 -> {
                inputType = InputType.TYPE_CLASS_DATETIME
                filters = arrayOf(InputFilter.LengthFilter(4))
                hint = "Year"
            }
        }
    }


    private fun correctFilled(): Boolean {
        var isChecked = false
        when (format) {
            0 -> {
                if (text.toString().isNotEmpty())
                    isChecked = true
            }
            1 -> {
                val phonePattern = "\\+[0-9]+".toRegex()
                isChecked = text.toString().matches(phonePattern)
            }
            2 -> {
                val ipPattern = "[0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+".toRegex()
                isChecked = text.toString().matches(ipPattern)
            }
            3 -> {
                if (text.toString().isNotEmpty())
                    isChecked = true
            }
            4 -> {
                val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
                isChecked = text.toString().matches(emailPattern)
            }
            5 -> {
                val zipCodePattern = "^[0-9]{5}(?:-[0-9]{4})?$".toRegex()
                isChecked = text.toString().matches(zipCodePattern)
            }
            6 -> {
                if (text.toString().isNotEmpty() && text.toString() != "0") {
                    isChecked = true
                }
            }
        }
        return isChecked
    }

    private fun errorDrawable(msg: String) {
        setCompoundDrawablesWithIntrinsicBounds(
            0,
            0,
            R.drawable.ic_baseline_error_24,
            0
        )
        error = msg
    }

    fun isNotBlank(): Boolean {
        if (text!!.isEmpty()) {
            errorDrawable("line is empty!")
        }
        return text!!.isNotBlank()
    }

    fun correctAndSetError():Boolean {
        if (isNotBlank()) {
            if (!correctFilled()) {
                errorDrawable("format not correctly!")
            }else {
                setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    0,
                    0
                )
                setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_baseline_check_circle_24,
                    0
                )
            }
        }

        return correctFilled()
    }
}