package com.example.divvie

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.divvie.database.DivvieDatabase
import com.example.divvie.database.Person

class DivvieViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = DivvieDatabase.getInstance(application).dao()

    private fun getAllPersonStatic() = dao.getAllPersonStatic()

    fun getAllPerson() = dao.getAllPerson()

    fun insertPerson(person: Person) { dao.insertPerson(person) }

    fun deletePerson(person: Person) = dao.deletePerson(person)

    fun getNumberOfPeople() = dao.getNumberOfPeople()

    fun updatePerson(person: Person) {dao.updatePerson(person)}

    fun splitPretaxEqually() {
        for (person in getAllPersonStatic()) {
            person.subtotal = getSubtotal()?.div(getAllPersonStatic().size) ?: AMOUNT_DEFAULT
            updatePerson(person)
        }
    }

    fun clearPersonalSubtotal() {
        for (person in getAllPersonStatic()) {
            person.subtotal = AMOUNT_DEFAULT
            updatePerson(person)
        }
    }

    fun calculatePersonResult() {
        for (person in getAllPersonStatic()) {
            val tax: Double = getTax() ?: AMOUNT_DEFAULT
            val tip: Double = getTip() ?: AMOUNT_DEFAULT
            val ratio = person.subtotal / getSubtotal()!!
            person.tax = ratio * tax
            person.tip = ratio * tip
            updatePerson(person)
        }
    }

    private val displayPrices = MutableLiveData<Boolean>()
    val displayPricesObservable: LiveData<Boolean>
        get() = displayPrices

    private val subtotal = MutableLiveData<Double>()
    val subtotalObservable: LiveData<Double>
        get() = subtotal

    private val tax = MutableLiveData<Double>()
    val taxObservable: LiveData<Double>
        get() = tax

    private val tip = MutableLiveData<Double>()
    val tipObservable: LiveData<Double>
        get() = tip

    private val total = MutableLiveData<Double>()
    val totalObservable: LiveData<Double>
        get() = total

    private fun setTotal() {
        val subtotal: Double = getSubtotal() ?: AMOUNT_DEFAULT
        val tax: Double = getTax() ?: AMOUNT_DEFAULT
        val tip: Double = getTip() ?: AMOUNT_DEFAULT
        total.value = subtotal + tax + tip
    }

    fun setDisplayPrices(bool: Boolean) {
        displayPrices.value = bool
    }

    fun setSubtotal(num: Double) {
        subtotal.value = num
        setTotal()
    }

    fun getSubtotal(): Double? {
        return subtotal.value
    }

    fun setTax(num: Double) {
        tax.value = num
        setTotal()
    }

    fun getTax(): Double? {
        return tax.value
    }

    fun setTip(num: Double) {
        tip.value = num
        setTotal()
    }

    fun getTip(): Double? {
        return tip.value
    }
}

