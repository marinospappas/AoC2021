package mpdev.aoc2021.day19

const val NUMBER_OF_OVERL_BCNS = 12

/**
 * identifies two overlapping scanners by:
 * 1. identifying at least 12 beacons that consist of sets of 3, which:
 *      for each set of these 3 beacons any pair of beacons has equal square distance
 *      to the corresponding pair across the two scanners
 * 2. then all the permutations of these 12 beacons are tried for both scanners
 *    (or all the permutations of 12 beacons if more than 12 were identified in step 1)
 *      so that they are arranged in a way where all the square distances between any two beacons
 *      in the same positions are the same across the two scanners
 *  -> at this point we know we have a definite overlap
 * 3. the scanner that was found to be overlapping has its absolute position and orientation updated:
 *      all possible 24 orientation are tested in a loop until the new scanners position and orientation
 *      give us absolute coordinates for all the beacons in the new scanner that agree with
 *      the absolute coordinates of the beacons in the old scanner.
 */
fun Scanner.overlaps(sc2: Scanner): Boolean {
    // 1. find all the beacon triplets that have equal distances between any two of them
    // across the two scanners
    val overlappingPairs = findTripletsOfEqDist(this.distanceToBeacon, sc2.distanceToBeacon)
    if (overlappingPairs[0].size >= NUMBER_OF_OVERL_BCNS) {
        // 2. find the actual overlapping pairs where all distances between any 2 beacons are the same
        val overlappingBeacons =
            compareBeacCombs(overlappingPairs[0], this.distanceToBeacon, overlappingPairs[1], sc2.distanceToBeacon)
        if (overlappingBeacons[0].isNotEmpty()) {
            // 3. if minimum number of beacons overlaps position
            // calculate scanner 1 position and orientation
            calculateScannerPosition(this, overlappingBeacons[0], sc2, overlappingBeacons[1])
            println("scanner ${sc2.id} position: ${sc2.position}")
            sc2.inPosition = true
            sc2.overlappedBy = this.id
            return true
        }
    }
    return false
}

fun findTripletsOfEqDist(array1: Array<Array<Long>>, array2: Array<Array<Long>>): List<List<Int>> {
    val listOfEquals1 = mutableListOf<Int>()
    val listOfEquals2 = mutableListOf<Int>()
    // find two points of equal distance across the two scanners
    // scanner 1
    for (i1 in 0 until array1.size)
        for (j1 in i1 until array1.size)
            // check against scanner 2
            for (i2 in 0 until array2.size)
                for (j2 in i2 until array2.size)
                    if (array1[i1][j1] > 0 && array1[i1][j1] == array2[i2][j2]) {
                        // now try to add a third point
                        for (k1 in 0 until array1.size)
                            for (k2 in 0 until array2.size)
                                if (array1[i1][k1] > 0 && array1[i1][k1] == array2[i2][k2]
                                    && array1[j1][k1] > 0 && array1[j1][k1] == array2[j2][k2]) {
                                    // all square distances match - add the 3 points to the list
                                    // (for both scanners)
                                    listOfEquals1.add(i1)
                                    listOfEquals1.add(j1)
                                    listOfEquals1.add(k1)
                                    listOfEquals2.add(i2)
                                    listOfEquals2.add(j2)
                                    listOfEquals2.add(k2)
                                }
                    }
    return listOf(listOfEquals1.distinct(), listOfEquals2.distinct())
}

fun compareBeacCombs(pairs1: List<Int>, array1: Array<Array<Long>>, pairs2: List<Int>, array2: Array<Array<Long>>): List<List<Int>> {
    pairs1.permutations(NUMBER_OF_OVERL_BCNS).forEach { comb1 ->
        pairs2.permutations(NUMBER_OF_OVERL_BCNS).forEach { comb2 ->
            if (compareDistances(comb1, array1, comb2, array2))
                return (listOf(comb1, comb2))
        }
    }
    return listOf(listOf(), listOf())
}

fun compareDistances(beacons1Indx: List<Int>, array1: Array<Array<Long>>, beacons2Indx: List<Int>, array2: Array<Array<Long>>): Boolean {
    val distances1 = mutableListOf<Long>()
    for (i in 0 .. beacons1Indx.size-2)
        for (j in i+1 .. beacons1Indx.size-1) {
            distances1.add(array1[ beacons1Indx[i] ][ beacons1Indx[j] ])
        }
    val distances2 = mutableListOf<Long>()
    for (i in 0 .. beacons2Indx.size-2)
        for (j in i+1 .. beacons2Indx.size-1)
            distances2.add(array2[ beacons2Indx[i] ][ beacons2Indx[j] ])
    return distances1.isEqual(distances2)
}

fun calculateScannerPosition(sc1: Scanner, overlBeac1: List<Int>, sc2: Scanner, overlBeac2: List<Int>) {
    orientation@for (tryOrientation in orientation) {
        sc2.xyzMapping = tryOrientation
        sc2.position.x = sc1.beacons[overlBeac1[0]].absCoord.x - sc2.xMapped(sc2.beacons[overlBeac2[0]].relCoord)
        sc2.position.y = sc1.beacons[overlBeac1[0]].absCoord.y - sc2.yMapped(sc2.beacons[overlBeac2[0]].relCoord)
        sc2.position.z = sc1.beacons[overlBeac1[0]].absCoord.z - sc2.zMapped(sc2.beacons[overlBeac2[0]].relCoord)
        sc2.updBcnCoord()
        for (index in overlBeac1.indices)
            if (! sc1.beacons[ overlBeac1[index] ].absCoord.isEqual( sc2.beacons[ overlBeac2[index] ].absCoord ))
                continue@orientation
        break
    }
}

fun List<Long>.isEqual(list2: List<Long>): Boolean {
    if (this.size != list2.size)
        return false
    var equal = true
    for (i in this.indices)
        equal = equal && this[i] == list2[i]
    return equal
}
