package battle.classes

/**
 * Created by Devansh on 01-06-2020
 */

open class Person(name: String = "", hp: Int = 0, mp:Int = 0, atk: Int = 0, defence: Int = 0, magic: Array<Spell>){
    var maxmp: Int = mp
    var mp: Int = mp
    var maxhp: Int = hp
    var hp: Int = hp
    var atkl: Int = atk - 10
    var atkh: Int = atk + 10
    var df: Int = defence
    var name: String = name
    var magic = magic
    var actions = arrayOf<String>("Attack", "Magic")

    fun generate_damage(): Int{
        return (atkl..atkh).random()
    }

    fun take_dmg(dmg:Int): Int{
        hp-=dmg
        if (hp<0){
            hp=0
        }
        return hp
    }

    fun heal(dmg:Int): Int{
        hp+=dmg
        if (hp>maxhp){
            hp=maxhp
        }
        return hp
    }

    fun get_hp(): Int{
        return hp
    }

    fun get_max_hp(): Int{
        return maxhp
    }

    fun get_mp(): Int{
        return mp
    }

    fun get_max_mp(): Int{
        return mp
    }

    fun reduce_mp(cost: Int){
        mp-=cost
    }

    fun choose_action(){
        var i: Int = 1
        print(name)
        println("\tACTIONS:")
        for (item in actions){
            println("\t\t${i}:${item}")
            ++i
        }
    }

    fun choose_target(enemies: MutableList<Person>): Int{
        var i = 1
        println("\nTARGET:")
        for (enemy in enemies){
            println("\t\t${i}.${enemy.name}")
            ++i
        }
        var choice = Integer.valueOf(readLine())
        var ch = choice - 1
        return ch

    }

    fun choose_magic(){
        var i = 1
        println("\n\tMAGIC:")
        for (spell in magic){
            println("\t\t${i}: ${spell.name} (cost: ${spell.cost} )")
            ++i
        }
    }

    fun getEnemeyStats(){
        var hpval:Int = 50*hp/maxhp
        var hpstr:String = "$hp/$maxhp"
        var currhp = ""

        if (hpstr.length < 11){
            var decreased = 11 - hpstr.length

            while (decreased > 0){
                currhp+=" "
                decreased-=1
            }
            currhp+=hpstr
        }
        else
            currhp=hpstr

        print("\n                         __________________________________________________\n")
        print("${name}\t     ${currhp}|")

        for (i in 0 until hpval)
            print("█")
        for (i in 0..(49-hpval))
            print(" ")

        print("| ")
    }

    fun getStats(){
        var hpval:Int = 25*hp/maxhp
        var mpval:Int = 10*mp/maxmp
        var hpstr:String = "$hp/$maxhp"
        var currhp = ""

        if (hpstr.length < 9){
            var decreased = 9 - hpstr.length

            while (decreased > 0){
                currhp+=" "
                decreased-=1
            }
            currhp+=hpstr
        }
        else
            currhp=hpstr

        var mpstr:String = "$mp/$maxmp"
        var currmp = ""

        if(mpstr.length < 7) {
            var decreased = 7 - mpstr.length

            while (decreased > 0) {
                currmp += " "
                decreased -= 1
            }
            currmp += mpstr
        }
        else
            currmp = mpstr

        print("\n                         __________________________             ___________\n")
        print("${name}        ${currhp}|")
        for(i in 0..hpval)
            print("█")
        for(i in 0..(24 - hpval))
            print(" ")

        print("|    $currmp|")

        for(i in 0..mpval)
            print("█")
        for(i in 0..(9 - mpval))
            print(" ")

        print( "|")

    }



}