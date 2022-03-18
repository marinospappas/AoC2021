package mpdev.aoc2021.day24

class ALUProgram {

    // each function returns the new value Z after the 18 steps of the particular code section
    // is executed based on the previous value of Z and the input to this section (W)

    var inp = listOf(-1,1,2,3,4,5,6,7,8,9,1,2,3,4)

    /** subroutine 1 */
    fun sub1(): Int {
        return inp[1] + 15
    }

    /** subroutine 2 */
    fun sub2(z1: Int): Int {
        return z1 * 26 + inp[2] + 12
    }

    /** subroutine 3 */
    fun sub3(z2: Int): Int {
        return z2 * 26 + inp[3] + 15
    }

    /** subroutine 4 */
    fun sub4(z3: Int): Int {
        var z4 = z3 / 26   // => can only be z4 = z2 from above as inp3+15 is always < 26
        var x = z3 % 26 - 9 // => this is x = inp3 + 6 from above
        x = if (z3 % 26 - 9 == inp[4]) 0  else 1     // ******** input constraint
        z4 = (z3 / 26) * (25*x + 1) + (inp[4] + 12) * x    // z will reduce if x == 0
        return z4       // z4 reduces back to z2 if inp4 == inp3 + 6
    }

    /** subroutine 5 */
    fun sub5(z4: Int): Int {
        var z5 = z4 / 26
        var x = z4 % 26 - 7
        x = if (z4 % 26 - 7 == inp[5]) 0 else 1   // ******** input constraint
        z5 = (z4 / 26) * (25*x + 1) + (inp[5] + 15) * x   // z will reduce if x == 0
        return z5      // z4 reduces back to z1 if z4 % 26 - 7 == inp5
    }

    /** subroutine 6 */
    fun sub6(z5: Int): Int {
        // var x = z5 % 26 + 11
        // x = if (z5 % 26 + 11 == inp[6]) 0 else 1    // always false as inp<10 so x = 1
        val z6 = z5 * 26 + inp[6] + 2
        return z6
    }

    /** subroutine 7 */
    fun sub7(z6: Int): Int {
        var z7 = z6 / 26
        var x = z6 % 26 - 1
        x = if (z6 % 26 - 1 == inp[7]) 0 else 1   // ******** input constraint
        z7 = (z6 / 26) * (25*x + 1) + (inp[7] + 11) * x     // z will reduce if x == 0
        return z7     // z7 reduces back to z5 if z6 % 26 - 1 == inp7
                      // or inp6 + 1 = inp7  (if we substitute z6%26 with inp6 + 2 from above
    }

    /** subroutine 8 */
    fun sub8(z7: Int): Int {
        var z8 = z7 / 26
        var x = z7 % 26 - 16
        x = if (z7 % 26 - 16 == inp[8]) 0 else 1   // ******** input constraint
        z8 = (z7 / 26) * (25*x + 1) + (inp[8] + 15) * x     // z will reduce if x == 0
        return z8     // z8 reduces back to 0 if z7 % 26 - 16 == inp8
    }

    /** subroutine 9 */
    fun sub9(z8: Int): Int {
        return inp[9] + 10
    }

    /** subroutine 10 */
    fun sub10(z9: Int): Int {
        //var z10 = z9 / 26
        var z10 = 0     /// from above as inp[9]+10 < 26
        var x = z9 % 26 - 15    // inp9 - 5 from above
        x = if (inp[9] - 5 == inp[10]) 0 else 1    // ******* input constraint
        z10 = (inp[10] + 2) * x      // z will be 0 if inp[9] - 5 == inp[10]
        return z10
    }

    /** subroutine 11 */
    fun sub11(z10: Int): Int {
        return inp[11]
    }

    /** subroutine 12 */
    fun sub12(z11: Int): Int {
        return z11 * 26 + inp[12]
    }

    /** subroutine 13 */
    fun sub13(z12: Int): Int {
        var z13 = z12 / 26
        var x = z12 % 26 - 4    // inp12 - 4 from above
        x = if (inp[12] - 4 == inp[13]) 0 else 1    // ******* input constraint
        z13 = (z12 / 26) * (25 * x + 1) + (inp[12] + 15) * x      // z will be reduced if inp[12] - 4 == inp[13]
        return z13
    }

    /** subroutine 14 */
    fun sub14(z13: Int): Int {
        var z14 = z13 / 26
        var x = z13 % 26
        x = if (z13 % 26 == inp[14]) 0 else 1    // ******* input constraint
        z14 = (z13 / 26) * (25 * x + 1) + (inp[14] + 15) * x      // z will be reduced if z13 % 26 == inp14
        return z14
    }
}