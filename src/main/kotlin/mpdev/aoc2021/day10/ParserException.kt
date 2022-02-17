package mpdev.aoc2021.day10

enum class Error { OK, illegParen, illegSq, illegCurl, illegTr, incomplete, other }

val errScore = mapOf (  Pair(Error.illegParen, 3),
                        Pair(Error.illegSq, 57),
                        Pair(Error.illegCurl, 1197),
                        Pair(Error.illegTr, 25137),
                      )

class ParserException(message: String, var error: Error, var score: Int = 0): Exception(message)