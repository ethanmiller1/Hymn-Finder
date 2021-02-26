package org.improving.service;

import org.improving.entity.Sermon;

import java.util.List;

public interface SermonService {

    List<Sermon> findAll();

    Sermon findById(int theId);

    void save(Sermon theSermon);

    void deleteById(int theId);

}
