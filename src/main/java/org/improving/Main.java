package org.improving;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        var linkCounter = new LinkCounter();
        Set<String> uniqueLinks = new HashSet<>();

        linkCounter.getLinks("https://improving.com", uniqueLinks);
    }
}
