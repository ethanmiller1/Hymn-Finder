package org.improving.controller;

import org.improving.entity.Sermon;
import org.improving.service.SermonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sermons")
public class SermonController {

    private SermonService sermonService;

    @Autowired
    public SermonController(SermonService sermonService) {
        this.sermonService = sermonService;
    }

    @GetMapping("/list")
    public List<Sermon> findAll() {
        return sermonService.findAll();
    }
}
