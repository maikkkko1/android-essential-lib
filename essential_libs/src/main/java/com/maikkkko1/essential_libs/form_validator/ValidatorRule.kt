package com.maikkkko1.essential_libs.form_validator

import java.util.*

sealed class ValidatorRule {
    object MustHaveLetterAndNumbers: ValidatorRule()
    object MustNotBeEmptyOrNull: ValidatorRule()
    object MustBeValidEmail: ValidatorRule()
    object MustBeValidCreditCard: ValidatorRule()

    data class MustBeBetweenTwoIntegers(val firstValue: Int, val secondValue: Int): ValidatorRule()
    data class MustBeBetweenTwoDates(val firstDate: Date, val secondDate: Date): ValidatorRule()

    data class MustBeValidCondition(val condition: (() -> Boolean)): ValidatorRule()

    data class MustBeValidRegex(val regex: String): ValidatorRule()

    data class MustHaveMinimumLength(val size: Int): ValidatorRule()
    data class MustHaveMaximumLength(val size: Int): ValidatorRule()

    /** Canadian rules **/
    object MustBeValidCanadianPostalCode: ValidatorRule()

    /** Brazilian rules **/
    object MustBeValidBrazilianCPF: ValidatorRule()
}