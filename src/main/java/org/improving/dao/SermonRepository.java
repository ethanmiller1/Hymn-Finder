package org.improving.dao;

import org.improving.entity.Sermon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin( "http://localhost:4200")
public interface SermonRepository extends JpaRepository< Sermon, Integer >
{
}
