# android-form-validators-lib

This is a library that aims to assist in the validation of forms in Android development.

Available validations:

```kotlin
/** Universal validations */
ValidatorRule.MustHaveLetterAndNumbers // The input value must contains letter and numbers.
ValidatorRule.MustNotBeEmptyOrNull // The input value must not be empty or null.
ValidatorRule.MustBeValidEmail // The input value must be a valid email address.
ValidatorRule.MustBeValidCreditCard // The input value must be a valid credit card.
ValidatorRule.MustBeBetweenTwoIntegers(firstValue: Int, secondValue: Int) // The input value must be between this two integers.
ValidatorRule.MustBeBetweenTwoDates(firstDate: Date, secondDate: Date) // The input value must be between this two dates.
ValidatorRule.MustBeValidRegex(regex: String) // The input value must match the regex.
ValidatorRule.MustBeValidCondition(condition: (() -> Boolean) // The condition must returns true.
ValidatorRule.MustHaveMinimumLength(size: Int) // The input value length must be at least $size.
ValidatorRule.MustHaveMaximumLength(size: Int) // The input value length must be a maximum of $size.

/** Canadian validations */
ValidatorRule.MustBeValidCanadianPostalCode // The input value must be a valid canadian postal code.

/** Brazilian validations */
ValidatorRule.MustBeValidBrazilianCPF // The input value must be a valid brazilian CPF.
```

Available methods:
```kotlin
validate(inputs: List<FormValidatorMaterialInput>, showInputError: Boolean = false, errorIcon: Drawable? = null): Boolean // Material Text Field Input
validate(inputs: List<FormValidatorDefaultInput>, showInputError: Boolean = false): Boolean // Default Android Edit Text
```

### Example with Material Text Field Input

```kotlin
val buttonMaterial = findViewById<Button>(R.id.buttonValidateMaterial)

val emailInputContainer = findViewById<TextInputLayout>(R.id.emailInputContainer)
val emailInput = findViewById<TextInputEditText>(R.id.emailInput)

val nameInputContainer = findViewById<TextInputLayout>(R.id.nameInputContainer)
val nameInput = findViewById<TextInputEditText>(R.id.nameInput)

val materialInputsToValidate = mutableListOf(
  /** Valid email validation */
  FormValidatorMaterialInput(emailInput, emailInputContainer, rules = listOf(ValidatorRule.MustBeValidEmail)),

  /** Null/empty validation */
  FormValidatorMaterialInput(nameInput, nameInputContainer, rules = listOf(ValidatorRule.MustNotBeEmptyOrNull))
)

buttonMaterial.setOnClickListener {
  val isValidForm = FormValidator.validate(materialInputsToValidate, true)

  Toast.makeText(this, "Is Valid: ${isValidForm}", Toast.LENGTH_LONG).show()
}
```

#### The result will be
![](https://i.imgur.com/FMIkkeV.png)

### Example with Default Edit Text

```kotlin
val buttonDefault = findViewById<Button>(R.id.buttonValidateDefault)

val emailInput = findViewById<EditText>(R.id.emailInput)
val nameInput = findViewById<EditText>(R.id.nameInput)

val defaultInputsToValidate = mutableListOf(
  /** Valid email validation */
  FormValidatorDefaultInput(emailInput, rules = listOf(ValidatorRule.MustBeValidEmail)),

  /** Null/empty validation */
  FormValidatorDefaultInput(nameInput, rules = listOf(ValidatorRule.MustNotBeEmptyOrNull))
)

buttonDefault.setOnClickListener {
  val isValidForm = FormValidator.validate(defaultInputsToValidate, true)

  Toast.makeText(this, "Is Valid: ${isValidForm}", Toast.LENGTH_LONG).show()
}
```

#### The result will be
![](https://i.imgur.com/AZj8iAv.png)

### You can also use multiple validations for a input
```kotlin
val buttonDefault = findViewById<Button>(R.id.buttonValidateDefault)

val emailInput = findViewById<EditText>(R.id.emailInput)
val nameInput = findViewById<EditText>(R.id.nameInput)

val defaultInputsToValidate = mutableListOf(
  /** Valid email validation */
  FormValidatorDefaultInput(emailInput, rules = listOf(ValidatorRule.MustBeValidEmail)),

  /** Null/empty validation */
  FormValidatorDefaultInput(nameInput, rules = listOf(ValidatorRule.MustNotBeEmptyOrNull, ValidatorRule.MustHaveMinimumLength(10))
)

buttonDefault.setOnClickListener {
  val isValidForm = FormValidator.validate(defaultInputsToValidate, true)

  Toast.makeText(this, "Is Valid: ${isValidForm}", Toast.LENGTH_LONG).show()
}
```

### Installation
Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

Add the dependency
```gradle
dependencies {
  implementation 'com.github.maikkkko1:android-form-validators-lib:1.0'
}
```
