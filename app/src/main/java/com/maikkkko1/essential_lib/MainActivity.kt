package com.maikkkko1.essential_lib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.maikkkko1.essential_libs.form_validator.FormValidator
import com.maikkkko1.essential_libs.form_validator.ValidatorRule
import com.maikkkko1.essential_libs.form_validator.entity.FormValidatorDefaultInput
import com.maikkkko1.essential_libs.form_validator.entity.FormValidatorMaterialInput
import com.maikkkko1.essential_libs.common.CommonManager
import com.maikkkko1.essential_libs.common.entity.ScheduleItemAndroidCalendar
import java.util.*
import kotlin.time.days

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setValidator()
        setWebview()
        setCallPhone()
        setScheduleItem()
    }

    private fun setScheduleItem() {
        findViewById<Button>(R.id.btnScheduleCalendar).setOnClickListener {
            CommonManager(activity = this).scheduleItemOnAndroidCalendar(
                    ScheduleItemAndroidCalendar(
                            startDate = Date(),
                            endDate = Date(Date().time + (1000 * 60 * 60 * 24)),
                            eventTitle = "Event test"
                    )
            )
        }
    }

    private fun setCallPhone() {
        findViewById<Button>(R.id.btnCallPhone).setOnClickListener {
            CommonManager(activity = this).callPhoneNumber("+55 51 980316522")
        }
    }

    private fun setWebview() {
        findViewById<Button>(R.id.buttonOpenWebView).setOnClickListener {
            CommonManager(activity = this).openWebView("https://www.guarana-technologies.com/")
        }
    }

    private fun setValidator() {
        val buttonMaterial = findViewById<Button>(R.id.buttonValidateMaterial)
        val buttonDefault = findViewById<Button>(R.id.buttonValidateDefault)

        val materialInputContainer = findViewById<TextInputLayout>(R.id.materialInputContainer)
        val materialInput = findViewById<TextInputEditText>(R.id.materialInput)

        val defaultInput = findViewById<EditText>(R.id.defaultInput)

        val validationRules: List<ValidatorRule> = listOf(
                ValidatorRule.MustNotBeEmptyOrNull)

        val materialInputsToValidate = mutableListOf(
                FormValidatorMaterialInput(
                        input = materialInput,
                        container = materialInputContainer,
                        rules = validationRules
                )
        )

        buttonMaterial.setOnClickListener {
            val isValidForm = FormValidator.validate(materialInputsToValidate, true)

            Toast.makeText(this, "Is Material Valid: ${isValidForm}", Toast.LENGTH_LONG).show()
        }

        val defaultInputsToValidate = mutableListOf(
                FormValidatorDefaultInput(
                        input = defaultInput,
                        rules = validationRules
                )
        )


        buttonDefault.setOnClickListener {
            val isValidForm = FormValidator.validate(defaultInputsToValidate, true)

            Toast.makeText(this, "Is Default Valid: ${isValidForm}", Toast.LENGTH_LONG).show()
        }
    }
}