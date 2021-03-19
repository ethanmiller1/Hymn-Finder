package org.improving.service;

import org.improving.entity.Sermon;

import java.util.List;

public interface SermonService {

    List<Sermon> findAll();

    Sermon findById(long theId);

    void save(Sermon theSermon);

    void deleteById(long theId);

}
