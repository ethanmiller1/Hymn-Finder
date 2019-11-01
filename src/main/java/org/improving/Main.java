package org.improving;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        var linkCounter = new LinkCounter();
        Set<String> uniqueLinks = new HashSet<>();

        linkCounter.setStartTime(new Date());
        linkCounter.getLinks("https://improving.com", uniqueLinks);
        linkCounter.setEndTime(new Date());

        long elapsedTicks = linkCounter.getEndTime().getTime() - linkCounter.getStartTime().getTime();
        double elapsedSeconds = elapsedTicks / 1000.0;
        System.out.println("We were running for " + elapsedSeconds + "s.");
    }
}
