package org.improving.dao;

import org.improving.entity.Sermon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin( "http://localhost:4200")
public interface SermonRepository extends JpaRepository< Sermon, Long >
{
    @Transactional
    @Modifying
    @Query("update Sermon sermon set sermon.archiveResource = :archiveResource where sermon.id = :id")
    void updateArchiveResourceById( @Param("id") long id, @Param("archiveResource") String archiveResource);

    @Query("select sermon from Sermon sermon where sermon.title = :#{#req.title} and sermon.preacher = :#{#req.preacher}")
    Optional<Sermon> findMatchingSermon(@Param("req") Sermon req);

    Sermon findByTitle(String title);
}
