fun main() {
    while (true) {
        println("Введите количество секунд, когда вы были в последний раз на сайте или нажмите Enter для того чтобы выйти из программы.")
        var input = readLine()
        if (input != "") {
            println(agoToText(Integer.parseInt(input)))
        } else {
            println("Спасибо за работу.")
            break
        }
    }
}

fun agoToText(seconds: Int): String {
    return when {
        seconds >= 0 && seconds < 60 -> "был(а) только что"
        seconds >= 60 && seconds < 60 * 60 -> conjugateMinutes(seconds)
        seconds >= 60 * 60 && seconds < 24 * 60 * 60 -> conjugateHours(seconds)
        seconds >= 24 * 60 * 60 && seconds < 24 * 2 * 60 * 60 -> "был(а) в сети сегодня"
        seconds >= 24 * 2 * 60 * 60 && seconds < 24 * 3 * 60 * 60 -> "был(а) в сети вчера"
        seconds >= 24 * 3 * 60 * 60 -> "был(а) в сети давно"
        else -> throw Exception("Параметр seconds должен быть положительным числом")
    }
}

fun conjugateMinutes(seconds: Int): String {
    val secondsInMinutes = seconds / 60
    val lastCharacter = secondsInMinutes % 10
    val twoLastCharacters = secondsInMinutes % 100
    val minutes = when {
        lastCharacter == 1 && twoLastCharacters != 11 -> "минуту"
        (twoLastCharacters >= 11 && twoLastCharacters <= 19)
                || (lastCharacter >= 5 && lastCharacter <= 9)
                || lastCharacter == 0 -> "минут"
        else -> "минуты"
    }
    return "был(а) в сети $secondsInMinutes $minutes назад"
}

fun conjugateHours(seconds: Int): String {
    val secondsInHours = seconds / 3600
    val lastCharacter = secondsInHours % 10
    val twoLastCharacters = secondsInHours % 100
    val hours = when {
        lastCharacter == 1 && twoLastCharacters != 11 -> "час"
        (twoLastCharacters >= 11 && twoLastCharacters <= 19)
                || (lastCharacter >= 5 && lastCharacter <= 9)
                || lastCharacter == 0 -> "часов"
        else -> "часа"
    }
    return "был(а) в сети $secondsInHours $hours назад"
}