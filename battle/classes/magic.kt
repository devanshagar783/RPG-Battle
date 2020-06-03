package battle.classes

/**
 * Created by Devansh on 03-06-2020
 */

open class Spell(name:String, cost:Int, dmg:Int, type:String){
    var name = name
    var cost = cost
    var dmg = dmg
    var type = type

    fun generate_damage(): Int{
        val low = dmg -15
        val high = dmg + 15
        return (low..high).random()
    }
}