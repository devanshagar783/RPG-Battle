package battle
import battle.classes.Person
import battle.classes.Spell
import battle.classes.Colors


/**
 * Created by Devansh on 01-06-2020
 */

class Main(): Colors() {
    fun main() {
        var fire = Spell("Fire", 10, 600, "Black")
        var thunder = Spell("Thunder", 10, 600, "Black")
        var blizzard = Spell("BLizzard ", 10, 600, "Black")
        var meteor = Spell("Meteor", 20, 1200, "Black")
        var quake = Spell("Quake", 14, 140, "Black")

        var cure = Spell("Cure", 12, 620, "White")
        var cura = Spell("Cura", 18, 1500, "White")

        var player_magic = arrayOf(fire, thunder, blizzard, meteor, cura, cure, quake)
        var enemySpells = arrayOf(fire, thunder, blizzard, meteor, cura, cure)

        val player1 = Person("Devansh", 3260, 132, 300, 34, player_magic)
        val player2 = Person("Somansh", 4160, 188, 311, 34, player_magic)

        // val ab = arrayOf<String>("")
        val enemy1 = Person("Thanos", 11200, 701, 525, 25, enemySpells)
        val enemy2 = Person("Loki", 1250, 130, 560, 325, enemySpells)

        var players = mutableListOf<Person>(player1, player2)
        var enemies = mutableListOf<Person>(enemy1, enemy2)

        var running: Boolean = true
        var i: Int = 0

        println(ANSI_BRIGHT_RED + "\n\nTHE ENEMY ATTACKS" + ANSI_RESET)

        loop@ while (running) {
            println("\n==============================")
            println("\n")
            print("NAME                       HP                                      MP")

            for (player in players)
                player.getStats()
            for (enemy in enemies)
                enemy.getEnemeyStats()

            println("\n")
            loop1@ for (player in players) {
                player.choose_action()
                var choice = Integer.valueOf(readLine())
                var index = choice - 1

                if (index == 0) {
                    var dmg = player.generate_damage()
                    var enemy = player.choose_target(enemies)

                    enemies[enemy].take_dmg(dmg)
                    println(ANSI_BRIGHT_GREEN + "You attacked ${enemies[enemy].name} for ${dmg} points of damage" + ANSI_RESET)

                    if (enemies[enemy].get_hp() == 0) {
                        print(ANSI_BRIGHT_GREEN + enemies[enemy].name + " is dead.\n" + ANSI_RESET)
                        var ask = enemies.removeAt(enemy)
                        if (enemies.size == 0) {
                            print(ANSI_BRIGHT_GREEN + "YOU WON!!!" + ANSI_RESET)
                            running = false
                            break@loop
                        }
                    }
                } else if (index == 1) {
                    player.choose_magic()
                    println("Choose magic:")
                    var magic_choice = Integer.valueOf(readLine()) - 1

                    if (magic_choice == -1)
                        continue

                    var spell: Spell
                    try {
                        spell = player.magic[magic_choice]
                    } catch (e: Exception) {
                        print("Using default value of fire because of wrong input")
                        spell = fire
                    }
                    var magic_dmg = spell.generate_damage()

                    var current_mp = player.get_mp()

                    if (spell.cost > current_mp) {
                        println("\nNot enough mp\n")
                        continue
                    }

                    player.reduce_mp(spell.cost)

                    if (spell.type == "White") {
                        player.heal(magic_dmg)
                        println(ANSI_BRIGHT_BLUE + "\n${spell.name} heals for ${magic_dmg} HP " + ANSI_RESET)
                    } else if (spell.type == "Black") {
                        val enemy = player.choose_target(enemies)

                        enemies[enemy].take_dmg(magic_dmg)
                        println(ANSI_BRIGHT_GREEN + "\n${spell.name} deals ${magic_dmg} points of damage to ${enemies[enemy].name}" + ANSI_RESET)

                        if (enemies[enemy].get_hp() == 0) {
                            println(ANSI_BRIGHT_GREEN + enemies[enemy].name + " is dead." + ANSI_RESET)
                            var ask = enemies.removeAt(enemy)
                            if (enemies.size == 0) {
                                print(ANSI_BRIGHT_GREEN + "YOU WON!!!" + ANSI_RESET)
                                running = false
                                break@loop
                            }
                        }
                    }
                } else {
                    println("Wrong input")
                    i = 1
                    break@loop1
                }

            }

            println()
            for (enemy in enemies) {

                var enemyChoice = (0..1).random()
                if (enemyChoice == 0) {
                    var target = (0 until players.size).random()
                    var enemyDamage = enemy.generate_damage()

                    players[target].take_dmg(enemyDamage)
                    println(ANSI_BRIGHT_RED + "${enemy.name} deals ${enemyDamage} points of damage to ${players[target].name}" + ANSI_RESET)

                    if (players[target].get_hp() == 0) {
                        println(ANSI_BRIGHT_RED + players[target].name + " has died." + ANSI_RESET)
                        var ask = players.removeAt(target)
                        if (players.size == 0) {
                            print(ANSI_BRIGHT_RED + "YOU LOST!!!" + ANSI_RESET)
                            running = false
                            break@loop
                        }
                    }
                }
                if (enemyChoice == 1) {
                    var magicChoice = (enemy.magic.indices).random()
                    var spell = enemy.magic[magicChoice]
                    var magicDmg = spell.generate_damage()

                    enemy.reduce_mp(spell.cost)

                    if (spell.type == "White") {
                        enemy.heal(magicDmg)
                        println(ANSI_BRIGHT_BLUE + "${spell.name} heals ${enemy.name} for ${magicDmg} HP." + ANSI_RESET)
                    } else if (spell.type == "Black") {
                        var target = (0 until players.size).random()
                        players[target].take_dmg(magicDmg)
                        println(ANSI_BRIGHT_RED + "${enemy.name} deals ${magicDmg} points of damage to ${players[target].name}" + ANSI_RESET)

                        if (players[target].get_hp() == 0) {
                            println(ANSI_BRIGHT_RED + players[target].name + " has died." + ANSI_RESET)
                            var ask = players.removeAt(target)
                            if (players.size == 0) {
                                print(ANSI_BRIGHT_RED + "YOU LOST!!!" + ANSI_RESET)
                                running = false
                                break@loop
                            }
                        }
                    }
                }
            }
        }
    }
}

fun main(){
    Main().main()
}