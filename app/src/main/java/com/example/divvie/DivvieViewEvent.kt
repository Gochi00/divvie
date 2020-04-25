package com.example.divvie

open class DivvieViewEvent

sealed class InputViewEvent: DivvieViewEvent() {
    object DisplayFragment: InputViewEvent()
    object InsertPerson: InputViewEvent()
    object RemovePerson: InputViewEvent()
    data class EnterSubtotal(val input: String): InputViewEvent()
    data class EnterTax(val input: String): InputViewEvent()
    object Next: InputViewEvent()
}

sealed class SplitViewEvent: DivvieViewEvent() {
    object DisplayFragment: SplitViewEvent()
    object SplitEqually: SplitViewEvent()
    object EnterIndividually: SplitViewEvent()
    object Back: SplitViewEvent()
}

sealed class ItemViewEvent: DivvieViewEvent() {
    object DisplayFragment: ItemViewEvent()
    data class EnterItemPrice(val input: String): ItemViewEvent()
    object Next: ItemViewEvent()
    object Done: ItemViewEvent()
    object Back: ItemViewEvent()
    object ClearAll: ItemViewEvent()
}

sealed class ResultViewEvent: DivvieViewEvent() {
    object DisplayFragment: ResultViewEvent()
    data class EnterTip(val input: String): ResultViewEvent()
    object ToggleFormat: ResultViewEvent()
    object Back: ResultViewEvent()
    object StartOver: ResultViewEvent()
}

sealed class BowlsViewEvent: DivvieViewEvent() {
    object DisplayFragment: BowlsViewEvent()
    data class ClickBowl(val i: Int): BowlsViewEvent()
    object DisplayBreakdown: BowlsViewEvent()
}