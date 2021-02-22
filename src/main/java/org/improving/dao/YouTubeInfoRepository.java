package org.improving.dao;

import org.improving.entity.YouTubeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "youTubeInfo", path = "youtube-info")
public interface YouTubeInfoRepository extends JpaRepository<YouTubeInfo, Integer>
{
}
