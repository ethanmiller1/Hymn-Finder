package org.improving.dao;

import org.improving.entity.Sermon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SermonRepository extends JpaRepository<Sermon, Integer>
{
}
