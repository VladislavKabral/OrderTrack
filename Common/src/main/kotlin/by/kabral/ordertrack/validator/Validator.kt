package by.kabral.ordertrack.validator

interface Validator<T> {
    fun validate(dto: T)
}