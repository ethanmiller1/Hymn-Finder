package org.improving.entity;

import lombok.Data;

@Data
public class ArchiveResource {
    private String title;
    private String link;

    public ArchiveResource() {
    }

    public ArchiveResource(String title, String link) {
        this.title = title;
        this.link = link;
    }
}
