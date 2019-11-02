package org.improving;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        var linkCounter = new LinkCounter();
        Set<String> uniqueLinks = new HashSet<>();

        var startTime = new Date();
        linkCounter.getLinks("https://improving.com", uniqueLinks);
        var endTime = new Date();

        long elapsedTicks = endTime.getTime() - startTime.getTime();
        double elapsedSeconds = elapsedTicks / 1000.0;
        System.out.println("We were running for " + elapsedSeconds + "s.");
    }
}
