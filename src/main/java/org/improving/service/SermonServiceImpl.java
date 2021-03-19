package org.improving.service;

import org.improving.dao.SermonRepository;
import org.improving.entity.Sermon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SermonServiceImpl implements SermonService {

    private SermonRepository sermonRepository;

    @Autowired
    public SermonServiceImpl(SermonRepository sermonRepository) {
        this.sermonRepository = sermonRepository;
    }

    @Override
    public List<Sermon> findAll() {
        return sermonRepository.findAll();
    }

    @Override
    public Sermon findById(long id) {
        return sermonRepository.findById(id).orElseGet(() -> throwRuntime(id));
    }

    @Override
    public void save(Sermon sermon) {
        sermonRepository.save(sermon);
    }

    @Override
    public void deleteById(long id) {
        sermonRepository.deleteById(id);
    }

    public void updateArchiveResourceById(long id, String link) {
        sermonRepository.updateArchiveResourceById(id, link);
//        Sermon sermon = findById(id);
//        sermon.setArchiveResource(link);
//        sermonRepository.save(sermon);
    }



    private Sermon throwRuntime(long id) {
        throw new RuntimeException("Did not find sermon id - " + id);
    }
}
