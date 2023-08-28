
fun nestFunc (firstName: String): (String) -> String {
    return fun (lastName: String) = "$firstName $lastName"
}

fun compose (f1: (String) -> String, f2: (String) -> String): (String) -> String {
 return fun(input: String):String{
     return f1(f2(input))
 }
}

val toUpperCase = fun (str: String) = str.uppercase()

val concatQuestionMark = fun (str: String) = str.plus("?")


fun main() {
    val helloPrefix = nestFunc("jeremy")
    val fullStr = helloPrefix("gilbert")
    println(fullStr)

    val composed = compose(toUpperCase, concatQuestionMark)
    val result = composed("System.out.Println")
    println(result)

}



