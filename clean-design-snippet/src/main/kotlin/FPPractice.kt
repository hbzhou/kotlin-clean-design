
fun nestFunc (firstName: String): (String) -> String {
    return { lastName -> "$firstName $lastName"}
}

fun compose (f1: (String) -> String, f2: (String) -> String): (String) -> String {
    return { input ->  f2(f1(input))}
}

fun pipe(f1: () -> String, f2: (String) -> String): () -> String {
    return { f2(f1()) }
}

val  toUpperCase = { str: String ->str.uppercase() }

val concatQuestionMark = { str: String -> str.plus("?")}


fun main() {
    val helloPrefix = nestFunc("hello")
    val fullStr = helloPrefix("world")
    println(fullStr)

    val composed = compose(toUpperCase, concatQuestionMark)
    val result = composed("System.out.Println")
    println(result)

    val boxed1 = Box(toUpperCase).map(concatQuestionMark).map(helloPrefix).fold("System.out.Println")
    println(boxed1)
    val boxed2 = Box(helloPrefix)
        .map(toUpperCase)
        .map(concatQuestionMark)
        .fold("System.out.Println")
    println(boxed2)


    val task1 = Task{"System.out.println"}
        .map(helloPrefix)
        .map(toUpperCase)
        .map(concatQuestionMark)
        .fold()

    println(task1)

    val task2 = Task{"System.out.println"}
        .map(toUpperCase)
        .map(concatQuestionMark)
        .map(helloPrefix).fold()

    println(task2)

}

class Box (private val f: (String) -> String ){
    fun map (g: (String) -> String ) = Box(compose(f, g))
    fun fold(str: String) = f(str)
}

class Task (private val f: () -> String ){
    fun map (g: (String) -> String) = Task(pipe(f, g))
    fun fold() = f()
}


