fun main() {
    // инициализация тестовых полей
    val playerField = createEmptyField(10)
    playerField[3][2] = '#'
    playerField[3][3] = '#'

    val enemyField = createEmptyField(10)
    enemyField[5][7] = '#'
    enemyField[5][5] = 'X'
    enemyField[5][6] = 'O'

    val thirdField = createEmptyField(10)
    thirdField[2][2] = '#'

    // параметры для параллельного вывода
    val fields = arrayOf(playerField, enemyField, thirdField)
    val titles = arrayOf("Ваше поле", "Противник", "Третье")
    val show = booleanArrayOf(true, false, false)

    printFields(fields, titles, show)
}

// создание двумерного массива символов
fun createEmptyField(size: Int = 10): Array<CharArray> {
    return Array(size) { CharArray(size) { '.' } }
}

// вывод одиночной матрицы
fun printField(field: Array<CharArray>, title: String, showShips: Boolean = true, debug: Boolean = false) {
    val size = field.size

    if (debug) {
        println("=== $title (debug) ===")
        val border = "  " + "+---".repeat(size) + "+"

        print("    ")
        for (c in 0 ..< size) print("$c   ")
        println()

        for (r in field.indices) {
            println(border)
            print("$r |")
            for (c in field[r].indices) {
                val cell = field[r][c]
                val displayCell = if (!showShips && cell == '#') '.' else cell
                print(" $displayCell |")
            }
            println()
        }
        println(border)
    } else {
        println("=== $title ===")
        print("  ")
        for (c in 0 ..< size) print("$c ")
        println()

        for (r in field.indices) {
            print("$r ")
            for (c in field[r].indices) {
                val cell = field[r][c]
                if (!showShips && cell == '#') print(". ") else print("$cell ")
            }
            println()
        }
    }
    println()
}

// вывод двух матриц одной ширины рядом
fun printBothFields(player: Array<CharArray>, enemy: Array<CharArray>, showEnemyShips: Boolean = false) {
    if (player.size != enemy.size) {
        println("Ошибка: Поля должны быть одного размера.")
        return
    }
    val fields = arrayOf(player, enemy)
    val titles = arrayOf("Ваше поле", "Поле противника")
    val show = booleanArrayOf(true, showEnemyShips)

    printFields(fields, titles, show)
}

// параллельный вывод массива полей в одну строку
fun printFields(fields: Array<Array<CharArray>>, titles: Array<String>, showShips: BooleanArray) {
    if (fields.size != titles.size || fields.size != showShips.size || fields.isEmpty()) {
        println("Ошибка: Размеры массивов аргументов не совпадают.")
        return
    }

    val targetSize = fields[0].size
    for (i in fields.indices) {
        if (fields[i].size != targetSize) {
            println("Ошибка: Все поля должны быть одного размера.")
            return
        }
    }

    val spacesBetween = "            "

    // вывод строк заголовков
    for (i in fields.indices) {
        val titleText = "--- ${titles[i]} ---"
        print(titleText)
        val fieldWidthChars = targetSize * 2 + 2
        val remainingSpaces = fieldWidthChars - titleText.length
        if (remainingSpaces > 0) {
            print(" ".repeat(remainingSpaces))
        }
        if (i < fields.size - 1) print(spacesBetween)
    }
    println()

    // вывод строк индексов колонок
    for (i in fields.indices) {
        print("  ")
        for (c in 0 ..< targetSize) {
            print("$c ")
        }
        if (i < fields.size - 1) print(spacesBetween)
    }
    println()

    // вывод строк с данными матриц
    for (r in 0 ..< targetSize) {
        for (i in fields.indices) {
            print("$r ")
            val currentField = fields[i]
            val shouldShow = showShips[i]

            for (c in 0 ..< targetSize) {
                val cell = currentField[r][c]
                if (!shouldShow && cell == '#') {
                    print(". ")
                } else {
                    print("$cell ")
                }
            }
            if (i < fields.size - 1) print(spacesBetween)
        }
        println()
    }
    println()
}
