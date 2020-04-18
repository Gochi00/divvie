package com.example.divvie

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.divvie.database.DivvieDatabase
import com.example.divvie.database.Person

class DivvieViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = DivvieDatabase.getInstance(application).dao()

    fun insertPerson(person: Person) { dao.insertPerson(person) }

    fun getAllPerson() = dao.getAllPerson()

    fun deletePerson(person: Person) = dao.deletePerson(person)

    fun getNumberOfPeople() = dao.getNumberOfPeople()

    private val displayPrices = MutableLiveData<Boolean>()
    val displayPricesObservable: LiveData<Boolean>
        get() = displayPrices

    private val subtotal = MutableLiveData<Double>()
    val subtotalObservable: LiveData<Double>
        get() = subtotal

    private val tax = MutableLiveData<Double>()
    val taxObservable: LiveData<Double>
        get() = tax

    private val pretaxListOfPrices = MutableLiveData<List<Double>>()
    val pretaxListOfPricesObservable: LiveData<List<Double>>
        get() = pretaxListOfPrices

    fun setDisplayPrices(bool: Boolean) {
        displayPrices.value = bool
    }

    fun setSubtotal(num: Double) {
        subtotal.value = num
    }

    fun getSubtotal(): Double? {
        return subtotal.value
    }

    fun setTax(num: Double) {
        tax.value = num
    }

    fun getTax(): Double? {
        return tax.value
    }

    fun setPretaxListOfPrices(list: List<Double>) {
        pretaxListOfPrices.value = list
    }

    fun getPretaxListOfPrices(): List<Double>? {
        return pretaxListOfPrices.value
    }
}

