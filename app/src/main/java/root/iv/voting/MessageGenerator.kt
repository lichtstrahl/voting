package root.iv.voting

import java.util.*

class MessageGenerator (val random:Random) {
    fun getRandomString(len:Int) :String {
        return (1..len)
                .map { kotlin.random.Random.nextInt(Char.MIN_VALUE.toInt(), Char.MAX_VALUE.toInt()).toChar() }
                .joinToString("")
    }
}