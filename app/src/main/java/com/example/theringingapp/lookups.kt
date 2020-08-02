package com.example.theringingapp

fun getNumOfBellsName(numBells: Int): String{
    when (numBells) {
        3-> return "Singles"
        4-> return "Minimus"
        5-> return "Doubles"
        6-> return "Minor"
        7-> return "Triples"
        8-> return "Major"
        9-> return "Caters"
        10-> return "Royal"
        11-> return "Cinques"
        12-> return "Maximus"
        13-> return "sextuples"
        14-> return "Fourteen"
        15-> return "septuples"
        16-> return "Sixteen"
        else -> {
            return numBells.toString()
        }
    }
}