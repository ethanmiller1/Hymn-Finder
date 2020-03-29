package org.improving;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        var hymnFinder = new HymnFinder();

        var startTime = new Date();
//        var hymns = hymnFinder.getMultipleHymns(HymnFinder.SEARCHES);
//        hymnFinder.printAllHymns(hymns);
        System.out.println(hymnFinder.getStanzas("Wonderful Peace"));
        var endTime = new Date();

        long elapsedTicks = endTime.getTime() - startTime.getTime();
        double elapsedSeconds = elapsedTicks / 1000.0;
        System.out.println("We were running for " + elapsedSeconds + "s.");
    }
}
