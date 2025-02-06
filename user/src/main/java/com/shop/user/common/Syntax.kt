package com.shop.user.common

//Cấu trúc cho toàn bộ file
// variable : private > public, const > val > var ,
// function : constructor > set/get > override > init > private), public > private),
// inner class...

// Example:
class Syntax {
    /*========================================================================
      CONSTANTS
    =========================================================================*/
    companion object {
        const val DEFAULT_VALUE = "Default"
        const val MAX_COUNT = 100
    }

    /*========================================================================
      VARIABLES
    =========================================================================*/
    private val myVariable: String = "Example"
    private var anotherVariable: Int = 0

    /*========================================================================
        CONSTRUCTOR
    =========================================================================*/
    init {
        // Constructor logic
        println("MyClass initialized")
    }

    /*========================================================================
        OVERRIDDEN METHODS
    =========================================================================*/
    override fun toString(): String {
        return "MyClass with value $myVariable"
    }

    /*========================================================================
        PUBLIC FUNCTIONS
    =========================================================================*/
    fun myPublicFunction() {
    }

    /*========================================================================
        FUNCTIONS
    =========================================================================*/
    private fun myPrivateFunction() {
    }

    /*========================================================================
        INNER CLASSES
    =========================================================================*/
    inner class MyInnerClass {
    }
}